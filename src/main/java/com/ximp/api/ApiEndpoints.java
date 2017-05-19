package com.ximp.api;

public class ApiEndpoints {
	
	
	//CardsController
	public static final String CARD_RELOAD					=	"/cards/reload";
	public static final String CANCEL_CARD_RELOAD			=	"/cards/reload/cancel";
	public static final String CARD_INQUIRE_BALANCE			=	"/cards/balance/{cardNumber}/inquire";
	public static final String CARD_TRANSACTIONS			=	"/cards/{cardNumber}/transactions/{businessId}";
	public static final String ASSIGN_CARD_TO_USER			=	"/cards/{cardNumber}/assign/{userId}";
	public static final String SELL_CARD					=	"/cards/sell";
	public static final String CARD_GROUPS					=	"/cards/{businessId}/groups";
	
	//ProductsServiceController
	public static final String PRODUCTS_SERVICES			=	"/products/{businessId}";
	public static final String PRODUCTS_SERVICES_LOAD		=	"/products/load/{businessId}/denoms";
	public static final String PRODUCTS_SERVICES_ACQUIRE	=	"/products/acquire";
	
	//RewardsController
	public static final String REWARDS_AVAILABLE			=	"/rewards/{businessId}";
	public static final String REWARDS_CLAIM				=	"/rewards/claim";
	
	//UsersController
	public static final String USER_LOGIN					=	"/users/login";
	
}

