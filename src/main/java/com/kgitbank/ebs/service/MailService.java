package com.kgitbank.ebs.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
            sendMail.setFrom("EBSOnlineClones@gmail.com", "EBS온라인클론스");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
          return authKey;
    }
    public void sendId(String email, List<String> ids) {
    	try {
        	MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("온라인 클론스 아이디 찾기 ");
	    	StringBuffer txt = new StringBuffer().append("<h1>[아이디입니다]</h1>")
							.append("<p>아래의 항목들이 이 이메일로 가입되어있는 회원들입니다.<ul>");
	    	for(String id: ids) {
	    		txt.append("<li>"+id+"</li>");
	    	}
	    	txt.append("</ul></p>");
	    	sendMail.setText(txt.toString());
	    	sendMail.setFrom("EBSOnlineClones@gmail.com", "EBS온라인클론스");
            sendMail.setTo(email);
            sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void sendPw(String email, String pw) {
    	try {
        	MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("온라인 클론스 비밀번호 찾기 ");
	    	sendMail.setText(new StringBuffer().append("<h1>[비밀번호입니다]</h1>")
					.append("<p>발송된 비밀번호</p><br>")
					.append(pw)
					.toString());
	    	sendMail.setFrom("EBSOnlineClones@gmail.com", "EBS온라인클론스");
            sendMail.setTo(email);
            sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
