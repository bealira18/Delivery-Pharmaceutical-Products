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

/**
 * This class serves as the interface that the application can use to send email
 * notifications, invoices or any other type of electronic correspondence.
 * 
 * @author lapr3-2020-g052
 */
public class EmailService {

    /**
     * The applications email session object, used to send the emails.
     */
    private Session emailSession;

    /**
     * Initializes the Email interface, by creating a new session and properly 
     * authenticate with the information from the configuration file.
     */
    public EmailService() {

        emailSession = Session.getInstance(System.getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getProperty("mail.username"), System.getProperty("mail.password"));
            }
        });
    }

    /**
     * Sends a email to a valid destination with the provided subject line and email body.
     *
     * @param destinationEmail a valid email destination.
     * @param subjectLine the subject of the email.
     * @param emailBody the email text body.
     * @return true on success, false on a MessageException (ie, invalid email).
     */
    public boolean sendEmail(String destinationEmail, String subjectLine, String emailBody) {

        try {
            Message message = new MimeMessage(emailSession);
            message.setFrom(new InternetAddress(System.getProperty("mail.username")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationEmail));
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
