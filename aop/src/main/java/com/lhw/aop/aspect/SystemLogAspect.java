package com.lhw.aop.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhw.aop.annotation.PrintLog;
import com.lhw.aop.module.Log;
import com.lhw.aop.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：linhw
 * @date ：21.12.21 13:13
 * @description：系统日志切面
 * @modified By：
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    LogService service;

    @Pointcut("@annotation(com.lhw.aop.annotation.PrintLog)")
    public void logAspect(){}


    @AfterThrowing(pointcut = "logAspect()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) throws Exception{
        service.save(buildLog(joinPoint,"执行失败",ex));
    }

    @AfterReturning(value = "logAspect()", returning = "result")
    public void afterRetureMethod(JoinPoint joinpoint, Object result) throws Exception {
        service.save(buildLog(joinpoint,result,null));
    }

    private Log buildLog(JoinPoint joinPoint, Object result, Exception ex) throws Exception{
        HttpServletRequest request = getHttpServletRequest();

        Log log = Log.builder()
                .result(result.toString())
                .logTime(SDF.format(new Date()))
                .author("lhw")
                .description(getServiceMethodDescription(joinPoint))
                .remark(getServiceMethodParams(joinPoint))
                .url(request.getRequestURL().toString())
                .ip(request.getLocalAddr())
                .exMessage(null == ex ? null : ex.getMessage())
                .build();
        return log;
    }

    /**
     * 获取方法上的注解描述（也就是注解PrintLog中的description）
     * @param point
     * @return
     */
    private String getServiceMethodDescription(JoinPoint point) throws Exception {
        //获取目标类名
        String targetClassName = point.getTarget().getClass().getName();
        //获取目标方法名
        String targetMethodName = point.getSignature().getName();
        //获取目标方法所有的参数
        Object[] parameters = point.getArgs();
        Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0 ; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }

        //通过反射获取到目标类和所有方法
        Class<?> clazz = Class.forName(targetClassName);
        Method[] methods = clazz.getMethods();

        String description = "";
        //循环遍历，查找其中对应方法的PrintLog注解中的description
        outer : for (Method method : methods) {
            if (method.getName().equals(targetMethodName)){
                //获取方法的所有参数类型
                Class<?>[] tempParameterType = method.getParameterTypes();
                //然后判断方法是否匹配（可能存在重载的情况）
                if (tempParameterType.length != parameterTypes.length){
                    continue;
                }
                //前面参数个数一致不代表就是同一个方法，还要判断一下参数类型是否都一致
                for (int i = 0 ; i < tempParameterType.length ; i++){
                    if (!manualPacking(tempParameterType[i]).equals(parameterTypes[i].getSimpleName())){
                        //如果存在一个不一致的情况，直接跳出本次外层循环，继续下一次循环
                        continue outer;
                    }
                }

                description = method.getAnnotation(PrintLog.class).description();
                break;
            }
        }

        return description;
    }

    /**
     * 获取json格式的参数用于存储到数据库中
     * @param point
     * @return
     * @throws Exception
     */
    private String getServiceMethodParams(JoinPoint point) throws Exception {
        Object[] params = point.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(params);
    }


    /**
     * 获取servlet请求体
     * @return
     */
    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)attributes;
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }

    /**
     * 手动装箱，将基本数据类型转成包装类型，然后返回SimpleName，用于判断参数类型是否一致
     * @param clazz
     * @return
     */
    private String manualPacking(Class<?> clazz){
        switch (clazz.getSimpleName()){
            case "int" : return Integer.class.getSimpleName();
            case "byte" : return Byte.class.getSimpleName();
            case "short" : return Short.class.getSimpleName();
            case "long" : return Long.class.getSimpleName();
            case "boolean" : return Boolean.class.getSimpleName();
            case "float" : return Float.class.getSimpleName();
            case "double" : return Double.class.getSimpleName();
            case "char" : return Character.class.getSimpleName();
            default : return clazz.getSimpleName();
        }
    }

}
