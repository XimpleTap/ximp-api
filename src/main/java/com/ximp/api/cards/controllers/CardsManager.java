package com.ximp.api.cards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ximp.api.cards.objects.CardAvailTransactions;
import com.ximp.api.cards.objects.CardGroupObject;
import com.ximp.api.cards.objects.CardSalesObject;
import com.ximp.api.cards.objects.CardsDao;
import com.ximp.api.cards.objects.InquiryObject;
import com.ximp.api.cards.objects.TopUpResultObject;
import com.ximp.api.cards.objects.TopupObject;

@Component
public class CardsManager {
	
	@Autowired
	CardsDao cardsDAO;
	
	public InquiryObject inquireCardBalance(String cardNumber) throws Exception, IndexOutOfBoundsException{
		return cardsDAO.inquireCardBalance(cardNumber);
	}
	
	public TopUpResultObject processTopUp(TopupObject rObject) throws Exception{
		return cardsDAO.processTopUp(rObject);
	}
	
	public List<CardAvailTransactions> getLastTransactions(String cardNumber, int businessId) throws Exception{
		return cardsDAO.getLastTransactionsList(cardNumber, businessId);
	}
	
	public void assignCardToUser(String cardNumber, long userId) throws Exception{
		cardsDAO.assignCardToUser(cardNumber, userId);
	}
	
	public void processCardSales(CardSalesObject csObject) throws Exception{
		cardsDAO.processCardSales(csObject);
	}
	
	public List<CardGroupObject> getCardGroupsOfBusiness(int businessId) throws Exception{
		return cardsDAO.getCardGroupsOfBusiness(businessId);
	}
	
	public boolean tagCardAsLost(String cardNumber) throws Exception{
		return cardsDAO.tagCardAsLost(cardNumber);
	}
}
