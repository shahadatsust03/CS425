/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Shahadat
 */

public class EmailController {
    
    
    final String username = "gpa.application@gmail.com";
    final String password = "gpa.application1";

    public EmailController() {

    }

    /**
     * *
     * Generate Email after new Applicant registration.
     *
     * @param strTo
     */
    public void generateEmailForNewAppRegistration(String strTo) {
        String strSubject = "Confirmation Email for registration";
        String strMessage = "Welcome to Blog World. " + "Your registration is confirmed.\n"
                + "Do not reply this is system generated email.";

        sendEmail(strTo, strSubject, strMessage);
    }

   
    /**
     * 
     * @param strTo
     * @param newPassword 
     */
    public void generateEmailForRetrievePassword(String strTo,String newPassword) throws Exception{

        String strSubject = "New password";
        String strMessage = "Since you requested new password we have sent you this email " 
                + "\n Now your new password is "+newPassword+"\n"
                + "Do not reply this is system generated email.";
        sendEmail(strTo, strSubject, strMessage);

    }

    /**
     * Generate email after reset user password.
     * @param strTo 
     */
    public void generateEmailForResetPassword(String strTo) {
        
        String strSubject = "Password Changed.";
        String strMessage = "Dear Applicant,\n\n"
                + "We are sending you this email because your password has been changed";

        sendEmail(strTo, strSubject, strMessage);

    }
    
    

    public void sendEmail(String strTo, String strSubject, String strMessage) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gpa.application@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(strTo));
            message.setSubject(strSubject);
            message.setText(strMessage);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}


    
    
