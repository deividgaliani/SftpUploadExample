package com.example.SftpUploadExample.util;

import com.example.SftpUploadExample.config.SftpConfig;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SftpUtil {

    private final SftpConfig sftpConfig;

    public ChannelSftp setupSftpConnection() throws JSchException {
        JSch jSch = new JSch();
//        jSch.setKnownHosts("/Users/deivi/.ssh/known_hosts");
        jSch.setConfig("StrictHostKeyChecking", "no");
        Session jschSession = jSch.getSession(sftpConfig.getUsername(), sftpConfig.getHost());
        jschSession.setPassword(sftpConfig.getPassword());
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }
}



