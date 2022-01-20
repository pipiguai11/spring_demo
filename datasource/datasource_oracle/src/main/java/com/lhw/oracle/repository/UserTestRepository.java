package com.lhw.oracle.repository;

import com.lhw.oracle.module.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：linhw
 * @date ：22.1.20 11:34
 * @description：
 * @modified By：
 */
@Repository
public interface UserTestRepository extends JpaRepository<UserTest,String> {
}
