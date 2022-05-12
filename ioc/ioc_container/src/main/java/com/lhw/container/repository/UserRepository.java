package com.lhw.container.repository;

import com.lhw.container.domain.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author ：linhw
 * @date ：22.5.12 10:51
 * @description：
 * @modified By：
 */
@Data
public class UserRepository {

    private Collection<User> users;

    private BeanFactory beanFactory;

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

}
