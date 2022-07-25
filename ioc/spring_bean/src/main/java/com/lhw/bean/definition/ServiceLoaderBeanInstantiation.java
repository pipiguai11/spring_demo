package com.lhw.bean.definition;

import com.lhw.bean.interfaces.UserTestInterface;
import com.lhw.bean.interfaces.impl.UserTestImpl1;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author ：linhw
 * @date ：22.5.15 11:41
 * @description：使用ServiceLoader读取文件实现bean的实例化
 *
 *      注意点：
 *          1、ServiceLoader都是基于接口去读取的
 *              意思就是基于接口去读取实现类
 *          2、在读取之前要先按照要求创建一个文件
 *              在META-INF.services目录下，创建一个名为接口的全路径的文件（{@link com.lhw.bean.interfaces.UserTestInterface}）
 *              然后在这个文件中写入所有实现类的全路径名
 *          3、然后就可以使用ServiceLoader直接读取了
 *
 * @modified By：
 */
public class ServiceLoaderBeanInstantiation {

    public static void main(String[] args) {
//        readFile();
//        registerBean();
        registerImplBean();
    }

    /**
     * 直接读取文件并加载类，这里并没有注入到ioc容器
     */
    private static void readFile() {
        //读取UserTestInterface接口的所有实现类，也就是在META-INF.services目录下的com.lhw.bean.interfaces.UserTestInterface文件中配置的所有类
        ServiceLoader<UserTestInterface> loader = ServiceLoader.load(UserTestInterface.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(loader);
    }

    /**
     * 输出实现类的getMessage方法
     * @param loader
     */
    private static void displayServiceLoader(ServiceLoader<UserTestInterface> loader) {
        Iterator<UserTestInterface> interfaceIterator = loader.iterator();
        while (interfaceIterator.hasNext()) {
            UserTestInterface userTestInterface = interfaceIterator.next();
            System.out.println(userTestInterface.getMessage());
        }
    }


    /**
     * 将ServiceLoader注入到容器中，然后在去读取
     *      效果和上面的readFile方法一样，依旧只是读取实现类，但是没有将实现类注册到ioc容器中
     */
    private static void registerBean() {
        GenericApplicationContext context = new GenericApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ServiceLoaderFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("serviceType", UserTestInterface.class);

        context.registerBeanDefinition("myServiceLoader", beanDefinitionBuilder.getBeanDefinition());

        context.refresh();

        System.out.println(context.getBeansOfType(ServiceLoader.class));
        ServiceLoader loader = context.getBean("myServiceLoader", ServiceLoader.class);
        displayServiceLoader(loader);

        context.close();
    }

    /**
     * 基于上面的进行优化，将实现类全都注册到IOC容器中
     */
    private static void registerImplBean() {
        GenericApplicationContext context = new GenericApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ServiceLoaderFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("serviceType", UserTestInterface.class);

        context.registerBeanDefinition("myServiceLoader", beanDefinitionBuilder.getBeanDefinition());

        context.refresh();

        //拿到ServiceLoader
        ServiceLoader<UserTestInterface> loader = context.getBean("myServiceLoader", ServiceLoader.class);
        //从中获取到迭代器，封装了所有的实现类
        Iterator<UserTestInterface> iterator = loader.iterator();
        //将每一个实现类都注册到ioc容器中
        while (iterator.hasNext()) {
            UserTestInterface temp = iterator.next();
            context.registerBean(temp.getClass().getSimpleName(), temp.getClass());
        }

        //依赖查找
        /**结果如下：
         * {UserTestImpl1=com.lhw.bean.interfaces.impl.UserTestImpl1@78e67e0a, UserTestImpl2=com.lhw.bean.interfaces.impl.UserTestImpl2@bd8db5a}
         * {myServiceLoader=java.util.ServiceLoader[com.lhw.bean.interfaces.UserTestInterface]}
         * 测试实现类1
         */
        System.out.println(context.getBeansOfType(UserTestInterface.class));
        System.out.println(context.getBeansOfType(ServiceLoader.class));
        System.out.println(context.getBean(UserTestImpl1.class).getMessage());

        context.close();
    }

}
