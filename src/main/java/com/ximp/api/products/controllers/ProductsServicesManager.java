package com.ximp.api.products.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ximp.api.products.objects.AcquireProductRequest;
import com.ximp.api.products.objects.AcquireProductResult;
import com.ximp.api.products.objects.ProductToAcquire;
import com.ximp.api.products.objects.ProductsServicesDAO;
import com.ximp.api.products.objects.ProductsServicesObject;

@Component
public class ProductsServicesManager {
	
	@Autowired
	ProductsServicesDAO productsServicesDAO;
	
	public List<ProductsServicesObject> getProductsAndServices(int businessId) throws Exception{
		return productsServicesDAO.getProductsAndServicesOfBusiness(businessId);
	}
	
	public List<ProductsServicesObject> getLoadDenoms(int businessId) throws Exception{
		return productsServicesDAO.getLoadDenomsOfBusiness(businessId);
	}
	
	public List<AcquireProductResult> acquireProducts(int businessId, AcquireProductRequest acquireRequest) throws Exception{
		List<AcquireProductResult> products=new ArrayList<AcquireProductResult>();
		String acquireResult="";
		for(ProductToAcquire product:acquireRequest.getProductsToAcquire()){
			acquireResult=productsServicesDAO.acquireProduct(businessId, acquireRequest.getCardNumber(), product);
			AcquireProductResult result=new AcquireProductResult();
			result.setItemCount(product.getItemCount());
			result.setProductId(product.getProductId());
			result.setMessage(acquireResult);
			products.add(result);
		}
		
		return products;
	}

}
