package com.lhw.createinterface.service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ：linhw
 * @date ：21.11.19 14:51
 * @description： 新的接口
 * @modified By：
 */
public class NewControllerService {

    public void testCreate() throws IOException {
        System.out.println("create success");
        HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getResponse();
        assert response != null;
        OutputStream outputStream = response.getOutputStream();
        outputStream.write("create success".getBytes());
        outputStream.flush();
        outputStream.close();
//        return "create success";
    }

}
