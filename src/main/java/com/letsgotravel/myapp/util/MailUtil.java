package com.letsgotravel.myapp.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.letsgotravel.myapp.domain.MemberVo;

@Component
public class MailUtil {
	
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MemberVo mv, String tempPassword) throws MessagingException {
        String subject = "LecoTrip 임시 비밀번호 발급 안내";
        String msg = "<div align='left'>";
        msg += "<h3>" + mv.getId() + "님의 임시 비밀번호입니다.<br>마이페이지에서 비밀번호를 반드시 변경해 주세요.</h3>";
        msg += "<p>임시 비밀번호 : <strong>" + tempPassword + "</strong></p>";
        msg += "</div>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom("lecotripfindpw@gmail.com");
        helper.setTo(mv.getEmail());
        helper.setSubject(subject);
        helper.setText(msg, true); // HTML 형식

        mailSender.send(mimeMessage);
    }
}