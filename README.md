# SpringBoot



快速搭建。企业级基于spring的一个能够“快速启动”的应用。



## 一、SpringBoot中的常规配置

### 1. 入口类和相关注解

```java
package com.jump.zhu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//启动类   IOC的核心代码    本质是ApplicationContext 
@SpringBootApplication
public class BootApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
```

```java
//四个元注解
//注解可以写在哪些地方
@Target({ElementType.TYPE})
//注解的作用域
@Retention(RetentionPolicy.RUNTIME)
//该注解会被API抽取
@Documented
//可继承
@Inherited

//本质上就是一个Configuration注解
@SpringBootConfiguration
//自动装配的注解
@EnableAutoConfiguration

//自动扫描
@ComponentScan(
    //不加入IOC的类  与之对应的有includeFilters 代表加入IOC的类
    excludeFilters = {@Filter(
        //ANNOTATION：注解类型
        //ASSIGNABLE_TYPE：ANNOTATION：指定的类型
        //ASPECTJ：按照Aspectj的表达式，基本上不会用到
        //REGEX：按照正则表达式
        //CUSTOM：自定义规则     自定义规则的filter类要实现TypeFilter接口的match方法   
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
```



### 2.banner

http://patorjk.com/software/taag/

在resources目录下创建一个`banner.txt`文件

#### 关闭banner

```java
//设置初始化类
SpringApplication springApplication = new SpringApplication(BootApplication.class);
//关闭banner
springApplication.setBannerMode(Banner.Mode.OFF);
//启动Spring
springApplication.run(args);
```



### 3、常规配置

在SpringBoot中给我们提供的有两个配置文件` applicationContext.properties`,`applicationContext.yml` 作用是一样的，一个项目中只需要有一个就好。

#### 3.1 Tomcat配置修改

##### 定义

```properties
#设置端口口
server.port=80
#设置url
server.servlet.context-path=/spring
#设置编码格式  需要同步设置项目编码格式
server.tomcat.uri-encoding=UTF-8
#  以前是spring.http.encoding  
server.servlet.encoding.charset=UTF-8
server.servlet.encodin.enabled=true
server.servlet.encodin.force=true

spring.messages.encoding=UTF-8
```

#### 3.2 自定义的属性

##### 定义

```properties
user.name=bobo
user.age=18
user.addr=山西
```

##### 使用1

```java
    @Value("${user.name}")
    private String userName;
    @Value("${user.age}")
    private Integer age;
    @Value("${user.addr}")
    private String address;
```

##### 使用2

```java
package com.jump.zhu.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "user")
public class User {

    private String userName;

    private Integer age;

    private String addr;

}
```

```java
    @Autowired
    private User user;
```



### 4.logback日志

SpringBoot集成了logback

#### 4.1 直接配置

```properties
#logback的配置
#以前叫  logging.file
logging.file.name=d:/log.log
logging.level.org.springframework.web=DEBUG
```

#### 4.2 通过xml配置

在resources文件下创建  `logback.xml` 文件



### 5.profiles配置

开发跟测试以及生产环境配置不一致时使用。

```properties
spring.profiles.active=prod
```



## 二、 静态页面配置

### 1.本身自带

src\main\resources\static\index.html

src\main\webapp\index.html

### 2. properties 、yml配置

```properties
spring.webflux.static-path-pattern=/**
# 覆盖掉默认的配置 自己手动加上静态资源的路径   引入的第三方jar包
## 以前是这个 spring.resources.static-locations=classpath:/META-INF/resources
spring.web.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/custom/

```



### 3.config类配置

```java
@Configuration
public class FileWebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/jump/");
        super.addResourceHandlers(registry);
    }
}
```



## 三、自动装配

### starter



| 名称                                   | 描述                                                         |
| :------------------------------------- | :----------------------------------------------------------- |
| spring-boot-starter-thymeleaf          | 使MVC Web applications 支持Thymeleaf                         |
| spring-boot-starter-data-couchbase     | 使用Couchbase 文件存储数据库、Spring Data Couchbase          |
| spring-boot-starter-artemis            | 为JMS messaging使用Apache Artemis                            |
| spring-boot-starter-web-services       | 使用Spring Web Services                                      |
| spring-boot-starter-mail               | Java Mail、Spring email为邮件发送工具                        |
| spring-boot-starter-data-redis         | 通过Spring Data Redis 、Jedis client使用Redis键值存储数据库  |
| spring-boot-starter-web                | 构建Web，包含RESTful风格框架SpringMVC和默认的嵌入式容器Tomcat |
| spring-boot-starter-activemq           | 为JMS使用Apache ActiveMQ                                     |
| spring-boot-starter-data-elasticsearch | 使用Elasticsearch、analytics engine、Spring Data Elasticsearch |
| spring-boot-starter-integration        | 使用Spring Integration                                       |
| spring-boot-starter-test               | 测试 Spring Boot applications包含JUnit、 Hamcrest、Mockito   |
| spring-boot-starter-jdbc               | 通过 Tomcat JDBC 连接池使用JDBC                              |
| spring-boot-starter-mobile             | 通过Spring Mobile构建Web应用                                 |
| spring-boot-starter-validation         | 通过Hibernate Validator使用 Java Bean Validation             |
| spring-boot-starter-hateoas            | 使用Spring MVC、Spring HATEOAS构建 hypermedia-based RESTful Web 应用 |
| spring-boot-starter-jersey             | 通过 JAX-RS、Jersey构建 RESTful web applications；spring-boot-starter-web的另一替代方案 |
| spring-boot-starter-data-neo4j         | 使用Neo4j图形数据库、Spring Data Neo4j                       |
| spring-boot-starter-websocket          | 使用Spring WebSocket构建 WebSocket 应用                      |
| spring-boot-starter-aop                | 通过Spring AOP、AspectJ面向切面编程                          |
| spring-boot-starter-amqp               | 使用Spring AMQP、Rabbit MQ                                   |
| spring-boot-starter-data-cassandra     | 使用Cassandra分布式数据库、Spring Data Cassandra             |
| spring-boot-starter-social-facebook    | 使用 Spring Social Facebook                                  |
| spring-boot-starter-jta-atomikos       | 为 JTA 使用 Atomikos                                         |
| spring-boot-starter-security           | 使用 Spring Security                                         |
| spring-boot-starter-mustache           | 使MVC Web applications 支持Mustache                          |
| spring-boot-starter-data-jpa           | 通过 Hibernate 使用 Spring Data JPA （Spring-data-jpa依赖于Hibernate） |
| spring-boot-starter                    | Core starter,包括 自动配置支持、 logging and YAML            |
| spring-boot-starter-groovy-templates   | 使MVC Web applications 支持Groovy Templates                  |
| spring-boot-starter-freemarker         | 使MVC Web applications 支持 FreeMarker                       |
| spring-boot-starter-batch              | 使用Spring Batch                                             |
| spring-boot-starter-social-linkedin    | 使用Spring Social LinkedIn                                   |
| spring-boot-starter-cache              | 使用 Spring caching 支持                                     |
| spring-boot-starter-data-solr          | 通过 Spring Data Solr 使用 Apache Solr                       |
| spring-boot-starter-data-mongodb       | 使用 MongoDB 文件存储数据库、Spring Data MongoDB             |
| spring-boot-starter-jooq               | 使用JOOQ链接SQL数据库；spring-boot-starter-data-jpa、spring-boot-starter-jdbc的另一替代方案 |
| spring-boot-starter-jta-narayana       | Spring Boot Narayana JTA Starter                             |
| spring-boot-starter-cloud-connectors   | 用连接简化的 Spring Cloud 连接器进行云服务就像Cloud Foundry、Heroku那样 |
| spring-boot-starter-jta-bitronix       | 为JTA transactions 使用 Bitronix                             |
| spring-boot-starter-social-twitter     | 使用 Spring Social Twitter                                   |
| spring-boot-starter-data-rest          | 使用Spring Data REST 以 REST 方式暴露 Spring Data repositories |
| spring-boot-starter-actuator           | 使用Spring Boot Actuator 的 production-ready 功能来帮助你监视和管理应用 |
| spring-boot-starter-undertow           | 使用 Undertow 作为嵌入式服务容器；spring-boot-starter-tomcat的另一替代方案 |
| spring-boot-starter-jetty              | 使用 Jetty 作为嵌入式服务容器；spring-boot-starter-tomcat的另一替代方案 |
| spring-boot-starter-logging            | 为 logging 使用Logback.默认 logging starter                  |
| spring-boot-starter-tomcat             | 使用 Tomcat 作为嵌入式服务容器；作为默认嵌入式服务容器被spring-boot-starter-web使用 |
| spring-boot-starter-log4j2             | 使用Log4j2记录日志；spring-boot-starter-logging的另一替代方案 |

### @Import

在Spring中我们将类型交给SpringIOC管理的方式有哪些？

> 1.基于xml配置文件<bean>
>
> 2.基于xml配置文件@Component
>
> 3.基于Java配置类【@Configuration】 @Bean
>
> 4.基于Java配置类+@ComponentScan+@Component
>
> 5.FactoryBean接口【getObject()】
>
> 6.@Import注解

 

### @EnableAutoConfiguration

  



## 四、SpringBoot集成

### 1、SpringBoot整合servlet

#### 1.1、第一种方式

##### @ServletComponentScan+@WebServlet注解

在springboot启动的时候加载WebServlet

```java
@WebServlet(name="FirstServlet",urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("First running...");
        PrintWriter out = resp.getWriter();
        out.write("first FirstServlet");
        out.flush();
        out.close();
    }
}
```

```java
@SpringBootApplication
@ServletComponentScan
public class BootApplication {
    public static void main(String[] args) {
        //ApplicationContext run = SpringApplication.run(BootApplication.class, args);
        SpringApplication springApplication = new SpringApplication(BootApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
```

#### 1.2、第二种方式

##### 自己写一个ServletRegistrationBean

```java
    @Bean
    public ServletRegistrationBean getRegistrationBean(){
        //将要添加的servlet封装成一个ServletRegistrationBean对象
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new FirstServlet());
        //添加映射地址
        servletRegistrationBean.addUrlMappings("/second");
        return servletRegistrationBean;
    }
```

### 2、SpringBoot整合Filter

#### 2.1第一种方式

##### 使用@WebFilter(urlPatterns = "/first")注解

```java
@WebFilter(urlPatterns = "/first")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter befor....");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("doFilter after....");
    }

    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
```

#### 2.2第二种方式





### 3、SpringBoot整合Lister

#### 3.1第一种方式



#### 3.2第二种方式



### 4.SpringBoot如何实现文件上传下载

#### 4.1文件上传

##### 1.配置application.properties

```properties
#设置上传
spring.servlet.multipart.enabled=true
#单个文件上传大小
spring.servlet.multipart.max-file-size=20MB
#设置一次上传文件的大小
spring.servlet.multipart.max-request-size=200MB
```

##### 2.写Html文件，Controller文件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
</head>
<body>
<h1>上传</h1>
<form name="aa" action="/spring/upload" method="post" enctype="multipart/form-data">
    <label>账号：</label><input type="text" name="username"><br>
    <label>照片：</label><input type="file" name="upload"><br>
    <input type="submit" value="提交">
</form>
</body>

</html>
```



```java
@RestController
public class FileController {
    @RequestMapping("/upload")
    public String upload(MultipartFile upload,String username) throws IOException {
        System.out.println("username :  "+username);
        upload.transferTo(new File("D:/",upload.getOriginalFilename()));
        System.out.println("fileName :  "+upload.getOriginalFilename());
        return "success";
    }
}
```



#### 4.2文件下载



##### 1.写Html文件，Controller文件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件下载</title>
</head>
<body>
<h1>下载</h1>
<form name="aa" action="/spring/download" method="post" enctype="multipart/form-data">
    <input type="submit" value="下载">
</form>
</body>
</html>
```



```java
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        File file = new File("E:\\download\\chrome\\picture\\old\\80845930_p0.jpg");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName=1.jpg");
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = in.read(b)) > 0){
                out.write(b,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```

### 5.SpringBoot整合前端模板框架

**使用String返回视图时，只能使用@Controller不能使用@RestController**

#### 1.整合freemarker

##### 1.添加依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
```

##### 2.添加配置

```properties
spring.freemarker.suffix=.ftl
```

##### 3.书写controller,bean

``` java
package com.jump.zhu.controller;

import com.jump.zhu.bean.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @RequestMapping("/showUser")
    public String showUser(Model model){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"zhangsan",22));
        list.add(new User(2,"lisi",23));
        list.add(new User(3,"wangwu",24));
        model.addAttribute("list",list);
        return "user";
    }
}
```

``` java
package com.jump.zhu.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
@Data
//@ConfigurationProperties(prefix = "user")
public class User {

    private Integer id;

    private String userName;

    private Integer age;

    private String addr;

    public User() {
    }

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}

```

##### 4.模板页面 user.ftl

``` html
<html>
<head>
    <title>用户信息</title>
    <meta charset="UTF-8">
</head>
<body>
<table border="1" align="center" width="50%">
    <tr>
        <th>ID</th>
        <th>名字</th>
        <th>年龄</th>
    </tr>
    <#list list as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.userName}</th>
            <th>${user.age}</th>
        </tr>
    </#list>
</table>
</body>
</html>
```

#### 2.整合Thymeleaf

##### 1、添加依赖

``` xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```



##### 2.书写controller

```java
    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("msg","早上好！！！！");
        return "show";
    }
```



##### 3.模板页面

``` html
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Thymeleaf整合</h1>
    <br>
    <!--/*@thymesVar id="msg" type="jump"*/-->
    <span th:text="${msg}"></span>
</body>
</html>
```

### 6.SpringBoot实现热部署

##### 1.修改setting

![idea热部署设置](image\idea热部署设置1.png)

##### 2.ctrl+shift+alt+/

![idea热部署设置](image\idea热部署设置2.png)

勾选

![idea热部署设置](image\idea热部署设置3.png)

##### 3.添加spring-boot-devtools

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
```

##### 4.修改build节点

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

### 7.SpringBoot异常处理

#### 7.1 默认错误页面

SpringBoot默认的处理异常机制：SpringBoot默认的已经提供了一套处理机制。一旦程序中出现异常SpringBoot会向/error的url发出请求。在SpringBoot中提供了一个叫BasicExeceptionController来处理/error请求，然后跳转默认显示异常的页面来展示异常信息。



#### 7.2 局部异常              

##### @ExceptionHandler注解       

优先级  1

 ```java
    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointerExceptionHandler(Exception e){
        ModelAndView view = new ModelAndView();
        view.addObject("error",e.toString());
        view.setViewName("error1");
        return view;
    }
 ```

#### 7.3 全局异常

##### 7.3.1 @ControllerAdvice注解+@ExceptionHandler注解

优先级  2

```java
package com.jump.zhu.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHander {

    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointerExceptionHandler(Exception e){
        ModelAndView view = new ModelAndView();
        view.addObject("error",e.toString());
        view.setViewName("error1");
        return view;
    }
}

```

##### 7.3.2 SimpleMappingExceptionResolver处理

优先级  4



```java
    /**
    * 异常处理 异常和处理地址的映射
    */
	@Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver mapping = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("java.lang.ArithmeticException","error2");
        mappings.s
        mapping.setExceptionMappings(mappings);
        return mapping;

    }
```

##### 7.3.3 自定义全局异常 实现 HandlerExceptionResolver

优先级  3

```java
@Component
public class MyHanderExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("全局的自定义异常处理触发了。。。。");
        ModelAndView mv = null;
        //view.
        if(e instanceof  NullPointerException){
            mv = new ModelAndView();
            mv.setViewName("error1");
            mv.addObject("error",e.toString());
        }
        return mv;
    }
}
```

### 8. SpringBoot单元测试

#### 8.1引入依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
```

#### 8.2 @SpringBootTest注解

```java
@SpringBootTest
class BootApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.query());
    }

}
```



### 9. SpringBoot整合MyBatists

#### 9.1 基础

##### 9.1.1  引入pom依赖

```xml
        <!--mysql -->
		<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.19</version>
        </dependency>
        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>

```

##### 9.1.2 修改配置文件

```properties
#数据库配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.32.209:3306
spring.datasource.username=mybatis
spring.datasource.password=mybatis
#连接池
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
#mabatis的别名
mybatis.type-aliases-package=com.jump.zhu.pojo
#mybatis的映射文件
mybatis.mapper-locations=classpath:mapper/*.xml

```

##### 9.1.3 建表，创建pojo

```sql
create table users(
    id int(11) not null AUTO_INCREMENT,
    name varchar(255) default null,
    age int(11) default null,primary key (id)
) engine=InnoDB default CHARSET = utf8;

insert into users values(null,'ruru',18);
```

```java
@Data
public class User {

    private Integer id;
    private String name;
    private Integer age;

}
```



##### 9.1.4 建mapper数据库映射文件

@Mapper

@Repository

@MapperScan("com.jump.zhu.mapper")

```java
//防止引入报错
@Repository
//使mapper加载到boot容器中   或者在 boot中加入@MapperScan("com.jump.zhu.mapper")
@Mapper
public interface UserMapper {
    public List<User> query();
}

```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org/DTD Mapper 3..0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jump.zhu.mapper.UserMapper" >
    <select id="query" resultType="User">
        select * from users
    </select>
</mapper>
```



##### 9.1.5 建service controller

```java
public interface UserService {
    List<User> query();
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> query() {
        return mapper.query();
    }
}
```

```java
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/query")
    public List<User> query(){
        return userService.query();
    }
}
```

#### 9.2 查询

##### 9.2.1  建controller，mapper，service 见9.1

```java
    @RequestMapping("/user/query1")
    public String query1(Model model){
        model.addAttribute("list",userService.query());
        return "user1";
    }
```



##### 9.2.2  建html 参考 5.2

```html
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>用户信息</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>用户管理</h1>
<table border="1" align="center" width="50%">
    <tr>
        <th>ID</th>
        <th>名字</th>
        <th>年龄</th>
    </tr>
    <!--/*@thymesVar id="list" type="java.util.List"*/-->
    <tr th:each="user:${list}">
        <!--/*@thymesVar id="id" type="java.lang.String"*/-->
        <th th:text="${user.id}"></th>
        <!--/*@thymesVar id="userName" type="java.lang.String"*/-->
        <th th:text="${user.name}"></th>
        <!--/*@thymesVar id="age" type="java.lang.String"*/-->
        <th th:text="${user.age}"></th>
    </tr>
</table>
</body>
</html>
```

#### 9.3 新增

##### 9.3.1  建controller，mapper，service 见9.1

service 省略

mapper

```xml
    <insert id="addUser" parameterType="User">
        INSERT INTO users(name,age) values (#{name},#{age})
    </insert>
```

```java
@Repository
@Mapper
public interface UserMapper {
    public List<User> query();
    public Integer addUser(User user);
}

```

controller

```java
    @RequestMapping("/user/add")
    public String add(User user){
        userService.addUser(user);
        return "redirect:/user/query1";
    }

    @RequestMapping("/user/{page}")
    public String toAdd(@PathVariable String page){
        return page;
    }
```

##### 9.3.2  建html 参考 5.2

```html
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>用户信息</title>
</head>
<body>
<h1>添加用户</h1>
<form th:action="@{/user/add}" method="post">
<table border="1" align="center" width="50%">
    <tr>
        <th>ID</th>
        <th>名字</th>
        <th>年龄</th>
    </tr>
    <!--/*@thymesVar id="list" type="java.util.List"*/-->
    <tr >
        <th></th>
        <th><input type="text" name="name"></th>
        <th><input type="text" name="age"></th>
    </tr>
</table>
    <input type="submit" value="提交">
</form>
</body>
</html>
```

#### 9.4 修改

##### 9.4.1 建controller，mapper，service 见9.1

controller

```java
    @RequestMapping("/user/updateInfo")
    public String updateInfo(Integer id,Model model){
        model.addAttribute("user",userService.queryById(id));
        return "updateUser";
    }

    @RequestMapping("/user/update")
    public String update(User user){
        userService.updateUser(user);
        return "redirect:/user/query1";
    }
```

service 省略

mapper

```xml
    <select id="queryById" resultType="User">
        select * from users where id = #{id};
    </select>

    <update id="update" parameterType="User">
        update users set name = #{name},age=#{age} where id = #{id}
    </update>
```



##### 9.4.2 HTML

```html
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>用户信息</title>
    </head>
<body>
<h1>添加用户</h1>
<form th:action="@{/user/update}" method="post">
    <table border="1" align="center" width="50%">
        <tr>
            <th>ID</th>
            <th>名字</th>
            <th>年龄</th>
        </tr>
        <!--/*@thymesVar id="list" type="java.util.List"*/-->
        <tr >
            <input type="hidden" name="id" th:value="${user.id}">
            <th th:text="${user.id}"></th>
            <th><input type="text" name="name" th:value="${user.name}"></th>
            <th><input type="text" name="age" th:value="${user.age}"></th>
        </tr>
    </table>
    <input type="submit" value="提交">
</form>
</body>
</html>
```

#### 9.5 删除

##### 9.5.1 建controller，mapper，service 见9.1

controller

```java
    @RequestMapping("/user/delete")
    public String delete(Integer id){
        userService.deleteUser(id);
        return "redirect:/user/query1";
    }
```

service 省略

mapper

```xml
    <delete id="deleteUser" parameterType="Integer">
        delete from users where id = #{id}
    </delete>
```

### 10 SpringBoot整合Shiro



