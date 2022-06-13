package com.zjsq.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component//通用bean
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender mailSender;//忽略报错,可以正常运行

    @Value("${spring.mail.username}")//给变量赋值,这里是发件人
    private String from;

    /**
     *发邮件
     * @param to 收件人
     * @param subject 标题
     * @param content 内容
     */
    public void sendMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        //Spring可以自动帮助构建MimeMessage
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);//支持html文本
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            //发生异常就写入日志
            logger.error("发送邮件失败" + e.getMessage());
        }
    }
}
