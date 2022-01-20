package com.lhw.base.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

/**
 * @author ：linhw
 * @date ：21.6.25 11:15
 * @description： 基础数据访问接口
 * @modified By：
 */
@NoRepositoryBean
public interface BaseRepository<ENTITY,ID> extends JpaRepository<ENTITY,ID> {

    ENTITY find(ID id);

    ENTITY findOne(ID id);

    void deleteOne(ID id);

    int updateOne(ENTITY entity);

    List<ENTITY> findAllByIds(Collection<ID> ids, Sort sort);

    Page<ENTITY> findAllByIds(Collection<ID> ids, Pageable pageable);

}
