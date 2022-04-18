package com.lhw.jwt_auth.repo;

import com.lhw.jwt_auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：linhw
 * @date ：22.4.18 14:33
 * @description：
 * @modified By：
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
