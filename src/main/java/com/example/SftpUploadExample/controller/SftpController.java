package com.example.SftpUploadExample.controller;

import com.example.SftpUploadExample.config.SftpConfig;
import com.example.SftpUploadExample.util.SftpUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sftp")
@RequiredArgsConstructor
public class SftpController {

    private final SftpConfig sftpConfig;
    private final SftpUtil sftpUtil;

    @GetMapping("/hello")
    public String hello() {
        return "<h1> SFTP CONFIGURATION </h1>" +
                "<p>Host: " + sftpConfig.getHost() + "</p>" +
                "<p>Username: " + sftpConfig.getUsername() + "</p>" +
                "<p>Password: " + sftpConfig.getPassword() + "</p>";
    }

    @GetMapping("/upload")
    public String uploadFileSftp() {
        try {
            ChannelSftp sftp = sftpUtil.setupSftpConnection();
            sftp.connect();

            String filename = "teste.txt";
            String filePath = "src/main/resources/files/" + filename;
            String remoteDir = "upload/";

            sftp.put(filePath, remoteDir + filename);
            sftp.exit();

            return "Upload do arquivo com sucesso para o diretorio: " + remoteDir + filename;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

}
