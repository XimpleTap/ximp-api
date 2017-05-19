package com.ximp.api.rewards.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ximp.api.rewards.objects.RewardsObject;

public class RewardsObjectMapper implements RowMapper<RewardsObject>{

	public RewardsObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		RewardsObject rObject=new RewardsObject();
		rObject.setBusinessName(rs.getString("business_name"));
		rObject.setRequiredPoints(rs.getDouble("required_points"));
		rObject.setRewardName(rs.getString("description"));
		rObject.setRewardId(rs.getInt("id"));
		rObject.setRewardCountLimit(rs.getInt("reward_count_limit"));
		rObject.setMaxSingleClaim(rs.getInt("max_single_claim"));
		return rObject;
	}

}
