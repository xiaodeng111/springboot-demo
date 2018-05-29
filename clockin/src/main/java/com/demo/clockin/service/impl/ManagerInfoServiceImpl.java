package com.demo.clockin.service.impl;


import com.demo.clockin.dao.IManagerInfoDao;
import com.demo.clockin.domain.bo.ManagerInfoBo;
import com.demo.clockin.service.IManagerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  业务接口 IManagerInfoService 实现类
 * 
 * @author dengrq
 *
 * @date 2017-02-28
 */
@Service
public class ManagerInfoServiceImpl implements IManagerInfoService {

	@Resource
	private IManagerInfoDao managerInfoDao;


	public int doSave(ManagerInfoBo managerInfo) {
		return managerInfoDao.doSave(managerInfo);
	}
		

	public int doEdit(ManagerInfoBo managerInfo) {
		return managerInfoDao.doEdit(managerInfo);
	}
	
	public ManagerInfoBo findInfoByManagerId(Integer managerId) {
		ManagerInfoBo managerInfo = new ManagerInfoBo();
		managerInfo.setManagerId(managerId);
		return managerInfoDao.findInfoByManagerId(managerInfo);
	}
}
