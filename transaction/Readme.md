## spring事务失效的场景

### 1、未启用spring事务管理功能
@EnableTransactionManagement 注解用来启用spring事务自动管理事务的功能

### 2、方法不是public类型的
@Transaction 可以用在类上、接口上、public方法上，如果将@Trasaction用在了非public方法上，事务将无效

### 3、数据源未配置事务管理器
spring是通过事务管理器了来管理事务的，一定不要忘记配置事务管理器了，要注意为每个数据源配置一个事务管理器
参考com.lhw.transaction.config.DatasourceConfig

### 4、自身调用问题
spring是通过aop的方式，对需要spring管理事务的bean生成了代理对象，然后通过代理对象拦截了目标方法的执行，
在方法前后添加了事务的功能，所以必须通过代理对象调用目标方法的时候，事务才会起效

### 5、异常类型错误
spring事务回滚的机制：对业务方法进行try catch，当捕获到有指定的异常时，spring自动对事务进行回滚
并不是任何异常情况下，spring都会回滚事务，默认情况下，RuntimeException和Error的情况下，spring事务才会回滚
当然也可以自定义，通过@Transaction(rollbackFor = {异常类型列表})

### 6、异常被吞了
当业务方法抛出异常，spring感知到异常的时候，才会做事务回滚的操作，若方法内部将异常给吞了，那么事务无法感知到异常了，事务就不会回滚了
也就是事务方法内部进行了try catch

### 7、业务和spring事务代码必须在一个线程中
spring事务实现中使用了ThreadLocal，ThreadLocal大家应该知道吧，可以实现同一个线程中数据共享，必须是同一个线程的时候，数据才可以共享，
这就要求业务代码必须和spring事务的源码执行过程必须在一个线程中，才会受spring事务的控制