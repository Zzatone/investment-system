package util;

import bean.Email;
import dao.EmailDao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail {

    private final static  String SERVICE_HOST = "smtp.163.com";//QQ服务器

    private final static  String PORT = "25"; //smtp的端口号

    private final static  String ACCOUNT = "w00x02h11@163.com"; //发送邮件的QQ账号

    private final static  String AUTH_CODE = "PUMABPMYABZBWFEX";

    public boolean SendEmail_Update() throws UnsupportedEncodingException, MessagingException {
        ArrayList<Email> emails=new EmailDao().selectAllEmail_ifexist();
        Properties properties=new Properties();
        properties.setProperty("mail.smtp.host",SERVICE_HOST);
        properties.setProperty("mail.smtp.port",PORT);
        properties.setProperty("mail.smtp.socketFactory.port",PORT);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ACCOUNT,AUTH_CODE);
            }
        });
        session.setDebug(true);

        try {
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(ACCOUNT));
            for(Email email:emails){
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmail(),email.getName()));
            }
            message.setSubject("数据更新","UTF-8");
            message.setText("数据更新","UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
            return true;
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
        return false;
    }

    public boolean SendEmail_Upgrade(Email email,String title,String respone) throws UnsupportedEncodingException, MessagingException {
        Properties properties=new Properties();
        properties.setProperty("mail.smtp.host",SERVICE_HOST);
        properties.setProperty("mail.smtp.port",PORT);
        properties.setProperty("mail.smtp.socketFactory.port",PORT);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");
        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ACCOUNT,AUTH_CODE);
            }
        });
        session.setDebug(true);

        try {
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(ACCOUNT));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getEmail(),email.getName()));
            message.setSubject(title,"UTF-8");
            message.setText(respone,"UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport.send(message);
            return true;
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
        return false;
    }
}
