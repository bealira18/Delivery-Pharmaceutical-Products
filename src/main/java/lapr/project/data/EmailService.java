/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ricardo
 */
public class EmailService {
    
    private Session emailSession;
    
    public EmailService()
    {
        emailSession = Session.getInstance(System.getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getProperty("mail.username"), System.getProperty("mail.password"));
            }
        });
    }
    
    public boolean sendEmail(String destinationEmaail, String subjectLine, String emailBody) {
        try {

            Message message = new MimeMessage(emailSession);
            message.setFrom(new InternetAddress(System.getProperty("mail.username")));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmaail));

            message.setSubject(subjectLine);
            message.setText(emailBody);

            //Transport.send(message);
            System.out.println(destinationEmaail+"\n"+subjectLine+"\n"+emailBody);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
