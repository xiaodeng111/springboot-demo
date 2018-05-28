package com.demo.clockin.dao;


import com.demo.clockin.domain.bo.ManagerInfoBo;
import com.demo.clockin.domain.bo.ManagerInfoBo;

/**
 *  DAO 接口
 * 
 * @author cg
 * 
 * @since 2017-02-28
 */
public interface IManagerInfoDao {

	/**
	 * 添加
	 * 
	 * @param managerInfo
	 * @return
	 */
	public int doSave(ManagerInfoBo managerInfoBo);

	/**
	 * 修改
	 * 
	 * @param managerInfo
	 * @return
	 */
	public int doEdit(ManagerInfoBo managerInfoBo);
	
	/**
	 * 根据managerId查询
	 * @param managerInfo
	 * @return
	 */
	public ManagerInfoBo findInfoByManagerId(ManagerInfoBo managerInfoBo);
}
