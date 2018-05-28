package com.demo.clockin.service.impl;

import com.demo.clockin.dao.IRoleResourceDao;
import com.demo.clockin.domain.bo.RoleResourceBo;
import com.demo.clockin.service.IRoleResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色资源 业务接口 IRoleResourceService 实现类
 * 
 * @author dengrq
 *
 * @date 2014-08-04
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl implements IRoleResourceService {

	@Resource
	private IRoleResourceDao roleResourceDao;

	public List<RoleResourceBo> findRoleResourceByRoleId(RoleResourceBo roleResource) {
		return roleResourceDao.findRoleResourceByRoleId(roleResource);
	}

	@Override
	public int doAddSave(RoleResourceBo roleResource) {
		return roleResourceDao.doAddSaveRoleResource(roleResource);
	}

	@Override
	public int doDeleteByRoleIds(String str) {
		return roleResourceDao.doDeleteByRoleIds(str);
	}
	
}
