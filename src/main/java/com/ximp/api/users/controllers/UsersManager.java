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
		sendMail(activationCode);
		
	}

	private void sendMail(String messageToSend) throws Exception{
	  
	}
}
