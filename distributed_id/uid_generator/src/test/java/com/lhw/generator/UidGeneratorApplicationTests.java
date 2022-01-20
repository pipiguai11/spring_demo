package com.lhw.generator;

import com.lhw.generator.repo.WorkerNodeRepository;
import com.xfvape.uid.impl.CachedUidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UidGeneratorApplicationTests {

    @Autowired
    private WorkerNodeRepository repo;

    @Autowired
    private CachedUidGenerator cachedUidGenerator;

    @Test
    void testUid(){
        long uid = cachedUidGenerator.getUID();
        long uid2 = cachedUidGenerator.getUID();
        long uid3 = cachedUidGenerator.getUID();
        System.out.println(cachedUidGenerator.parseUID(uid));
        System.out.println(uid);
        System.out.println(cachedUidGenerator.parseUID(uid2));
        System.out.println(uid2);
        System.out.println(cachedUidGenerator.parseUID(uid3));
        System.out.println(uid3);

        /**
         * 结果输出：uid是唯一的，通过创建时间和sequence进行区分
         *      UID：指的是通过雪花算法算出来的唯一ID
         *      timestamp：创建时间
         *      workerId：数据库中对应的记录ID（每次重启机器，都会在数据库中生成一条记录，这个workerId就是那条记录的标识id）
         *      sequence：同一时刻创建出来的UID排序
         * {"UID":"5434612747539300352","timestamp":"2021-05-24 15:34:08","workerId":"10014","sequence":"0"}
         * 5434612747539300352
         * {"UID":"5434612747539300353","timestamp":"2021-05-24 15:34:08","workerId":"10014","sequence":"1"}
         * 5434612747539300353
         * {"UID":"5434612747539300354","timestamp":"2021-05-24 15:34:08","workerId":"10014","sequence":"2"}
         * 5434612747539300354
         */

    }

    @Test
    void testParam(){
        for (int i = 0; i<1000; i++){
            long tempUid = cachedUidGenerator.getUID();
            System.out.println(cachedUidGenerator.parseUID(tempUid));
        }
    }

}
