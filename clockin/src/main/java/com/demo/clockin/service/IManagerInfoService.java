package com.demo.clockin.service;


import com.demo.clockin.domain.bo.ManagerInfoBo;

/**
 *  业务接口
 * 
 * @author dengrq
 *
 * @date 2017-02-28
 */
public interface IManagerInfoService {
		
	/**
	 * 添加  
	 * @param managerInfo
	 * @return
	 */
	public int doSave(ManagerInfoBo managerInfo);
	
	/**
	 * 修改  
	 * @param managerInfo
	 * @return
	 */
	public int doEdit(ManagerInfoBo managerInfo);
	
	/**
	 * 根据managerId查询 结果唯一
	 * @param managerId
	 * @return
	 */
	public ManagerInfoBo findInfoByManagerId(Integer managerId);
}
