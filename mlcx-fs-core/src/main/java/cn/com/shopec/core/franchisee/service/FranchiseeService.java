package cn.com.shopec.core.franchisee.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.franchisee.model.Franchisee;

/**
 * 加盟商表 服务接口类
 */
public interface FranchiseeService extends BaseService {

	/**
	 * 根据查询条件，查询并返回Franchisee的列表
	 * 
	 * @param q
	 *            查询条件
	 * @return
	 */
	public List<Franchisee> getFranchiseeList(Query q);

	/**
	 * 根据查询条件，分页查询并返回Franchisee的分页结果
	 * 
	 * @param q
	 *            查询条件
	 * @return
	 */
	public PageFinder<Franchisee> getFranchiseePagedList(Query q);
	
	/**
	 * 查询已经审核的加盟商数据
	 */
	public PageFinder<Franchisee> getAuditFranchiseePagedList(Query q);

	/**
	 * 根据主键，查询并返回一个Franchisee对象
	 * 
	 * @param id
	 *            主键id
	 * @return
	 */
	public Franchisee getFranchisee(String id);

	/**
	 * 根据主键数组，查询并返回一组Franchisee对象
	 * 
	 * @param ids
	 *            主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<Franchisee> getFranchiseeByIds(String[] ids);

	/**
	 * 根据主键，删除特定的Franchisee记录
	 * 
	 * @param id
	 *            主键id
	 * @param operator
	 *            操作人对象
	 * @return
	 */
	public ResultInfo<Franchisee> delFranchisee(String id, Operator operator);

	/**
	 * 新增一条Franchisee记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * 
	 * @param franchisee
	 *            新增的Franchisee数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator
	 *            操作人对象
	 * @return
	 */
	public ResultInfo<Franchisee> addFranchisee(Franchisee franchisee, Operator operator);

	/**
	 * 根据主键，更新一条Franchisee记录
	 * 
	 * @param franchisee
	 *            更新的Franchisee数据，且其主键不能为空
	 * @param operator
	 *            操作人对象
	 * @return
	 */
	public ResultInfo<Franchisee> updateFranchisee(Franchisee franchisee, Operator operator);

	/**
	 * 生成主键
	 * 
	 * @return
	 */
	public String generatePK();

	/**
	 * 为Franchisee对象设置默认值
	 * 
	 * @param obj
	 */
	public void fillDefaultValues(Franchisee obj);

}
