package com.demo.clockin.service.impl;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.dao.IRolesDao;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.RolesParam;
import com.demo.clockin.service.IRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 业务接口 IRolesService 实现类
 * 
 * @author dengrq
 *
 * @date 2014-08-04
 */
@Service
public class RolesServiceImpl implements IRolesService {

	@Resource
	private IRolesDao rolesDao;

	public RolesBo findRolesById(RolesBo roles) {
		return rolesDao.findRolesById(roles);
	}

	@Override
	public List<RolesBo> findRolesList(Integer roleId) {
		return rolesDao.findRolesList(roleId);
	}

	@Override
	public IPage<RolesBo> findRoles(RolesParam param) {
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return rolesDao.findRolesByPage(param, pageRequest);
	}

	@Override
	public int doAddSave(RolesBo roles) {
		return rolesDao.doAddSaveRoles(roles);
	}

	@Override
	public int doEditSave(RolesBo roles) {
		return rolesDao.doEditSaveRoles(roles);
	}
}
