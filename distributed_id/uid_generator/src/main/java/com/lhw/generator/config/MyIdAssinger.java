package com.lhw.generator.config;

import com.lhw.generator.module.WorkerNode;
import com.lhw.generator.repo.WorkerNodeRepository;
import com.xfvape.uid.utils.DockerUtils;
import com.xfvape.uid.utils.NetUtils;
import com.xfvape.uid.worker.WorkerIdAssigner;
import com.xfvape.uid.worker.WorkerNodeType;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author ：linhw
 * @date ：21.5.24 11:03
 * @description：id生成器
 * @modified By：
 */
public class MyIdAssinger implements WorkerIdAssigner {

    @Autowired
    private WorkerNodeRepository repo;

    @Override
    public long assignWorkerId() {
        WorkerNode workerNode = buildWorkerNode();
        repo.save(workerNode);
        WorkerNode result = repo.findByHostNameAndPortAndType(workerNode.getHostName(),workerNode.getPort(),workerNode.getType());
        return result.getId();
    }

    private WorkerNode buildWorkerNode(){
        WorkerNode workerNode = new WorkerNode();
        if (DockerUtils.isDocker()){
            workerNode.setType(WorkerNodeType.CONTAINER.value());
            workerNode.setHostName(DockerUtils.getDockerHost());
            workerNode.setPort(DockerUtils.getDockerPort());
            workerNode.setLaunchDate(new Date());
        }else {
            workerNode.setType(WorkerNodeType.ACTUAL.value());
            workerNode.setHostName(NetUtils.getLocalAddress());
            workerNode.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(100000));
            workerNode.setLaunchDate(new Date());
        }
        return workerNode;
    }

}
