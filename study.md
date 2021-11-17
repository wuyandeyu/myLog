我们先来看看SLF4J对Commons Logging的接口有何改进。在Commons Logging中，我们要打印日志，有时候得这么写：

int score = 99;
p.setScore(score);
log.info("Set score " + score + " for Person " + p.getName() + " ok.");
拼字符串是一个非常麻烦的事情，所以SLF4J的日志接口改进成这样了：

int score = 99;
p.setScore(score);
logger.info("Set score {} for Person {} ok.", score, p.getName());
我们靠猜也能猜出来，SLF4J的日志接口传入的是一个带占位符的字符串，用后面的变量自动替换占位符，所以看起来更加自然。


Commons Logging	                         SLF4J
org.apache.commons.logging.Log	         org.slf4j.Logger
org.apache.commons.logging.LogFactory	 org.slf4j.LoggerFactory



不同之处就是Log变成了Logger，LogFactory变成了LoggerFactory。


slf4j的用法就是常年不变的一句"Logger logger = LoggerFactory.getLogger(Object.class);"，可见这里就是通过LoggerFactory去拿slf4j提供的一个Logger接口的具体实现而已，LoggerFactory的getLogger的方法实现为
一直跟到LoggerFactory的bind()方法
getLogger的时候会去classpath下找STATIC_LOGGER_BINDER_PATH，STATIC_LOGGER_BINDER_PATH值

```
即所有slf4j的实现，在提供的jar包路径下，一定是有"org/slf4j/impl/StaticLoggerBinder.class"存在的
我们不能避免在系统中同时引入多个slf4j的实现，所以接收的地方是一个Set。大家应该注意到，上部分在演示同时引入logback、slf4j-simple、log4j的时候会有警告
```



使用SLF4J和Logback和前面讲到的使用Commons Logging加Log4j是类似的，先分别下载SLF4J和Logback，然后把以下jar包放到classpath下：

logback-core：其它两个模块的基础模块
logback-classic：它是log4j的一个改良版本，同时它完整实现了slf4j API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging
logback-access：访问模块与Servlet容器集成提供通过Http来访问日志的功能

slf4j-api-1.7.x.jar
logback-classic-1.2.x.jar
logback-core-1.2.x.jar

如果配置文件 logback-test.xml 和 logback.xml 都不存在，那么 logback 默认地会调用BasicConfigurator ，创建一个最小化配置。最小化配置由一个关联到根 logger 的ConsoleAppender 组成。输出用模式为%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n 的 PatternLayoutEncoder 进行格式化。root logger 默认级别是 DEBUG
尝试在 classpath下查找文件logback-test.xml；
如果文件不存在，则查找文件logback.xml；
如果两个文件都不存在，logback用BasicConfigurator自动对自己进行配置，这会导致记录输出到控制台。
(1) 根节点<configuration>，包含下面三个属性：
scan: 当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod: 设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug: 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。


需要配置输出源appender，打日志的loger（子节点）和root（根节点），实际上，它输出日志是从子节点开始，子节点如果有输出源直接输入，如果无，判断配置的addtivity，是否像上级传递，即是否向root传递，传递则采用root的输出源，否则不输出日志。
以<configuration>开头，后面有零个或多个<appender>元素，有零个或多个<logger>元素，有最多一个<root>元素。