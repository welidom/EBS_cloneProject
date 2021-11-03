package com.kgitbank.ebs.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.kgitbank.ebs.utils.MailUtils;

@Service	
public class MailService {
	@Inject
	private JavaMailSenderImpl mailSender;
    private String getAuthKey() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < 6) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    public String sendAuthMail(String email) {
        String authKey = getAuthKey();

        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("온라인 클론스 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
            .append("<p>아래 주소를 누르면 인증이 완료됨니다</p>")
            .append("<a href='http://www.eocs.kro.kr/check?email=")
            .append(email)
            .append("&authKey=")
            .append(authKey)
            .append("' target='_blenk'>이메일 인증 확인</a>")
            .toString());
            sendMail.setFrom("EBSOnlineClones@gmail.com", "EBS온라인클래스");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
          return authKey;
    }
}
