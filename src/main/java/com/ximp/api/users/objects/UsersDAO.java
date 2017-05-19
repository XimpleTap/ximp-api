package com.ximp.api.users.objects;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ximp.api.rewards.mappers.RewardsObjectMapper;
import com.ximp.api.rewards.objects.RewardsObject;
import com.ximp.api.rewards.objects.RewardsObjectGroup;
import com.ximp.api.users.mappers.LoginResponseMapper;

@Component
public class UsersDAO {


	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public LoginResponse loginCashier(Login login) throws Exception{
		LoginResponse response=new LoginResponse();
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("CASHIER_LOGIN")
				.returningResultSet("userDetail", new LoginResponseMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("userName", login.getUserName());
		params.addValue("userPassword", login.getUserPassword());
		SqlParameterSource param=params;
		
		Map<String, Object> details=procCall.execute(param);
		List<LoginResponse> objectList=(List<LoginResponse>)details.get("userDetail");
		response=objectList.get(0);
		
		return response;
		
	}
}
