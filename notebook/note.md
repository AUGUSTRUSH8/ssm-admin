- 后台设计规范：https://www.zcool.com.cn/work/ZMjYzODAxMzY=.html
- 为什么选择AdminLTE3
    - 基于 BootStrap
      前文中谈到 BootStrap 的优点 AdminLTE 模板也都有，响应式、代码复用性高、上手快……
    - 视觉效果
      对比了几个系统模板，AdminLTE 应该是最漂亮，从看到的第一眼就会喜欢上，美观、大气。
    - 社区活跃
      关注度高、更新迭代快，一直在进步和优化。
    - 复用性高
      工具齐全且内置了比较完善的后台管理系统所需页面，可以大大减少开发工作量，开箱即用。
    - 开源免费
      一般这种类似成品的 WebApp 模板都是收费的，而 AdminLTE 是完全免费的，开源在 GitHub 上
  
  怎么用？  http://blog.51cto.com/11140372/1906359
  
  - 新技能get：Ctrl+Shift+F12 --Windows上代码窗口最大化
  - 后台基本布局： AdminLTE3 模板中的 index.html 文件直接修改而来。
  
  - BootStrap Modal模态框
  >BootStrap模态框（Modal）是覆盖在父窗体上的子窗体，通常用来显示来自单独源的内容，可以在不离开父窗体的情况下有一些互动，子窗体可提供信息、交互等，想要在页面中使用该插件的功能，需要引用 modal.js，或者引用 bootstrap.js，或者压缩版的 bootstrap.min.js。
  
  - mysql版本之间的区别？
  比如5.5和5.7之间的时间的表示上就不同，5.5不认识datetime的类型，而5.7可以的
  
  - ubuntu下mysql的配置
  https://blog.csdn.net/xiangwanpeng/article/details/54562362
  http://dblab.xmu.edu.cn/blog/2140-2/
  
  - 重要bug记录
  程序本地跑的好好的，放到服务器上就是无法运行，catalina.out日志文件当中是这么记录的
  ```2016-01-14-14-22 [http-nio-8082-exec-1] [org.springframework.beans.factory.xml.XmlBeanDefinitionReader] [INFO] - Loading XML bean definitions from class path resource [org/springframework/jdbc/support/sql-error-codes.xml]
       2016-01-14-14-22 [http-nio-8082-exec-1] [org.springframework.jdbc.support.SQLErrorCodesFactory] [INFO] - SQLErrorCodes loaded: [DB2, Derby, H2, HSQL, Informix, MS-SQL, MySQL, Oracle, PostgreSQL, Sybase]
  ```
  错误原因：由于插入字断超过默认最大长度。
  
  - 部署上线注意事项
  修改
  ```
  public final static String FILE_PRE_URL = "http://localhost:8080";
  ```
  为服务器ip地址
  
  - 日志输出策略
  >在生产环境中，一般将应用系统的日志级别调整为 INFO 以避免过多的日志输出，但某些时候，需要跟踪具体问题，那么就得打开 DEBUG 日志级别。但如果打开 log4j.rootLogger，则需要的信息可能会淹没在日志的海洋中，这种情况下，就需要单独指定某个或者某些 Logger 的日志级别为 DEBUG，而 rootLogger 保持 INFO 级别不变。
  
  - 系统优化的点：
  >控制层和业务层新增的日志输出，主要是为了记录请求信息和方法调用信息，这里只是简单的记录了时间、参数等信息。如果可以的话，也可以对某些方法进行调用时间的统计，根据一个方法花费的时间长短来确定是否需要继续优化。虽然日志记录不能给系统性能带来提升，但是日志记录可以帮助开发人员快速查询到问题、找出系统瓶颈，为后续的系统性能优化提供指导作用。
  
  - 开发流程
  >需求 —> 编码 —> 自测 —> 预发布 —> 测试 —> 回滚 —> 改 Bug —> 发布 —> 发现 Bug —> 改 Bug —>发布……
  
  - 单元测试
  DAO和Service的测试基本一样的，找到applicationContext.xml文件，注解注入一下，然后把相应的组件也注入进来，就可以调用方法了
  ```java
    package service.test;
    
    import com.ssm.demo.entity.Picture;
    import com.ssm.demo.service.PictureService;
    import org.junit.Assert;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.test.context.ContextConfiguration;
    import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
    
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:applicationContext.xml")
    public class PictureServiceTest {
    
        @Autowired
        private PictureService pictureService;
    
        /**
         * pictureService的save()方法单元测试
         */
        @Test
        public void savePictureTest(){
            Picture picture = new Picture();
            picture.setPath("http://localhost:8080/dist/img/logo.jpg");
            picture.setRemark("Service单元测试2");
            //Assert为断言
            Assert.assertTrue(pictureService.save(picture) > 0);
        }
    
    }

  ```
  控制层的代码测试稍有不同
  >MockMvc 实现了对 HTTP 请求的模拟，能够直接在测试代码中实现对 Controller 方法的调用。这样可以使测试速度更快，不依赖网络环境，而且提供了一套验证的工具，可以使请求的验证既统一又方便。
  
  DAO 层代码单元测试主要用来检查和确认 MyBatis 的配置是否生效以及检查 SQL 语句的写法是否正确；
  
  Service 层代码单元测试主要用来检查业务逻辑是否正常，确认事务配置是否正确，以确保数据库事务正确配置以及业务层逻辑的正确实现。
  
  Controller 层代码单元测试主要用来检查请求 URL 配置、参数校验和返回数据是否符合预期，以确保请求正确设置，及接受的参数和返回的数据符合正确的业务逻辑，此外，还可以提升工作效率而不用频繁部署和启动项目。该层的单元测试需要模拟真实环境。
  
  - 数据库的游标
  游标（ResultSet）
  - JDBC
  ![](https://images.gitbook.cn/f91ef300-ad89-11e8-b43a-87ade5650616)
  
  - 数据源的讲究
    - 默认的数据源
    ```xml
    <bean id="dataSource"
          class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url"
                  value="jdbc:mysql://localhost:3306/ssm_demo?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;useSSL=true"/>
        <!-- 改为你的地址即可 -->
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
    ```
    
    默认的数据源是来多少数据库链接请求就产生多少条链接，资源消耗非常厉害
    - 数据库连接池
    
  