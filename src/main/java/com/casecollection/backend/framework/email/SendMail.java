package com.casecollection.backend.framework.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {  
    private static String username = "379041911@qq.com";  
    private static String password = "mwvcahfdtdywbhch";  
    private static String smtpServer = "smtp.qq.com";//邮件协议  
    private static String fromMailAddress = "379041911@qq.com";  
//    private static String toMailAddress = "1125280065@qq.com";  
    private static String toMailAddress = "379041911@qq.com";  
  
    public static void main(String[] args) throws Exception {  
        Properties props = new Properties();  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.host", smtpServer);     

        // 获得邮件会话对象  
        Session session = Session.getDefaultInstance(props,  
                new SmtpAuthenticator(username, password));  
        /** *************************************************** */  
        // 创建MIME邮件对象  
        MimeMessage mimeMessage = new MimeMessage(session);  
        mimeMessage.setFrom(new InternetAddress(fromMailAddress));// 发件人  
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(  
                toMailAddress));// 收件人  
        mimeMessage.setSubject("主题");  
        mimeMessage.setSentDate(new Date());// 发送日期  
        BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象  
        mdp.setContent("测试java邮件发送", "text/html;charset= GB2312");// 设置发送邮件内容为HTML类型,并为中文编码  
        Multipart mm = new MimeMultipart();  
        mm.addBodyPart(mdp);  
        mimeMessage.setContent(mm);  
        mimeMessage.saveChanges();  
        Transport.send(mimeMessage);// 发送邮件  
  
    }  
  
}  
/** 
 * Smtp认证 
 */  
class SmtpAuthenticator extends Authenticator {  
    String username = null;  
    String password = null;  
  
    // SMTP身份验证  
    public SmtpAuthenticator(String username, String password) {  
        this.username = username;  
        this.password = password;  
    }  
  
    public PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(this.username, this.password);  
    }  
  
} 
