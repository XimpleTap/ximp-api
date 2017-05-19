package com.ximp.api.cards.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.cards.objects.CardGroupObject;

public class CardGroupObjectMapper implements RowMapper<CardGroupObject>{

	public CardGroupObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		CardGroupObject cgObject=new CardGroupObject();
		cgObject.setBusinessId(rs.getInt("business_id"));
		cgObject.setCardPrice(rs.getDouble("card_price"));
		cgObject.setPreloadedAmount(rs.getDouble("preloaded_amount"));
		return cgObject;
	}

}
