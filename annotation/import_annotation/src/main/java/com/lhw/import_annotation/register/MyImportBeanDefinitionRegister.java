package com.lhw.import_annotation.register;

import com.lhw.import_annotation.model.TestA;
import com.lhw.import_annotation.model.TestB;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：linhw
 * @date ：22.5.5 13:55
 * @description：
 *
 *      自定义的Bean注册器
 *      在这里手动的把一些类注册到IOC容器中
 *
 * @modified By：
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        RootBeanDefinition aDef = new RootBeanDefinition(TestA.class);
        RootBeanDefinition bDef = new RootBeanDefinition(TestB.class);
        registry.registerBeanDefinition("a", aDef);
        registry.registerBeanDefinition("b", bDef);

    }

}
