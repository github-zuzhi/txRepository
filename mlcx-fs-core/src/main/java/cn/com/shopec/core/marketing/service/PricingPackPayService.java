package cn.com.shopec.core.marketing.service;

import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.shopec.common.Operator;
import cn.com.shopec.common.ResultInfo;
import cn.com.shopec.core.common.BaseService;
import cn.com.shopec.core.order.model.PricingPackOrder;
/**
 * 订单支付
 */
public interface PricingPackPayService extends BaseService {

	/**
	 * 微信统一下单
	 */
	public ResultInfo<SortedMap<String, String>> getCodeUrl(HttpServletRequest request,HttpServletResponse response,PricingPackOrder order);
	/**
	 * 根据app调起支付的参数，生成签名
	 * */
	 public ResultInfo<SortedMap<String, String>> getCodeUrlApp(HttpServletRequest request,HttpServletResponse response,String prepay_id,
	    		String nonceStr,String timeStamp);
	 /**
	  * 调用微信查询订单支付结果接口
	  * */
	 public ResultInfo<String> wxGetOrderPayResult(HttpServletRequest request, HttpServletResponse response,
				String orderNo);
	 /**
	 * 接受微信返回给商户后台的信息并修改订单信息
	 * */
	public void wxUpdateOrder(HttpServletRequest request, HttpServletResponse response,Operator operator);
	/**
	 * 支付宝商户后台返回orderStr给app端
	 * */
	public ResultInfo<String> alipayGetOrderStr(HttpServletRequest request, HttpServletResponse response, PricingPackOrder order);
	/**
	 * 支付宝返回给商户后台的异步支付结果信息，并修改订单信息
	 * */
	public void alipayUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	 

}
