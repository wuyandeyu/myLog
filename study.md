������������SLF4J��Commons Logging�Ľӿ��кθĽ�����Commons Logging�У�����Ҫ��ӡ��־����ʱ�����ôд��

int score = 99;
p.setScore(score);
log.info("Set score " + score + " for Person " + p.getName() + " ok.");
ƴ�ַ�����һ���ǳ��鷳�����飬����SLF4J����־�ӿڸĽ��������ˣ�

int score = 99;
p.setScore(score);
logger.info("Set score {} for Person {} ok.", score, p.getName());
���ǿ���Ҳ�ܲ³�����SLF4J����־�ӿڴ������һ����ռλ�����ַ������ú���ı����Զ��滻ռλ�������Կ�����������Ȼ��


Commons Logging	                         SLF4J
org.apache.commons.logging.Log	         org.slf4j.Logger
org.apache.commons.logging.LogFactory	 org.slf4j.LoggerFactory



��֮ͬ������Log�����Logger��LogFactory�����LoggerFactory��


slf4j���÷����ǳ��겻���һ��"Logger logger = LoggerFactory.getLogger(Object.class);"���ɼ��������ͨ��LoggerFactoryȥ��slf4j�ṩ��һ��Logger�ӿڵľ���ʵ�ֶ��ѣ�LoggerFactory��getLogger�ķ���ʵ��Ϊ
һֱ����LoggerFactory��bind()����
getLogger��ʱ���ȥclasspath����STATIC_LOGGER_BINDER_PATH��STATIC_LOGGER_BINDER_PATHֵ

```
������slf4j��ʵ�֣����ṩ��jar��·���£�һ������"org/slf4j/impl/StaticLoggerBinder.class"���ڵ�
���ǲ��ܱ�����ϵͳ��ͬʱ������slf4j��ʵ�֣����Խ��յĵط���һ��Set�����Ӧ��ע�⵽���ϲ�������ʾͬʱ����logback��slf4j-simple��log4j��ʱ����о���
```



ʹ��SLF4J��Logback��ǰ�潲����ʹ��Commons Logging��Log4j�����Ƶģ��ȷֱ�����SLF4J��Logback��Ȼ�������jar���ŵ�classpath�£�

logback-core����������ģ��Ļ���ģ��
logback-classic������log4j��һ�������汾��ͬʱ������ʵ����slf4j APIʹ����Ժܷ���ظ�����������־ϵͳ��log4j��JDK14 Logging
logback-access������ģ����Servlet���������ṩͨ��Http��������־�Ĺ���

slf4j-api-1.7.x.jar
logback-classic-1.2.x.jar
logback-core-1.2.x.jar

��������ļ� logback-test.xml �� logback.xml �������ڣ���ô logback Ĭ�ϵػ����BasicConfigurator ������һ����С�����á���С��������һ���������� logger ��ConsoleAppender ��ɡ������ģʽΪ%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n �� PatternLayoutEncoder ���и�ʽ����root logger Ĭ�ϼ����� DEBUG
������ classpath�²����ļ�logback-test.xml��
����ļ������ڣ�������ļ�logback.xml��
��������ļ��������ڣ�logback��BasicConfigurator�Զ����Լ��������ã���ᵼ�¼�¼���������̨��
(1) ���ڵ�<configuration>�����������������ԣ�
scan: ������������Ϊtrueʱ�������ļ���������ı䣬���ᱻ���¼��أ�Ĭ��ֵΪtrue��
scanPeriod: ���ü�������ļ��Ƿ����޸ĵ�ʱ���������û�и���ʱ�䵥λ��Ĭ�ϵ�λ�Ǻ��롣��scanΪtrueʱ����������Ч��Ĭ�ϵ�ʱ����Ϊ1���ӡ�
debug: ������������Ϊtrueʱ������ӡ��logback�ڲ���־��Ϣ��ʵʱ�鿴logback����״̬��Ĭ��ֵΪfalse��


��Ҫ�������Դappender������־��loger���ӽڵ㣩��root�����ڵ㣩��ʵ���ϣ��������־�Ǵ��ӽڵ㿪ʼ���ӽڵ���������Դֱ�����룬����ޣ��ж����õ�addtivity���Ƿ����ϼ����ݣ����Ƿ���root���ݣ����������root�����Դ�����������־��
��<configuration>��ͷ���������������<appender>Ԫ�أ����������<logger>Ԫ�أ������һ��<root>Ԫ�ء�