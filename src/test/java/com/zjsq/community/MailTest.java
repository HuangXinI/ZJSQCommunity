package com.zjsq.community;

import com.zjsq.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest//测试
@ContextConfiguration(classes = CommunityApplication.class)//设置，以这个类为配置类
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;//

    //测试发邮件
    @Test
    public void testMail(){
        mailClient.sendMail("879754473@qq.com", "邮件发送功能测试", "哈哈哈");
    }

    //测试利用thymeleaf模板引擎发送html邮件
    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username", "sunday");
        //模板引擎自动装配
        String content = templateEngine.process("/mail/demo", context);//生成动态网页（模板，内容）
        System.out.println(content);

        mailClient.sendMail("879754473@qq.com", "HTML", content);
    }
}
