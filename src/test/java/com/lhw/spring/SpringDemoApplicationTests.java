package com.lhw.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringDemoApplicationTests {

    @Autowired
    JavaMailSenderImpl sender;

    @Test
    void contextLoads() throws Exception {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("953522392@qq.com");
//        simpleMailMessage.setTo("linhw@dist.com.cn");
        simpleMailMessage.setTo("623010541@qq.com");
        simpleMailMessage.setSubject("hello world");
        simpleMailMessage.setText("嗨，臭宝");
        sender.send(simpleMailMessage);

//        MimeMessage mimeMessage = sender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//        messageHelper.setFrom("953522392@qq.com");
//        messageHelper.setTo("linhw@dist.com.cn");
//        messageHelper.setSubject("Happy New Year");
//        messageHelper.setText("新年快乐！");
//        messageHelper.addInline("doge.gif", new File("E:\\temp\\1.jpg"));
//        messageHelper.addAttachment("work.docx", new File("E:\\temp\\test.doc"));
//        sender.send(mimeMessage);

    }

}
