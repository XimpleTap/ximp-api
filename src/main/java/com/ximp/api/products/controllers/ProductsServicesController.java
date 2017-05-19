package com.ximp.api.products.controllers;

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
import com.ximp.api.TransactionTypes;
import com.ximp.api.products.objects.AcquireProductRequest;
import com.ximp.api.products.objects.AcquireProductResult;
import com.ximp.api.products.objects.ProductsServicesObject;

@RestController
public class ProductsServicesController {
	
	@Autowired
	ProductsServicesManager productsServicesManager;
	
	@RequestMapping(value=ApiEndpoints.PRODUCTS_SERVICES, method=RequestMethod.GET)
	public ApiResponse getProductsAndServices(@PathVariable int businessId){
		ApiResponse response=new ApiResponse();
		try{
			List<ProductsServicesObject> productList=productsServicesManager.getProductsAndServices(businessId);
			response.setApiData(productList);
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
	
	@RequestMapping(value=ApiEndpoints.PRODUCTS_SERVICES_LOAD, method=RequestMethod.GET)
	public ApiResponse getLoadDenoms(@PathVariable int businessId){
		ApiResponse response=new ApiResponse();
		try{
			List<ProductsServicesObject> productList=productsServicesManager.getLoadDenoms(businessId);
			response.setApiData(productList);
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
	
	@RequestMapping(value=ApiEndpoints.PRODUCTS_SERVICES_ACQUIRE, method=RequestMethod.POST)
	public ApiResponse acquireProductOrServices(@RequestBody AcquireProductRequest acquireRequest){
		ApiResponse response=new ApiResponse();
		try{
			List<AcquireProductResult> acquireResults=productsServicesManager.acquireProducts(acquireRequest.getBusinessId(),acquireRequest);
			response.setApiData(acquireResults);
			response.setTransactionType(TransactionTypes.ACQUIRE_PRODUCT);
			response.setStatus(ResponseStatus.OK);
			response.setStatusCode(ResponseCodes.OK);
		}catch(Exception e){
			response.setTransactionType(TransactionTypes.ACQUIRE_PRODUCT);
			response.setStatus(ResponseStatus.ERROR);
			response.setStatusCode(ResponseCodes.ERROR);
			e.printStackTrace();
		}
		
		return response;
		
	}
}
