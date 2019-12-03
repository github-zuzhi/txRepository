package cn.com.shopec.mgt.car.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.common.constants.Constant;
import cn.com.shopec.common.utils.ECDateUtils;
import cn.com.shopec.core.car.model.CarBrand;
import cn.com.shopec.core.car.model.CarModel;
import cn.com.shopec.core.car.model.CarPreiod;
import cn.com.shopec.core.car.model.CarSeries;
import cn.com.shopec.core.car.service.CarBrandService;
import cn.com.shopec.core.car.service.CarModelService;
import cn.com.shopec.core.car.service.CarPreiodService;
import cn.com.shopec.core.car.service.CarSeriesService;
import cn.com.shopec.core.common.PageFinder;
import cn.com.shopec.core.common.Query;
import cn.com.shopec.mgt.common.BaseController;

@Controller
@RequestMapping("carModelAll")
public class CarModelAllController extends BaseController {

	@Resource
	private CarBrandService carBrandService;
	@Resource
	private CarSeriesService  carSeriesService;
	@Resource
	public CarPreiodService carPreiodService;
	@Resource
	public CarModelService carModelService;

	
	@Override
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/*
	 * 显示车辆型号列表页
	 */
	@RequestMapping("toCarModelList")
	public String mainPage(ModelMap map) {
		List<CarBrand> carBrandList = carBrandService.getCarBrandList(new Query());
		map.put("carBrandList", carBrandList);
		return "car/car_modelAll_list";
	}

	/*
	 * 显示车型列表页
	 */
	@RequestMapping("pageListCarModelAll")
	@ResponseBody
	public PageFinder<CarSeries> pageListCarSeries(@ModelAttribute("carSeries") CarSeries carSeries, Query query) {
		Query q = new Query(query.getPageNo(), query.getPageSize(), carSeries);
		return carSeriesService.getMgtCarSeriesPagedList(q);
	}

	/*
	 * 显示车型增加页
	 */
	@RequestMapping("toCarModelAllAdd")
	public String toCarSeriesAdd(ModelMap map) {
		List<CarBrand> carBrandList = carBrandService.getCarBrandList(new Query());
		map.put("carBrandList", carBrandList);
		return "car/car_modelAll_add";
	}

	/*
	 * 显示车型修改页
	 */
	@RequestMapping("toCarModelAllEdit")
	public String toCarSeriesEdit(String carSeriesId, ModelMap map) {
		List<CarBrand> carBrandList = carBrandService.getCarBrandList(new Query());
		map.put("carBrandList", carBrandList);
		CarSeries carSeries = carSeriesService.getCarSeries(carSeriesId);
		map.put("carSeries", carSeries);
		return "car/car_modelAll_edit";
	}

	/*
	 * 新增或修改车型
	 */
	@RequestMapping("editCarModelAll")
	@ResponseBody
	public ResultInfo<CarSeries> editCarModelAll(@ModelAttribute("carSeries") CarSeries carSeries) {
		ResultInfo<CarSeries> resultInfo = new ResultInfo<CarSeries>();
		if (carSeries.getCarSeriesId() != null && carSeries.getCarSeriesId().length() != 0) {
			resultInfo = carSeriesService.updateCarSeries(carSeries, getOperator());
		} else {
			resultInfo = carSeriesService.addCarSeries(carSeries, getOperator());
		}
		return resultInfo;
	}

	/*
	 * 删除车系
	 */
	@RequestMapping("delCarModelAll")
	@ResponseBody
	public ResultInfo<CarSeries> delCarSeries(String carSeriesId) {
		ResultInfo<CarSeries> result = new ResultInfo<CarSeries>();
		CarPreiod carPreiod = new CarPreiod();
		carPreiod.setCarSeriesId(carSeriesId);
		List<CarPreiod> carPreiodList = carPreiodService.getCarPreiodList(new Query(carPreiod));
		if(carPreiodList.size()>0){
			result.setCode(Constant.FAIL);
			result.setMsg("有车辆年代隶属于当前车系，不能删除");
			return result;
		}else{
			CarModel carModel = new CarModel();
			carModel.setCarSeriesId(carSeriesId);
			carModel.setOnOffLineStatus(1);
			List<CarModel> carModelList = carModelService.getCarModelList(new Query(carModel));
			if(carModelList.size()>0){
				result.setCode(Constant.FAIL);
				result.setMsg("上架车型中有当前车系，不能删除");
				return result;
			}else{
				return carSeriesService.delCarSeries(carSeriesId, getOperator());
			}
		}
	}

	/*
	 * 验证车型名称是否重复
	 */
	@RequestMapping("carModelAllNameCheck")
	@ResponseBody
	public ResultInfo<String> carModelAllNameCheck(String carSeriesName,String carSeriesId) {
		ResultInfo<String> resultInfo = new ResultInfo<String>();
		CarSeries Series = new CarSeries();
		Series.setCarSeriesName(carSeriesName);
		List<CarSeries> list = carSeriesService.getCarSeriesList(new Query(Series));
		if (list.size() > 0) {
			if(carSeriesId!=null&&!"".equals(carSeriesId)){
				CarSeries carSeries = list.get(0);
				if(carSeries!=null&&carSeriesId.equals(carSeries.getCarSeriesId())){
					resultInfo.setCode(Constant.NO + "");
				}else{
					resultInfo.setCode(Constant.YES + "");
				}
			}else{
				resultInfo.setCode(Constant.YES + "");
			}
		} else {
			resultInfo.setCode(Constant.NO + "");
		}
		return resultInfo;
	}

}
