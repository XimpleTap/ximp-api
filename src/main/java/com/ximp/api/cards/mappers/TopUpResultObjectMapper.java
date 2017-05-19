package com.ximp.api.cards.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.cards.objects.TopUpResultObject;

public class TopUpResultObjectMapper implements RowMapper<TopUpResultObject>{

	public TopUpResultObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopUpResultObject tRObject=new TopUpResultObject();
		tRObject.setBalanceBeforeReload(rs.getDouble("oldBalance"));
		tRObject.setPointsBeforeReload(rs.getInt("oldPoints"));
		tRObject.setBalanceAfterReload(rs.getDouble("balance"));
		tRObject.setPointsAfterReload(rs.getInt("acquired_points"));
		tRObject.setLoadAmount(rs.getInt("top_up_amount"));
		return tRObject;
	}

}
