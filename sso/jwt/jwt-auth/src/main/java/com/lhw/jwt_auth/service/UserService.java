package com.lhw.jwt_auth.service;

import com.lhw.jwt_auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ：linhw
 * @date ：22.4.18 14:34
 * @description：spring security自定义用户存储方式具体实现
 *          从数据库中根据用户名进行查询
 * @modified By：
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
