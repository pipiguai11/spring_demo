package com.lhw.datasource_postgres.repo;

import com.lhw.datasource_postgres.module.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：linhw
 * @date ：22.1.20 13:25
 * @description：
 * @modified By：
 */
@Repository
public interface UserTestRepository extends JpaRepository<UserTest,String> {
}
