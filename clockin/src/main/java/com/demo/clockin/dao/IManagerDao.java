package com.demo.clockin.dao;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.param.ManagerParam;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.param.ManagerParam;
import com.demo.clockin.domain.vo.ManagerVo;
import org.apache.ibatis.annotations.Param;

/**
 *  DAO 接口
 * 
 * @author cg
 * 
 * @since 2017-02-27
 */
public interface IManagerDao {

	/**
	 * 分页查找
	 * @param manager
	 * @param pageRequest
	 * @return IPage<ManagerBo>
	 * @author: Bobbie.Qi
	 * @time:2017年4月26日
	 */
	public IPage<ManagerBo> findManagerByPage(@Param("param") ManagerParam manager, PageRequest pageRequest);

	/**
	 * 查询 Manager Page 对象
	 * @param manager
	 * @param pageSize
	 * @return
	 */
	public IPage<ManagerVo> findManagerListByPage(@Param("param") ManagerParam manager, PageRequest pageRequest);

	/**
	 * 添加
	 *
	 * @param manager
	 * @return
	 */
	public int doAddManager(ManagerBo manager);

	/**
	 * 根据ID获取指定的信息
	 *
	 * @param manager
	 * @return
	 */
	public ManagerBo findManagerById(ManagerBo manager);

	/**
	 * 修改
	 *
	 * @param manager
	 * @return
	 */
	public int doEditManager(ManagerBo manager);

	/**
	 * 根据ID删除指定的
	 *
	 * @param str
	 * @return
	 */
	public int doDeleteById(String str);

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
	 * @author: cg
	 * @time: 2017-06-02
	 */
	public ManagerVo findManagerById(Integer id);

	/**
	 * 分页查询账号管理列表
	 * @param managerParam
	 * @param pageRequest
	 * @return IPage<ManagerBo>
	 * @author: cg
	 * @time: 2017-06-02
	 */
	public IPage<ManagerBo> findListByPage(@Param("param") ManagerParam managerParam, PageRequest pageRequest);
		
	/**
	 * 根据编号删除账号管理
	 * @param id void
	 * @author: cg
	 * @time: 2017-06-02
	 */
	public void doDelManager(Integer id);
	
}
