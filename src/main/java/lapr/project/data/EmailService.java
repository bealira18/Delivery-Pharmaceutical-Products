package lapr.project.data;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private Session emailSession;

    public EmailService() {

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

            Transport.send(message);
            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
