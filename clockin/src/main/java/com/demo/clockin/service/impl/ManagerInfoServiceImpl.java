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
@Service("managerInfoService")
public class ManagerInfoServiceImpl implements IManagerInfoService {

	@Resource
	private IManagerInfoDao managerInfoDao;

	/* (non-Javadoc)
	 * @see com.demo.uc.service.IManagerInfoService#addSave(com.demo.uc.model.ManagerInfo)
	 */
	public int doSave(ManagerInfoBo managerInfo) {
		return managerInfoDao.doSave(managerInfo);
	}
		
	/* (non-Javadoc)
	 * @see com.demo.uc.service.IManagerInfoService#editSave(com.demo.uc.model.ManagerInfo)
	 */
	public int doEdit(ManagerInfoBo managerInfo) {
		return managerInfoDao.doEdit(managerInfo);
	}
	
	public ManagerInfoBo findInfoByManagerId(Integer managerId) {
		ManagerInfoBo managerInfo = new ManagerInfoBo();
		managerInfo.setManagerId(managerId);
		return managerInfoDao.findInfoByManagerId(managerInfo);
	}
}
