package com.ximp.api.rewards.objects;

import java.util.List;

public class RewardsObjectGroup {
	private int businessId;
	private List<RewardsObject> branchRewards;
	private List<RewardsObject> otherBranchesRewards;
	public List<RewardsObject> getBranchRewards() {
		return branchRewards;
	}
	public void setBranchRewards(List<RewardsObject> branchRewards) {
		this.branchRewards = branchRewards;
	}
	public List<RewardsObject> getOtherBranchesRewards() {
		return otherBranchesRewards;
	}
	public void setOtherBranchesRewards(List<RewardsObject> otherBranchesRewards) {
		this.otherBranchesRewards = otherBranchesRewards;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
}
