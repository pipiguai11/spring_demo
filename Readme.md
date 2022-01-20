### 模块说明
| 模块名 | 模块说明 |
| ---- | ---- |
| annotation | 注解模块，spring中的注解demo |
| main | 邮件服务，自定义邮件发送 |
| create_interface | 自定义运行时动态创建接口服务（类似低代码） |
| aop  | spring aop测试|
| datasource | 数据库demo |
| proxy | 代理demo|
| jpa | jpa的demo| 





### spring常规应用

#### 2021.11.19
1、在程序运行过程中动态创建controller接口

测试:
在程序跑起来之后，先调用【http://localhost:8080/test2/create】创建一个接口,
然后调用【http://localhost:8080/lhw/create】测试创建出来的接口


#### 2021.11.25
1、添加邮件发送功能
首先开启邮箱的SMTP服务，然后拿到授权码，引入mail启动器依赖，添加配置，然后就可以通过JavaMailSenderImpl发送邮件了


#### 2021.12.21
1、添加AOP

使用aop处理正常返回的调用以及出现异常的调用，将结果作为日志存放到数据库，两个都是后置切面