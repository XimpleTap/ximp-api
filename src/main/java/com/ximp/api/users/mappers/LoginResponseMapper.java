package com.ximp.api.users.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.users.objects.LoginResponse;

public class LoginResponseMapper implements RowMapper<LoginResponse>{

	public LoginResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoginResponse login=new LoginResponse();
		login.setBusinessId(rs.getInt("business_id"));
		login.setFirstName(rs.getString("first_name"));
		login.setLastName(rs.getString("last_name"));
		login.setUserId(rs.getLong("user_id"));
		login.setUserTypeId(rs.getInt("user_type_id"));
		login.setBannerLink(rs.getString("banner_link"));
		return login;
	}

}
