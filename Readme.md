### spring常规应用

#### 2021.11.19
1、在程序运行过程中动态创建controller接口

测试:
在程序跑起来之后，先调用【http://localhost:8080/test2/create】创建一个接口,
然后调用【http://localhost:8080/lhw/create】测试创建出来的接口


#### 2021.11.25
1、添加邮件发送功能
首先开启邮箱的SMTP服务，然后拿到授权码，引入mail启动器依赖，添加配置，然后就可以通过JavaMailSenderImpl发送邮件了