package com.ximp.api.rewards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ximp.api.ApiEndpoints;
import com.ximp.api.ApiResponse;
import com.ximp.api.ResponseCodes;
import com.ximp.api.ResponseStatus;
import com.ximp.api.TransactionTypes;
import com.ximp.api.rewards.objects.ClaimRewardRequest;
import com.ximp.api.rewards.objects.ClaimRewardResult;
import com.ximp.api.rewards.objects.RewardsObjectGroup;

@RestController
public class RewardsController {
	@Autowired
	RewardsManager rewardsManager;
	
	@RequestMapping(value=ApiEndpoints.REWARDS_AVAILABLE, method=RequestMethod.GET)
	public ApiResponse getRewardsList(@PathVariable int businessId){
		ApiResponse response=new ApiResponse();
		try{
			RewardsObjectGroup rewardsList=rewardsManager.getRewardsList(businessId);
			response.setApiData(rewardsList);
			response.setStatus(ResponseStatus.OK);
			response.setStatusCode(ResponseCodes.OK);
		}catch(Exception e){
			response.setStatus(ResponseStatus.ERROR);
			response.setStatusCode(ResponseCodes.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value=ApiEndpoints.REWARDS_CLAIM, method=RequestMethod.POST)
	public ApiResponse claimRewards(@RequestBody ClaimRewardRequest claimRequest){
		ApiResponse response=new ApiResponse();
		try{
			List<ClaimRewardResult> claimsResulList=rewardsManager.claimRewards(claimRequest.getBusinessId(), claimRequest.getCardNumber(), claimRequest);
			response.setApiData(claimsResulList);
			response.setTransactionType(TransactionTypes.CLAIM);
			response.setStatus(ResponseStatus.OK);
			response.setStatusCode(ResponseCodes.OK);
		}catch(Exception e){
			response.setTransactionType(TransactionTypes.CLAIM);
			response.setStatus(ResponseStatus.ERROR);
			response.setStatusCode(ResponseCodes.ERROR);
		}
		return response;
	}
}
