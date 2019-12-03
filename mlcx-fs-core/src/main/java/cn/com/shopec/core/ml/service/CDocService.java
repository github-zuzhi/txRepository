package cn.com.shopec.core.ml.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.ml.model.CDoc;

/**
 * 上传附件表 服务接口类
 */
public interface CDocService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CDoc的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CDoc> getCDocList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CDoc的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CDoc> getCDocPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CDoc对象
	 * @param id 主键id
	 * @return
	 */
	public CDoc getCDoc(String id);

	/**
	 * 根据主键数组，查询并返回一组CDoc对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CDoc> getCDocByIds(String[] ids);
	
	/**
	 * 根据主键，删除特定的CDoc记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<CDoc> delCDoc(String id, Operator operator);
	
	/**
	 * 新增一条CDoc记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param cDoc 新增的CDoc数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<CDoc> addCDoc(CDoc cDoc, Operator operator);
	
	/**
	 * 根据主键，更新一条CDoc记录
	 * @param cDoc 更新的CDoc数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public ResultInfo<CDoc> updateCDoc(CDoc cDoc, Operator operator);

	/**
	 * 生成主键
	 * @return
	 */
	public String generatePK();
	
		/**
	 * 为CDoc对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CDoc obj);
	//获取图片编号
	String getCDocNo(String biz_id);
		
}
