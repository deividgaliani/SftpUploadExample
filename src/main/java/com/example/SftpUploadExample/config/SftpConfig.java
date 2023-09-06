package com.example.SftpUploadExample.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@ConfigurationProperties(prefix = "sftp")
@PropertySource("classpath:application.properties")
public class SftpConfig {

    @Value("sftp.host")
    private String host;

    @Value("sftp.username")
    private String username;

    @Value("sftp.password")
    private String password;

}
