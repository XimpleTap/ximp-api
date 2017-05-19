package com.ximp.api.rewards.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ximp.api.rewards.objects.ClaimRewardRequest;
import com.ximp.api.rewards.objects.ClaimRewardResult;
import com.ximp.api.rewards.objects.RewardToClaim;
import com.ximp.api.rewards.objects.RewardsDAO;
import com.ximp.api.rewards.objects.RewardsObjectGroup;

@Component
public class RewardsManager {

		@Autowired
		RewardsDAO rewardsDAO;
		
		public RewardsObjectGroup getRewardsList(int businessId) throws Exception{
			return rewardsDAO.getAvailableRewards(businessId);
			
		}
		
		public List<ClaimRewardResult> claimRewards(int businessId, String cardNumber, ClaimRewardRequest claimRequest) throws Exception{
			List<ClaimRewardResult> claimsResult=new ArrayList<ClaimRewardResult>();
			for(RewardToClaim claim:claimRequest.getRewardsToClaim()){
				ClaimRewardResult claimResult=new ClaimRewardResult();
				claimResult.setItemCount(claim.getItemCount());
				claimResult.setRewardId(claim.getRewardId());
				String message=rewardsDAO.claimReward(businessId, cardNumber, claimRequest.getCashierId(), claim);
				claimResult.setMessage(message);
				claimsResult.add(claimResult);
			}
			return claimsResult;
		}
}
