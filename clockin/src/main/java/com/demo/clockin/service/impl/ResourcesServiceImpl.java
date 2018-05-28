package com.demo.clockin.service.impl;


import com.demo.clockin.service.IResourcesService;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.dao.IResourcesDao;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.param.ResourcesParam;
import com.demo.clockin.service.IResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源 业务接口 IResourcesService 实现类
 * 
 * @author dengrq
 *
 * @date 2014-07-29
 */
@Service("resourcesService")
public class ResourcesServiceImpl implements IResourcesService {

	@Resource
	private IResourcesDao resourcesDao;

	public List<ResourcesBo> selectAll() {
		return resourcesDao.selectAll();
	}
	
	@Override
	public ResourcesBo findResourcesById(Integer id) {
		return resourcesDao.findResourcesById(id);
	}

	@Override
	public IPage<ResourcesBo> findListByPage(ResourcesParam param) {
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return resourcesDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddResources(ResourcesBo resourcesBo) {
		resourcesDao.doAddResources(resourcesBo);
	}

	@Override
	public void doEditResources(ResourcesBo resourcesBo) {
		resourcesDao.doEditResources(resourcesBo);
	}

	@Override
	public void doDelResources(Integer id) {
		resourcesDao.doDelResources(id);
	}
	
}
