package com.lhw.jwt_auth.repo;

import com.lhw.jwt_auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：linhw
 * @date ：22.4.18 14:30
 * @description：
 * @modified By：
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    User findByUsername(String userName);

}
