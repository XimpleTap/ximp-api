package com.ximp.api.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

}
