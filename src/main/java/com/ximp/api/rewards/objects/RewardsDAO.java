package com.ximp.api.rewards.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ximp.api.rewards.mappers.RewardsObjectMapper;

@Component
public class RewardsDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@SuppressWarnings("unchecked")
	public RewardsObjectGroup getAvailableRewards(int businessId) throws Exception{
		RewardsObjectGroup rewardsGroup=new RewardsObjectGroup();
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("GET_AVAILABLE_REWARDS")
				.returningResultSet("branchRewards", new RewardsObjectMapper())
				.returningResultSet("otherBranchesRewards", new RewardsObjectMapper());
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("businessId", businessId);
		SqlParameterSource param=params;
		
		Map<String, Object> branchRewards=procCall.execute(param);
		rewardsGroup.setBranchRewards((List<RewardsObject>)branchRewards.get("branchRewards"));
		rewardsGroup.setOtherBranchesRewards((List<RewardsObject>)branchRewards.get("otherBranchesRewards"));
		rewardsGroup.setBusinessId(businessId);
		return rewardsGroup;
		
	}
	
	public String claimReward(int businessId, String cardNumber,  int cashierId, RewardToClaim rewardToClaim) throws Exception{
		String claimResult="";
		SimpleJdbcCall procCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("PROCESS_REWARD_CLAIM");
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("rewardId", rewardToClaim.getRewardId());
		params.addValue("businessId", businessId);
		params.addValue("itemCount", rewardToClaim.getItemCount());
		params.addValue("cardNumber", cardNumber);
		params.addValue("cashierId", cashierId);
		SqlParameterSource param=params;
		Map<String, Object> claimsResult=procCall.execute(param);
		claimResult=(String)claimsResult.get("outMessage");
		return claimResult;
		
	}
}
