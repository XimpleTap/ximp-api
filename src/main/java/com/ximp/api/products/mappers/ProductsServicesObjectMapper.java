package com.ximp.api.products.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.products.objects.ProductsServicesObject;

public class ProductsServicesObjectMapper implements RowMapper<ProductsServicesObject>{

	public ProductsServicesObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductsServicesObject productObject=new ProductsServicesObject();
		productObject.setBusinessId(rs.getInt("business_id"));
		productObject.setProductId(rs.getInt("id"));
		productObject.setProductPrice(rs.getDouble("price"));
		productObject.setProductName(rs.getString("description"));
		return productObject;
	}

}
