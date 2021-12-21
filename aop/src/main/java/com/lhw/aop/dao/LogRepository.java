package com.lhw.aop.dao;

import com.lhw.aop.module.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ：linhw
 * @date ：21.12.21 11:29
 * @description：日志映射对象
 * @modified By：
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Integer> {

    List<Log> findByLogTimeAfter(Date date);

    List<Log> findByLogTimeBetween(Date start, Date end);

    List<Log> findByLogTimeBefore(Date date);

}
