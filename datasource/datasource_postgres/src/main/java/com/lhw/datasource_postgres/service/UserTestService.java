package com.lhw.datasource_postgres.service;

import com.lhw.datasource_postgres.module.UserTest;
import com.lhw.datasource_postgres.repo.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserTestService {

    @Autowired
    UserTestRepository testRepository;

    public UserTest getUserByCode(String code){
        System.out.println("执行了查询操作");
        return testRepository.findById(code).orElse(null);
    }

    public List<UserTest> getAll(){
        System.out.println("执行了查询操作");
        return testRepository.findAll();
    }

    public UserTest saveUser(UserTest u){
        System.out.println("执行了保存操作");
        return testRepository.save(u);
    }

    public String testMessage(){
        return "result";
    }

    public String testTime(int num){
        return LocalDate.now().lengthOfYear() + " " + LocalDate.now().lengthOfMonth();
    }

}
