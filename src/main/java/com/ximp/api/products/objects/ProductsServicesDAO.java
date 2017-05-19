package com.ximp.api.products.objects;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ximp.api.products.mappers.ProductsServicesObjectMapper;

@Component
public class ProductsServicesDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductsServicesObject> getProductsAndServicesOfBusiness(int businessId) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("GET_PRODUCTS_AND_SERVICES").returningResultSet("products", new ProductsServicesObjectMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("businessId", businessId);
		SqlParameterSource param=params;
		Map<String, Object> result=procCall.execute(param);
		return (List<ProductsServicesObject>) result.get("products");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductsServicesObject> getLoadDenomsOfBusiness(int businessId) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("GET_TOPUP_DENOMS").returningResultSet("products", new ProductsServicesObjectMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("businessId", businessId);
		SqlParameterSource param=params;
		Map<String, Object> result=procCall.execute(param);
		return (List<ProductsServicesObject>) result.get("products");
	}
	public String acquireProduct(int businessId, String cardNumber, ProductToAcquire product) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("PROCESS_PRODUCT_BOUGHT");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("businessId", businessId);
		params.addValue("productId", product.getProductId());
		params.addValue("cardNumber",cardNumber);
		params.addValue("itemCount",product.getItemCount());
		SqlParameterSource param=params;
		Map<String, Object> result=procCall.execute(param);
		return (String) result.get("outMessage");
	}
}
