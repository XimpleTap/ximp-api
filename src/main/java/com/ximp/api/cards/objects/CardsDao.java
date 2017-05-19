package com.ximp.api.cards.objects;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ximp.api.cards.mappers.CardAvailTransactionsMapper;
import com.ximp.api.cards.mappers.CardGroupObjectMapper;
import com.ximp.api.cards.mappers.InquiryObjectMapper;
import com.ximp.api.cards.mappers.TopUpResultObjectMapper;

@Component
public class CardsDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@SuppressWarnings("unchecked")
	public InquiryObject inquireCardBalance(String cardNumber) throws Exception{
		InquiryObject iObject=new InquiryObject();
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("INQUIRE_CARD_BALANCE")
				.returningResultSet("balanceInq", new InquiryObjectMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("cardNumber", cardNumber);
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
		List<InquiryObject> result=(List<InquiryObject>)map.get("balanceInq");
		iObject=result.get(0);
		return iObject;
		
	}
	
	@SuppressWarnings("unchecked")
	public TopUpResultObject processTopUp(TopupObject rObject) throws Exception{
		TopUpResultObject tRObject=new TopUpResultObject();
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).returningResultSet("topup", new TopUpResultObjectMapper())
				.withProcedureName("TOPUP_CARD");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("cardNumber", rObject.getCardNumber());
		params.addValue("businessId", rObject.getBusinessId());
		params.addValue("productId", rObject.getProductId());
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
		List<TopUpResultObject> result=(List<TopUpResultObject>)map.get("topup");
		tRObject=result.get(0);
		return tRObject;
	}
	
	@SuppressWarnings("unchecked")
	public List<CardAvailTransactions> getLastTransactionsList(String cardNumber, int businessId) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).returningResultSet("tlist", new CardAvailTransactionsMapper())
				.withProcedureName("GET_LAST_TRANSACTIONS");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("cardNumber", cardNumber);
		params.addValue("businessId", businessId);
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
		List<CardAvailTransactions> result=(List<CardAvailTransactions>)map.get("tlist");
		return result;
	}
	

	public void assignCardToUser(String cardNumber, long userId) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("ASSIGN_CARD_TO_USER");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("cardNumber", cardNumber);
		params.addValue("userId", userId);
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
	}
	
	public void processCardSales(CardSalesObject csObject) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("PROCESS_CARD_SALES");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("cardNumber", csObject.getCardNumber());
		params.addValue("cashierId", csObject.getCashierId());
		params.addValue("cardGroupId", csObject.getCardGroupId());
		params.addValue("businessId", csObject.getBusinessId());
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
	}
	
	@SuppressWarnings("unchecked")
	public List<CardGroupObject> getCardGroupsOfBusiness(int businessId) throws Exception{
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("GET_CARD_GROUPS").returningResultSet("groups", new CardGroupObjectMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("businessId", businessId);
		SqlParameterSource param=params;
		Map<String, Object> map=procCall.execute(param);
		List<CardGroupObject> result=(List<CardGroupObject>) map.get("groups");
		return result;
	}
}
