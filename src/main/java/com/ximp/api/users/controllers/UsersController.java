package com.ximp.api.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ximp.api.ApiEndpoints;
import com.ximp.api.ApiResponse;
import com.ximp.api.ResponseCodes;
import com.ximp.api.ResponseStatus;
import com.ximp.api.users.objects.Login;
import com.ximp.api.users.objects.LoginResponse;


@RestController
public class UsersController {

	@Autowired
	UsersManager usersManager;
	
	@RequestMapping(value=ApiEndpoints.USER_LOGIN, method=RequestMethod.POST)
	public ApiResponse getProductsAndServices(@RequestBody Login login){
		ApiResponse response=new ApiResponse();
		try{
			LoginResponse loginResp=usersManager.loginCashier(login);
			response.setApiData(loginResp);
			response.setStatus(ResponseStatus.OK);
			response.setStatusCode(ResponseCodes.OK);
		}catch(Exception e){
			response.setApiData(null);
			response.setStatus(ResponseStatus.ERROR);
			response.setStatusCode(ResponseCodes.ERROR);
			e.printStackTrace();
		}
		
		return response;
		
	}
}
