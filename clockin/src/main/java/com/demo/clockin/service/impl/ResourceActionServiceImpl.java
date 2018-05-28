package com.demo.clockin.service.impl;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.dao.IResourceActionDao;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.param.ResourceActionParam;
import com.demo.clockin.service.IResourceActionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源动作 业务接口 IResourceActionService 实现类
 * 
 * @author dengrq
 *
 * @date 2014-07-31
 */
@Service("resourceActionService")
public class ResourceActionServiceImpl implements IResourceActionService {

	@Resource
	private IResourceActionDao resourceActionDao;

	public List<ResourceActionBo> selectAll() {
		return resourceActionDao.selectAll();
	}
	
	@Override
	public int findMaxBit() {
		return resourceActionDao.findMaxBit();
	}
	
	@Override
	public ResourceActionBo findResourceActionById(Integer id) {
		// TODO Auto-generated method stub
		return resourceActionDao.findResourceActionById(id);
	}

	@Override
	public IPage<ResourceActionBo> findListByPage(ResourceActionParam param) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return resourceActionDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddResourceAction(ResourceActionBo resourceActionBo) {
		// TODO Auto-generated method stub
		resourceActionDao.doAddResourceAction(resourceActionBo);
	}

	@Override
	public void doEditResourceAction(ResourceActionBo resourceActionBo) {
		// TODO Auto-generated method stub
		resourceActionDao.doEditResourceAction(resourceActionBo);
	}

	@Override
	public void doDelResourceAction(Integer id) {
		// TODO Auto-generated method stub
		resourceActionDao.doDelResourceAction(id);
	}
	
}
