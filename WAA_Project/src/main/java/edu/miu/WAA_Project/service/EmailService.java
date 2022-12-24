package edu.miu.WAA_Project.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void send(String to, String subject, String body);
    void sendWithHTMLBody(String to, String subject, String body);
}
