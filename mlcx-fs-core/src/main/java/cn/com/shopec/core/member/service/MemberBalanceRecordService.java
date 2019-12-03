package cn.com.shopec.core.member.service;

import java.util.List;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.core.member.model.MemberBalanceRecord;

/**
 * MemberBalanceRecordService 服务接口类
 */
public interface MemberBalanceRecordService extends BaseService {

	/**
	 * 根据查询条件，查询并返回MemberBalanceRecord的列表
	 * 
	 * @param q
	 *            查询条件
	 * @return
	 */
	public List<MemberBalanceRecord> getMemberBalanceRecordList(Query q);

	/**
	 * 根据查询条件，分页查询并返回MemberBalanceRecord的分页结果
	 * 
	 * @param q
	 *            查询条件
	 * @return
	 */
	public PageFinder<MemberBalanceRecord> getMemberBalanceRecordPagedList(Query q);

	/**
	 * 根据主键，查询并返回一个MemberBalanceRecord对象
	 * 
	 * @param id
	 *            主键id
	 * @return
	 */
	public MemberBalanceRecord getMemberBalanceRecord(String id);

	/**
	 * 根据主键数组，查询并返回一组MemberBalanceRecord对象
	 * 
	 * @param ids
	 *            主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<MemberBalanceRecord> getMemberBalanceRecordByIds(String[] ids);

	/**
	 * 新增一条MemberBalanceRecord记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * 
	 * @param MemberBalanceRecord
	 *            新增的Member数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator
	 *            操作人对象
	 * @return
	 */
	public ResultInfo<MemberBalanceRecord> addMemberBalanceRecord(MemberBalanceRecord memberBalance, Operator operator);


	/**
	 * 生成主键
	 * 
	 * @return
	 */

	public String generatePK();


}
