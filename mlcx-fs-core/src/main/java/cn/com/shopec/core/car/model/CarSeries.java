package cn.com.shopec.core.car.model;

import java.util.Date;

import cn.com.shopec.core.common.Entity;

/** 
 * 车系表 数据实体类
 */
public class CarSeries extends Entity<String> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//车系id
	private String carSeriesId;
	//车系名称
	private String carSeriesName;
	//车辆品牌id
	private String carBrandId;
	//车辆品牌名称
	private String carBrandName;
	//车系图标
	private String carSeriresPic;
	//创建时间
	private Date createTime;
	//创建时间 时间范围起（查询用）
	private Date createTimeStart;
	//创建时间 时间范围止（查询用）
	private Date createTimeEnd;	
	//修改时间
	private Date updateTime;
	//修改时间 时间范围起（查询用）
	private Date updateTimeStart;
	//修改时间 时间范围止（查询用）
	private Date updateTimeEnd;	
	//操作人id
	private String operatorId;
	//操作人类型
	private Integer operatorType;
	// 座位数
	private String seaTing;
	
	/*Auto generated properties end*/
	//车系名称（供后台查询使用）
	private String carSeriesNameQuery;
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public String getPK(){
		return carSeriesId;
	}
	
	public String getCarSeriesId(){
		return carSeriesId;
	}
	
	public void setCarSeriesId(String carSeriesId){
		this.carSeriesId = carSeriesId;
	}
	
	public String getCarSeriesName(){
		return carSeriesName;
	}
	
	public void setCarSeriesName(String carSeriesName){
		this.carSeriesName = carSeriesName;
	}
	
	public String getCarBrandId(){
		return carBrandId;
	}
	
	public void setCarBrandId(String carBrandId){
		this.carBrandId = carBrandId;
	}
	
	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarSeriresPic(){
		return carSeriresPic;
	}
	
	public void setCarSeriresPic(String carSeriresPic){
		this.carSeriresPic = carSeriresPic;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTimeStart(){
		return createTimeStart;
	}
	
	public void setCreateTimeStart(Date createTimeStart){
		this.createTimeStart = createTimeStart;
	}
	
	public Date getCreateTimeEnd(){
		return createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}	
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTimeStart(){
		return updateTimeStart;
	}
	
	public void setUpdateTimeStart(Date updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}
	
	public Date getUpdateTimeEnd(){
		return updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(Date updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}	
	
	public String getOperatorId(){
		return operatorId;
	}
	
	public void setOperatorId(String operatorId){
		this.operatorId = operatorId;
	}
	
	public Integer getOperatorType(){
		return operatorType;
	}
	
	public void setOperatorType(Integer operatorType){
		this.operatorType = operatorType;
	}
	public String getCarSeriesNameQuery() {
		return carSeriesNameQuery;
	}
	
	public void setCarSeriesNameQuery(String carSeriesNameQuery) {
		this.carSeriesNameQuery = carSeriesNameQuery;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	

	@Override
	public String toString() {
		return "CarSeries ["
		 + "carSeriesId = " + carSeriesId + ", carSeriesName = " + carSeriesName + ", carBrandId = " + carBrandId + ", carSeriresPic = " + carSeriresPic
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", operatorId = " + operatorId + ", operatorType = " + operatorType
		+"]";
	}

	public String getSeaTing() {
		return seaTing;
	}

	public void setSeaTing(String seaTing) {
		this.seaTing = seaTing;
	}
}
