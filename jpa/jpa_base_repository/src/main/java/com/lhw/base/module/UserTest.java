package com.lhw.base.module;

import com.lhw.base.annotation.NeedFilterField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERTEST")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTest {

    @Id
    @Column(name = "CODE")
    private String code;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADDR")
    private String addr;
    @Column(name = "AGE")
    @NeedFilterField
    private int age;

    @PrePersist
    public void testPrePersist(){
        System.out.println("=====================start==========================");
        System.out.println("保存实体操作：【Prepersist】");
        System.out.println("调用时期：在持久化之前");
        System.out.println("=====================end==========================");
    }

    @PostPersist
    public void testPostPersist(){
        System.out.println("=====================start==========================");
        System.out.println("保存实体操作：【PostPersist】");
        System.out.println("调用时期：在数据库存储实体，commit和flush期间");
        System.out.println("=====================end==========================");
    }

    @PostLoad
    public void testPostLoad(){
        System.out.println("=====================start==========================");
        System.out.println("查询实体操作：【PostLoad】");
        System.out.println("调用时期：数据库检索实体后");
        System.out.println("=====================end==========================");
    }

    @PreUpdate
    public void testPreUpdate(){
        System.out.println("=====================start==========================");
        System.out.println("更新实体操作：【PreUpdate】");
        System.out.println("调用时期：当一个实体被识别为被修改时EntityManager");
        System.out.println("=====================end==========================");
    }

    @PostUpdate
    public void testPostUpdate(){
        System.out.println("=====================start==========================");
        System.out.println("更新实体操作：【PostUpdate】");
        System.out.println("调用时期：更新数据库中的实体（在commit或期间flush）");
        System.out.println("=====================end==========================");
    }

    @PreRemove
    public void testPreRemove(){
        System.out.println("=====================start==========================");
        System.out.println("删除实体操作：【PreRemove】");
        System.out.println("调用时期：在EntityManager中标记要删除的实体时");
        System.out.println("=====================end==========================");
    }

    @PostRemove
    public void testPostRemove(){
        System.out.println("=====================start==========================");
        System.out.println("删除实体操作：【PostRemove】");
        System.out.println("调用时期：从数据库中删除实体（在commit或期间flush）");
        System.out.println("=====================end==========================");
    }

}
