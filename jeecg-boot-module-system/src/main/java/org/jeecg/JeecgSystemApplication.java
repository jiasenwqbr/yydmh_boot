package org.jeecg;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import sun.security.util.SecurityConstants;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* 单体启动类（采用此类启动为单体模式）
*/
@Slf4j
@SpringBootApplication
public class JeecgSystemApplication extends SpringBootServletInitializer {
//    @Value("${server.port}")
//    private int sslPort;//https的端口
//
//    @Value("${server.http-port}")
//    private int httpPort;//http的端口

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JeecgSystemApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(JeecgSystemApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "应用 快速开发平台 已经启动! 访问地址:\n\t" +
                "本地: \t\thttp://localhost:" + port + path + "/\n\t" +
                "远程: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "----------------------------------------------------------");

    }
//    @Bean
//    public TomcatServletWebServerFactory servletWebServerFactory(){
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//
//                //设置安全性约束
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                //设置约束条件
//                SecurityCollection collection = new SecurityCollection();
//                //拦截所有请求
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(connector());
//
//        return tomcat;
//    }

//    @Bean
//    public  Connector connector(){
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        //设置将分配给通过此连接器接收到的请求的方案
//        connector.setScheme("http");
//
//        //true： http使用http, https使用https;
//        //false： http重定向到https;
//        connector.setSecure(false);
//
//        //设置监听请求的端口号，这个端口不能其他已经在使用的端口重复，否则会报错
//        connector.setPort(httpPort);
//
//        //重定向端口号(非SSL到SSL)
//        connector.setRedirectPort(sslPort);
//
//       return connector;
//    }


}