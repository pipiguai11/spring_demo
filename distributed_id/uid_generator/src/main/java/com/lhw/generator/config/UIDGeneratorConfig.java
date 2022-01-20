package com.lhw.generator.config;

import com.xfvape.uid.impl.CachedUidGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：21.5.24 11:05
 * @description：UID生成器配置
 * @modified By：
 */
@Configuration
public class UIDGeneratorConfig {

    @Bean("myIdAssinger")
    public MyIdAssinger myIdAssinger(){
        return new MyIdAssinger();
    }

    @Bean("cachedUidGenerator")
    public CachedUidGenerator uidGenerator(@Qualifier("myIdAssinger")MyIdAssinger myIdAssinger){
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(myIdAssinger);
        //设置snowflake算法中的sequence最大值这里表示2的9次方，不设置的话默认是3
        cachedUidGenerator.setBoostPower(9);
        //设置支持机器重启的次数，每次重启会在数据库记录一次，且这里的分配策略是用完即弃，这里表示允许重启2的22次方次，也就是大概420万次
        cachedUidGenerator.setWorkerBits(23);
        //设置每秒下的并发序列，13bits支持每秒8192个并发
        //测试发现，结果中的sequence和这个有关，如果同一秒的sequence序列超过了这里设定的位数【比如这里设置了7，说明sequence最大占7位数，也就是2的7次方等于127】，则会自旋等待下一秒然后刷新sequence后继续输出
        cachedUidGenerator.setSeqBits(7);
        //设置当前时间相对与时间基点（上面设置的时间）的增量值
        cachedUidGenerator.setTimeBits(33);
        //设置时间基点
        cachedUidGenerator.setEpochStr("2016-05-20");

        return cachedUidGenerator;
    }

//    @Bean("defaultUidGenerator")
//    public DefaultUidGenerator defaultUidGenerator(){
//        return new DefaultUidGenerator();
//    }

}
