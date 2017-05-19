package com.ximp.api.cards.controllers;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.ximp.api.TransactionTypes;
import com.ximp.api.cards.objects.CardAvailTransactions;
import com.ximp.api.cards.objects.CardGroupObject;
import com.ximp.api.cards.objects.CardSalesObject;
import com.ximp.api.cards.objects.InquiryObject;
import com.ximp.api.cards.objects.TopUpResultObject;
import com.ximp.api.cards.objects.TopupObject;


@RestController
public class CardsController {
	Logger log=Logger.getLogger(CardsController.class);
	
	@Autowired
	CardsManager cardsManager;
	
	@RequestMapping(value=ApiEndpoints.CARD_RELOAD, method=RequestMethod.POST)
	public ApiResponse processTopup(@RequestBody TopupObject rObject){
		ApiResponse response=new ApiResponse();
		try{
			TopUpResultObject tRObject=cardsManager.processTopUp(rObject);
			response.setApiData(tRObject);
			response.setTransactionType(TransactionTypes.RELOAD);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setTransactionType(TransactionTypes.RELOAD);
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.CARD_INQUIRE_BALANCE, method=RequestMethod.GET)
	public ApiResponse inquireBalance(@PathVariable String cardNumber){
		ApiResponse response=new ApiResponse();
		try{
			InquiryObject iObject=cardsManager.inquireCardBalance(cardNumber);
			response.setApiData(iObject);
			response.setTransactionType(TransactionTypes.INQUIRE);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setTransactionType(TransactionTypes.INQUIRE);
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.CARD_TRANSACTIONS, method=RequestMethod.GET)
	public ApiResponse getTransactionsList(@PathVariable String cardNumber, @PathVariable int businessId){
		
		ApiResponse response=new ApiResponse();
		try{
			List<CardAvailTransactions> tList=cardsManager.getLastTransactions(cardNumber, businessId);
			response.setApiData(tList);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.ASSIGN_CARD_TO_USER, method=RequestMethod.GET)
	public ApiResponse assignCardToUser(@PathVariable String cardNumber, @PathVariable long userId){
		ApiResponse response=new ApiResponse();
		try{
			cardsManager.assignCardToUser(cardNumber, userId);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.SELL_CARD, method=RequestMethod.POST)
	public ApiResponse processCardSales(@RequestBody CardSalesObject csObject){
		ApiResponse response=new ApiResponse();
		try{
			cardsManager.processCardSales(csObject);
			response.setTransactionType(TransactionTypes.SELL_CARD);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
			response.setApiData("READY FOR USE:"+csObject.getCardNumber());
		}catch(Exception ex){
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.CARD_GROUPS, method=RequestMethod.GET)
	public ApiResponse assignCardToUser(@PathVariable int businessId){
		ApiResponse response=new ApiResponse();
		try{
			List<CardGroupObject> cardGroups=cardsManager.getCardGroupsOfBusiness(businessId);
			response.setApiData(cardGroups);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.CARD_LOST_REPORT, method=RequestMethod.GET)
	public ApiResponse reportLostCard(@PathVariable String cardNumber){
		ApiResponse response=new ApiResponse();
		try{
			cardsManager.tagCardAsLost(cardNumber);
			response.setStatusCode(ResponseCodes.OK);
			response.setStatus(ResponseStatus.OK);
		}catch(Exception ex){
			response.setStatusCode(ResponseCodes.ERROR);
			response.setStatus(ResponseStatus.ERROR);
		}
		return response;
	}
}

