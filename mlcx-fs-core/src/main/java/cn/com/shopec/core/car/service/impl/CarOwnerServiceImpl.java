package cn.com.shopec.core.car.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.common.constants.Constant;
import cn.com.shopec.common.exception.xls.XlsImportException;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import cn.com.shopec.core.car.dao.CarOwnerDao;
import cn.com.shopec.core.car.model.CarOwner;
import cn.com.shopec.core.car.service.CarOwnerService;

/**
 * 车主表 服务实现类
 */
@Service
public class CarOwnerServiceImpl implements CarOwnerService {

	private static final Log log = LogFactory.getLog(CarOwnerServiceImpl.class);
	
	@Resource
	private CarOwnerDao carOwnerDao;
	

	/**
	 * 根据查询条件，查询并返回CarOwner的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarOwner> getCarOwnerList(Query q) {
		List<CarOwner> list = null;
		try {
			//直接调用Dao方法进行查询
			list = carOwnerDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOwner>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarOwner的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarOwner> getCarOwnerPagedList(Query q) {
		PageFinder<CarOwner> page = new PageFinder<CarOwner>();
		
		List<CarOwner> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = carOwnerDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carOwnerDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOwner>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		page.setData(list);
		page.setRowCount(rowCount);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarOwner对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarOwner getCarOwner(String id) {
		CarOwner obj = null;
		if (id == null || id.length() == 0) { //传入的主键无效时直接返回null
			log.info(Constant.ERR_MSG_INVALID_ARG + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carOwnerDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarOwner对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarOwner> getCarOwnerByIds(String[] ids) {
		List<CarOwner> list = null;
		if (ids == null || ids.length == 0) {
			log.info(Constant.ERR_MSG_INVALID_ARG + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carOwnerDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOwner>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarOwner记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	@Transactional
	public ResultInfo<CarOwner> delCarOwner(String id, Operator operator) {
		ResultInfo<CarOwner> resultInfo = new ResultInfo<CarOwner>();
		if (id == null || id.length() == 0) { //传入的主键无效时直接返回失败结果
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg(Constant.ERR_MSG_INVALID_ARG );
			log.info(Constant.ERR_MSG_INVALID_ARG + " id = " + id);
			return resultInfo;
		}
		try {
		    //调用Dao执行删除，并判断删除语句执行结果
			int count = carOwnerDao.delete(id);
			if (count > 0) {
				resultInfo.setCode(Constant.SECCUESS);
			} else {
				resultInfo.setCode(Constant.FAIL);
				resultInfo.setMsg(Constant.ERR_MSG_DATA_NOT_EXISTS);
			}		  
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg(Constant.ERR_MSG_EXCEPTION_CATCHED);
		}
		return resultInfo;
	}
		
	/**
	 * 新增一条CarOwner记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carOwner 新增的CarOwner数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	@Transactional
	public ResultInfo<CarOwner> addCarOwner(CarOwner carOwner, Operator operator) {
		ResultInfo<CarOwner> resultInfo = new ResultInfo<CarOwner>();
		
		if (carOwner == null) { //传入参数无效时直接返回失败结果
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg(Constant.ERR_MSG_INVALID_ARG );
			log.info(Constant.ERR_MSG_INVALID_ARG + " carOwner = " + carOwner);
		} else {
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carOwner.getOwnerId() == null) {
					carOwner.setOwnerId(this.generatePK());
				}
				//如果传入的操作人不为null，则设置新增记录的操作人类型和操作人id
				if (operator != null) {
					carOwner.setOperatorType(operator.getOperatorType());
					carOwner.setOperatorId(operator.getOperatorId());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = new Date();
				carOwner.setCreateTime(now);
				carOwner.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carOwner);
				
				//调用Dao执行插入操作
				carOwnerDao.add(carOwner);
				resultInfo.setCode(Constant.SECCUESS);
				resultInfo.setData(carOwner);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				resultInfo.setCode(Constant.FAIL);
				resultInfo.setMsg(Constant.ERR_MSG_EXCEPTION_CATCHED);
			}	
		}
		
		return resultInfo;
	}			
	
	/**
	 * 根据主键，更新一条CarOwner记录
	 * @param carOwner 更新的CarOwner数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	@Transactional
	public ResultInfo<CarOwner> updateCarOwner(CarOwner carOwner, Operator operator) {
		ResultInfo<CarOwner> resultInfo = new ResultInfo<CarOwner>();
		
		if (carOwner == null || carOwner.getOwnerId() == null) { //传入参数无效时直接返回失败结果
			resultInfo.setCode(Constant.FAIL);
			resultInfo.setMsg(Constant.ERR_MSG_INVALID_ARG );
			log.info(Constant.ERR_MSG_INVALID_ARG + " carOwner = " + carOwner);
		} else {
			try {
				//如果传入的操作人不为null，则设置更新记录的操作人类型和操作人id
				if (operator != null) {
					carOwner.setOperatorType(operator.getOperatorType());
					carOwner.setOperatorId(operator.getOperatorId());
				}
				
				//设置更新时间为当前时间
				carOwner.setUpdateTime(new Date());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				int count = carOwnerDao.update(carOwner);			
				if (count > 0) {
					resultInfo.setCode(Constant.SECCUESS);
				} else {
					resultInfo.setCode(Constant.FAIL);
				}
				resultInfo.setData(carOwner);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				resultInfo.setCode(Constant.FAIL);
				resultInfo.setMsg(Constant.ERR_MSG_EXCEPTION_CATCHED);
			}
		}
		
		return resultInfo;
	}	
	
	/**
	 * 生成主键
	 * @return
	 */
	public String generatePK() {
		return String.valueOf(new Date().getTime() * 1000000 + new Random().nextInt(1000000));
	}
	
	/**
	 * 为CarOwner对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarOwner obj) {
		if (obj != null) {
		    if (obj.getIsAvailable() == null) {
		    	obj.setIsAvailable(1);
		    }
		    if (obj.getCencorStatus() == null) {
		    	obj.setCencorStatus(0);
		    }
		}
	}

	@Override
	public ResultInfo<CarOwner> importCarOwner(MultipartFile multipartFile, Operator operator) throws Exception {
		ResultInfo<CarOwner> resultInfo = new ResultInfo<CarOwner>();
		Sheet[] sheet = null;
		jxl.Workbook rwb = null;
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			try {
				String resImgPath = request.getSession().getServletContext().getRealPath("/");
				String filePath = resImgPath + "/xls/";
				File file = new File(filePath);
				if (!file.exists() && !file.isDirectory()) {
					file.mkdirs();
				}
				String filenameReal = filePath + System.currentTimeMillis() + "导入缓存文件.xls";

				File logoSaveFile = new File(filenameReal);
				multipartFile.transferTo(logoSaveFile);

				FileInputStream fis = new FileInputStream(logoSaveFile);
				rwb = Workbook.getWorkbook(fis);
				sheet = rwb.getSheets();
			} catch (Exception e) {
				throw new XlsImportException("文件操作异常！");
			}
			for (int i = 0; i < sheet.length; i++) {
				Sheet rs = rwb.getSheet(i);
				for (int j = 0; j < rs.getRows(); j++) {
					if (j == 0)
						continue;
					try {
						//名称，全称，类型，联系人，联系人电话，联系人号码，联系人邮箱，身份证号码，工商营业执照，组织机构代码，税务登记证
						Cell[] cells = rs.getRow(j);
						String ownerName = "";// 名称
						String ownerFullName = "";// 全称
						String contactPerson="";//联系人
						String contactTel = "";// 联系人电话
						String contactPhone = "";// 联系人号码
						String contactEmail = "";//联系人邮箱
						String idCardNo="";//身份证号码
						String bizLicenseNo = "";// 工商营业执照
						String organizationCode = "";// 组织机构代码
						String taxRegistration = "";//税务登记证
						if(cells[0].getContents()!=null&&!"".equals(cells[0].getContents())){
							ownerName = cells[0].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，名称必填！");
							return resultInfo;
						}
						if(cells[1].getContents()!=null&&!"".equals(cells[1].getContents())){
							ownerFullName = cells[1].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，全称必填！");
							return resultInfo;
						}
						if(cells[3].getContents()!=null&&!"".equals(cells[3].getContents())){
							contactPerson = cells[3].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，联系人必填！");
							return resultInfo;
						}
						if(cells[4].getContents()!=null&&!"".equals(cells[4].getContents())){
							contactTel = cells[4].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，联系人电话必填！");
							return resultInfo;
						}
						if(cells[5].getContents()!=null&&!"".equals(cells[5].getContents())){
							contactPhone = cells[5].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，联系人号码必填！");
							return resultInfo;
						}
						if(cells[6].getContents()!=null&&!"".equals(cells[6].getContents())){
							contactEmail = cells[6].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，联系人邮箱必填！");
							return resultInfo;
						}
						if(cells[7].getContents()!=null&&!"".equals(cells[7].getContents())){
							idCardNo = cells[7].getContents().trim();
						}else{
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，身份证号码必填！");
							return resultInfo;
						}
						if(cells[2].getContents() != null&&!"".equals(cells[2].getContents()) && cells[2].getContents().trim().equals("公司")){
							if(cells[8].getContents()!=null&&!"".equals(cells[8].getContents())){
								bizLicenseNo = cells[8].getContents().trim();
							}else{
								resultInfo.setCode(Constant.FAIL);
								resultInfo.setMsg("第" + (j + 1) + "行，工商营业执照号必填！");
								return resultInfo;
							}
							
							if(cells[9].getContents()!=null&&!"".equals(cells[9].getContents().trim())){
								organizationCode = cells[9].getContents().trim();
							}else{
								resultInfo.setCode(Constant.FAIL);
								resultInfo.setMsg("第" + (j + 1) + "行，组织机构代码必填！");
								return resultInfo;
							}
							
							if(cells[10].getContents()!=null&&!"".equals(cells[10].getContents().trim())){
								taxRegistration = cells[10].getContents().trim();
							}else{
								resultInfo.setCode(Constant.FAIL);
								resultInfo.setMsg("第" + (j + 1) + "行，税务登记证必填！");
								return resultInfo;
							}
						}
						
						
						
						Integer type=0;
						try {
							if(cells[2].getContents().trim().equals("个人")){
								type = 2;
							}else if(cells[2].getContents().trim().equals("公司")){
								type = 1;
							}
						} catch (Exception e) {
							resultInfo.setCode(Constant.FAIL);
							resultInfo.setMsg("第" + (j + 1) + "行，类型错误！");
							return resultInfo;
						}
						CarOwner coA=new CarOwner();
						coA.setOwnerName(ownerName);
						coA.setOwnerFullName(ownerFullName);
						coA.setOwnerType(type);
						coA.setContactPerson(contactPerson);
						coA.setContactTel(contactTel);
						coA.setContactPhone(contactPhone);
						coA.setContactEmail(contactEmail);
						coA.setIdCardNo(idCardNo);
						coA.setBizLicenseNo(bizLicenseNo);
						coA.setOrganizationCode(organizationCode);
						coA.setTaxRegistration(taxRegistration);
						addCarOwner(coA, operator);
					} catch (Exception e) {
						e.printStackTrace();
						resultInfo.setCode(Constant.FAIL);
						if (e instanceof XlsImportException)
							throw new XlsImportException(((XlsImportException) e).getErrorMsg());
						else
							throw new XlsImportException("第" + (j + 1) + "行出错！数据有误！");
					}
				}

			}
			resultInfo.setData(null);
			resultInfo.setCode(Constant.SECCUESS);
		} catch (Exception e) {
			if (e instanceof XlsImportException)
				throw new XlsImportException(((XlsImportException) e).getErrorMsg());
			else
				throw new XlsImportException("数据有误！");
		}
		return resultInfo;
	}

}
