/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Kary Johnson
 */
public class GmailUtil {
    public static void sendMail(String sendTo, String sendFrom, String subject,
            String body, boolean bodyIsHTML) throws MessagingException{
        
        // Used to get mail session (Gmail)
        Properties props =  new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // Used to create message
        Message message =  new MimeMessage(session);
        message.setSubject(subject);
        message.setText(body);
        
        // Used to set the address for the message        
        Address fromAddress = new InternetAddress(sendFrom);
        Address toAddress   = new InternetAddress(sendTo);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // Used to send message
        Transport transport = session.getTransport();
        transport.connect("posterbombemail@gmail.com", "Password100");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();       
    }
}
