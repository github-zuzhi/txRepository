package cn.com.shopec.core.ml.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.ml.model.ChargingGunInfo;
import cn.com.shopec.core.ml.vo.ChargingGunInfoVo;

/**
 * 充电枪 服务接口类
 */
public interface ChargingGunInfoService extends BaseService {

	/**
	 * 根据查询条件，查询并返回ChargingGunInfo的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<ChargingGunInfo> getChargingGunInfoList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回ChargingGunInfo的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<ChargingGunInfo> getChargingGunInfoPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个ChargingGunInfo对象
	 * @param id 主键id
	 * @return
	 */
	public ChargingGunInfo getChargingGunInfo(String id);

	/**
	 * 根据主键数组，查询并返回一组ChargingGunInfo对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<ChargingGunInfo> getChargingGunInfoByIds(String[] ids);
	
	/**
	 * 根据主键，删除特定的ChargingGunInfo记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<ChargingGunInfo> delChargingGunInfo(String id, Operator operator);
	
	/**
	 * 新增一条ChargingGunInfo记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param chargingGunInfo 新增的ChargingGunInfo数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<ChargingGunInfo> addChargingGunInfo(ChargingGunInfo chargingGunInfo, Operator operator);
	
	/**
	 * 根据主键，更新一条ChargingGunInfo记录
	 * @param chargingGunInfo 更新的ChargingGunInfo数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<ChargingGunInfo> updateChargingGunInfo(ChargingGunInfo chargingGunInfo, Operator operator);

	/**
	 * 生成主键
	 * @return
	 */
	public String generatePK();
	
		/**
	 * 为ChargingGunInfo对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(ChargingGunInfo obj);
	
	public PageFinder<ChargingGunInfoVo> getGunList(Query q);//VO 枪信息
	public ChargingGunInfoVo getGunListNo(String id);//VO 枪详情
		
}
