package com.lhw.generator.repo;

import com.lhw.generator.module.WorkerNode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：linhw
 * @date ：21.5.24 14:22
 * @description：UID生成
 * @modified By：
 */
public interface WorkerNodeRepository extends JpaRepository<WorkerNode,Integer> {

    WorkerNode findByHostNameAndPortAndType(String hostName, String port, int type);

}
