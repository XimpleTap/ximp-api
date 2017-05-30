package com.ximp.api.cards.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.cards.objects.InquiryObject;

public class InquiryObjectMapper implements RowMapper<InquiryObject>{

	public InquiryObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		InquiryObject iObject=new InquiryObject();
		iObject.setCardLoadBalance(rs.getDouble("balance"));
		iObject.setCardPointsBalance(rs.getDouble("acquired_points"));
		iObject.setBusinessId(rs.getInt("businessId"));
		iObject.setCardId(rs.getLong("cardId"));
		iObject.setUserId(rs.getLong("users_id"));
		iObject.setSerialNumber(rs.getString("serial_number"));
		iObject.setCardHolderName(rs.getString("holder_name"));
		return iObject;
	}

}
