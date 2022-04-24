package com.lhw.jwt_auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ：linhw
 * @date ：22.4.18 14:43
 * @description：自定义用户存储方式，集成UserDetailsService接口，然后实现类中实现loadUserByUsername方法即可
 * @modified By：
 */
public interface IUserService extends UserDetailsService {
}
