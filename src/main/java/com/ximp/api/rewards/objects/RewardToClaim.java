package com.ximp.api.rewards.objects;

public class RewardToClaim {

	private int rewardId;
	private int itemCount;

	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
}
