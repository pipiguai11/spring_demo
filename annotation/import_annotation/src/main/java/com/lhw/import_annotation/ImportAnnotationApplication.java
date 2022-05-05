package com.lhw.import_annotation;

import com.lhw.import_annotation.annotation.MyEnableAutoConfig;
import com.lhw.import_annotation.annotation.MyEnableAutoConfigByBean;
import com.lhw.import_annotation.annotation.MyEnableAutoConfigByDefinitionRegister;
import com.lhw.import_annotation.annotation.MyEnableAutoConfigByMyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MyEnableAutoConfigByBean
//@MyEnableAutoConfigByMyConfig
@MyEnableAutoConfigByDefinitionRegister
//@MyEnableAutoConfig
public class ImportAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportAnnotationApplication.class, args);
    }

}
