package cn.com.shopec.core.dailyrental.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.dailyrental.model.MerchantUser;

/**
 * MerchantUser 服务接口类
 */
public interface MerchantUserService extends BaseService {

	/**
	 * 根据查询条件，查询并返回MerchantUser的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<MerchantUser> getMerchantUserList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回MerchantUser的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<MerchantUser> getMerchantUserPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个MerchantUser对象
	 * @param id 主键id
	 * @return
	 */
	public MerchantUser getMerchantUser(String id);

	/**
	 * 根据主键数组，查询并返回一组MerchantUser对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<MerchantUser> getMerchantUserByIds(String[] ids);
	
	/**
	 * 根据主键，删除特定的MerchantUser记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<MerchantUser> delMerchantUser(String id, Operator operator);
	
	/**
	 * 新增一条MerchantUser记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param merchantUser 新增的MerchantUser数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<MerchantUser> addMerchantUser(MerchantUser merchantUser, Operator operator);
	
	/**
	 * 根据主键，更新一条MerchantUser记录
	 * @param merchantUser 更新的MerchantUser数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<MerchantUser> updateMerchantUser(MerchantUser merchantUser, Operator operator);
	
	/**
	 * 根据商家ID和会员手机查询商家用户
	 * @param merchantId
	 * @param mobilePhone
	 * @return
	 */
	MerchantUser getByMobilePhone(String merchantId,String mobilePhone,String password);
	
	/**
	 * 根据token查询商家用户
	 * @param token
	 * @param userNo
	 * @return
	 */
	MerchantUser getByToken(String token,String userNo);
	
	/**
	 * 生成主键
	 * @return
	 */
	public String generatePK();
	
		/**
	 * 为MerchantUser对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(MerchantUser obj);
	
	public ResultInfo<MerchantUser> updateMerchantUserByMerchantId(MerchantUser merchantUser);
		
}
