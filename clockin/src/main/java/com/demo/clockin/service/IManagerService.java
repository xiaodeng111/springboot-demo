package com.demo.clockin.service;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.param.ManagerParam;
import com.demo.clockin.domain.vo.ManagerVo;

/**
 *  业务接口
 * 
 * @author dengrq
 *
 * @date 2017-02-27
 */
public interface IManagerService {

	/**
	 * 添加  
	 * @param manager
	 * @return
	 */
	public int doSave(ManagerBo manager);
	
	/**
	 * 修改  
	 * @param manager
	 * @return
	 */
	public int doEdit(ManagerBo manager);
	
	/**
	 * 根据 ID 删除  
	 * @param str
	 * @return
	 */
	public int doDeleteByIds(String str);
	
	/**
	 * 查询 Manager Page 对象
	 * @param manager
	 * @param pageSize
	 * @return
	 */
	public IPage<ManagerVo> findManager(ManagerParam manager);
	
	/**
	 * 根据 ID 查找  
	 * @param manager
	 * @return
	 */
	public ManagerBo findManagerByParam(ManagerBo manager);
	
	/**
	 * 登录系统
	 * @param manager
	 * @return ManagerBo
	 * @author: Bobbie.Qi
	 * @time:2017年4月27日
	 */
	public ManagerVo findManager4Login(ManagerBo manager);
	
	/**
	 * 修改登录时间与IP
	 * @param manager void
	 * @author: Bobbie.Qi
	 * @time:2017年4月27日
	 */
	public void doSetLoginTime(ManagerBo manager);
	
	/**
	 * 重置密码
	 * @param manager
	 * @return
	 */
	public void doResetPassword(ManagerBo manager);
	
	/**
	 * 根据编号查询账号管理信息
	 * @param id
	 * @return ManagerBo
	 * @author: dengrq
	 * @time: 2017-06-02
	 */
	public ManagerVo findManagerById(Integer id);

	/**
	 * 分页查询账号管理列表
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: dengrq
	 * @time: 2017-06-02
	 */
	public IPage<ManagerBo> findListByPage(ManagerParam param);
	
	/**
	 * 添加账号管理
	 * @param managerBo void
	 * @author: dengrq
	 * @time: 2017-06-02
	 */
	public void doAddManager(ManagerBo managerBo);
	
	/**
	 * 编辑账号管理
	 * @param managerBo void
	 * @author: dengrq
	 * @time: 2017-06-02
	 */
	public void doEditManager(ManagerBo managerBo);
	
	/**
	 * 根据编号删除账号管理
	 * @param id void
	 * @author: dengrq
	 * @time: 2017-06-02
	 */
	public void doDelManager(Integer id);
	
}
