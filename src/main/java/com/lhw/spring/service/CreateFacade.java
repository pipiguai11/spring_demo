package com.lhw.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ：linhw
 * @date ：21.11.19 13:40
 * @description： 通过反射在程序运行过程中动态的创建controller接口
 *      这里创建完的接口访问路径就是如下的NEW_URL，前提是调用了/test2/create去创建这个接口
 * @modified By：
 */
@Service
public class CreateFacade {

    public static final String NEW_URL = "/lhw/create";

    public void createController() throws Exception {
        //获取到WebApplicationContext
        WebApplicationContext applicationContext = (WebApplicationContext)RequestContextHolder
                .currentRequestAttributes()
                .getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT",0);
        Assert.notNull(applicationContext,"applicationContext is Null");

        //从当前上下文环境中获取到指定Bean对象
        //这个RequestMappingHandlerMapping对象主要就是用来注册controller接口的，其中有个mappingRegistry属性用于存放应用的所有接口路径地址【如：/test1/message、/test2/create等等，对应的可以看到controller类】
        RequestMappingHandlerMapping mappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //上面这个RequestMappingHandlerMapping其实是继承了这个AbstractHandlerMethodMapping类的，上面所说的mappingRegistry真正定义是在这个AbstractHandlerMethodMapping类中的
        AbstractHandlerMethodMapping methodMapping = applicationContext.getBean(AbstractHandlerMethodMapping.class);

        //直接通过反射获取到AbstractHandlerMethodMapping类中的mappingRegistry，由于它是私有的，所以需要先设置setAccessible(true)
        Method method = Class.forName("org.springframework.web.servlet.handler.AbstractHandlerMethodMapping").getDeclaredMethod("getMappingRegistry");
        method.setAccessible(true);
        //然后调用invoke方法，从上下文环境中的AbstractHandlerMethodMapping对象上获取到mappingRegistry
        Object mappingRegistry = method.invoke(methodMapping);

        //在mappingRegistry对象中存在一个名叫pathLookup的Map对象，所有的路径数据都作为key存在这上面，同样它是私有的
        Field pathLookup = Class.forName("org.springframework.web.servlet.handler.AbstractHandlerMethodMapping$MappingRegistry").getDeclaredField("pathLookup");
        pathLookup.setAccessible(true);
        //从上下文环境中读取所有的pathLookup
        Map pathLookupMap = (Map) pathLookup.get(mappingRegistry);

        //先判断环境中是否已经存在该路径了，如果存在则直接返回，不存在则创建
        for (Object o : pathLookupMap.keySet()) {
            if (NEW_URL.equals(o)){
                System.out.println("【" + NEW_URL + "】已存在");
                return;
            }
        }

        //这后面就是创建一个新的接口的过程了。

        //通过反射获取到新的service的方法
        Method newMethod = NewControllerService.class.getDeclaredMethod("testCreate");

        //设置它的访问路径，可以直接发起http请求访问的
        PatternsRequestCondition condition = new PatternsRequestCondition(NEW_URL);

        //设置它的请求方式，这里设置成GET，方便浏览器测试，当然也可以设置成其他的
        RequestMethodsRequestCondition methodsRequestCondition = new RequestMethodsRequestCondition(RequestMethod.GET);

        //在内存中动态注册controller
        RequestMappingInfo info = new RequestMappingInfo(condition,methodsRequestCondition,null,null,null,null,null);

        //注册controller，当请求路径匹配上了/lhw/create时，就会自动调用NewControllerService类中的testCreate方法。
        mappingHandlerMapping.registerMapping(info, new NewControllerService(),newMethod);

    }

}
