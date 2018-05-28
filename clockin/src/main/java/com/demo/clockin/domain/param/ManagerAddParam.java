package com.demo.clockin.domain.param;

import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.bo.ManagerInfoBo;

public class ManagerAddParam {
	private ManagerBo manager;
	
	private ManagerInfoBo managerInfo;

	public ManagerBo getManager() {
		return manager;
	}

	public void setManager(ManagerBo manager) {
		this.manager = manager;
	}

	public ManagerInfoBo getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(ManagerInfoBo managerInfo) {
		this.managerInfo = managerInfo;
	}
	
	
}
