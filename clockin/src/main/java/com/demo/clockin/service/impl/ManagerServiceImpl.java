package com.demo.clockin.service.impl;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.dao.IManagerDao;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.param.ManagerParam;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.service.IManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  业务接口 IManagerService 实现类
 * 
 * @author dengrq
 *
 * @date 2017-02-27
 */
@Service
public class ManagerServiceImpl implements IManagerService {

	@Resource
	private IManagerDao managerDao;

	public int doSave(ManagerBo manager) {
		return managerDao.doAddManager(manager);
	}
	
	public int doEdit(ManagerBo manager) {
		return managerDao.doEditManager(manager);
	}
	
	public int doDeleteByIds(String str) {
		return managerDao.doDeleteById(str);
	}
	
	public IPage<ManagerVo> findManager(ManagerParam manager) {
//		return new Page(managerDao.getManagerCount(manager), pageSize);
		PageRequest pageRequest = new PageRequest(manager.getPageNumber(), manager.getPageSize());
		return managerDao.findManagerListByPage(manager, pageRequest);
	}

	public ManagerBo findManagerByParam(ManagerBo manager) {
		return managerDao.findManagerById(manager);
	}

	@Override
	public ManagerVo findManager4Login(ManagerBo manager) {
		return managerDao.findManager4Login(manager);
	}

	@Override
	public void doSetLoginTime(ManagerBo manager) {
		managerDao.doSetLoginTime(manager);
	}

	@Override
	public void doResetPassword(ManagerBo manager) {
		managerDao.doResetPassword(manager);
	}
	
	@Override
	public ManagerVo findManagerById(Integer id) {
		return managerDao.findManagerById(id);
	}

	@Override
	public IPage<ManagerBo> findListByPage(ManagerParam param) {
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return managerDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddManager(ManagerBo managerBo) {
		managerDao.doAddManager(managerBo);
	}

	@Override
	public void doEditManager(ManagerBo managerBo) {
		managerDao.doEditManager(managerBo);
	}

	@Override
	public void doDelManager(Integer id) {
		managerDao.doDelManager(id);
	}
	
}
