package com.example.email_module.email_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.sender.protocol}")
    private String protocol;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);


        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.setProperty("mail.transport.protocol",protocol);
        javaMailProperties.setProperty("mail.debug",debug);

        return javaMailSender;
    }


}
