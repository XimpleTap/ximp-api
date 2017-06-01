package com.ximp.api.users.controllers;

import java.util.Properties;

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
		sendMail(activationCode);
		
	}

	private void sendMail(String messageToSend) throws Exception{
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("dev.ximpletap@gmail.com");
	    mailSender.setPassword("X1mpyX1mp3r");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    
	    SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo("ezerdumale@gmail.com"); 
        message.setSubject("Activation"); 
        message.setText("https://apps.ximpletap.com:8080/users/activate/"+messageToSend);
        
	   JavaMailSender sender=mailSender;
	   sender.send(message);
	}
}
