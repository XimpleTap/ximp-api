package com.ximp.api.users.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.ximp.api.users.objects.Login;
import com.ximp.api.users.objects.LoginResponse;
import com.ximp.api.users.objects.UsersDAO;

@Component
public class UsersManager {
	
	@Autowired
	UsersDAO usersDAO;
	
	public LoginResponse loginCashier(Login login) throws Exception{
		return usersDAO.loginCashier(login);
	}
	
	public void activateUser(String activationCode) throws Exception{
		usersDAO.activateUser(activationCode);
	}
	
	public void sendActivationMail(long userId) throws Exception{
		String activationCode=usersDAO.generateActivationCode(userId);
		sendMail(activationCode, "ezerdumale@gmail.com");
		
	}

	private void sendMail(String messageToSend, String recepient) throws Exception{
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", 25); 
    	
    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
    	// The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("info@ximpletap.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        msg.setSubject("Activation");
        msg.setContent("https://apps.ximpletap.com/cards/activate?code="+messageToSend, "text/plain");
            
        // Create a transport.        
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
           // transport.connect("email-smtp.us-east-1.amazonaws.com", "AKIAB_IKWCDJ6RXNP5MVGA", "AriB_VTx6hPKIxLuBJPGkH29DKVfqHllDKKUqfaDw4Jco2");
            transport.connect("smtp.gmail.com", "dev.ximpletap@gmail.com", "...");
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();        	
        }
	}
}
