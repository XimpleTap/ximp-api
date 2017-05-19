package com.ximp.api.cards.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.cards.objects.CardAvailTransactions;

public class CardAvailTransactionsMapper implements RowMapper<CardAvailTransactions>{

	public CardAvailTransactions mapRow(ResultSet rs, int rowNum) throws SQLException {
		CardAvailTransactions catObject=new CardAvailTransactions();
		catObject.setProductDescription(rs.getString("description"));
		catObject.setItemsCount(rs.getInt("items_count"));
		catObject.setNewBalance(rs.getDouble("new_balance"));
		catObject.setOldBalance(rs.getDouble("old_balance"));
		catObject.setTotalPrice(rs.getDouble("total_price"));
		catObject.setTransactionTime(rs.getDate("transaction_time"));
		return catObject;
	}

	/*ps.description, tl.old_balance, 
	 tl.new_balance, tl.items_count, tl.transaction_time,
	 (tl.items_count*tl.item_price)-tl.transaction_discount AS total_price
	 */
}
