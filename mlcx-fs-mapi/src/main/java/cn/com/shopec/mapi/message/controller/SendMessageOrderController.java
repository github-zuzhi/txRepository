package cn.com.shopec.mapi.message.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import cn.com.shopec.core.marketing.service.SendMessageService;
import cn.com.shopec.core.member.service.MemberService;
import cn.com.shopec.core.order.model.Order;
import cn.com.shopec.core.order.service.OrderService;
import cn.com.shopec.mapi.common.controller.BaseController;
import cn.com.shopec.quartz.utils.MemberOrderQuartz;


/**
 * 订单计时器
 * */
public class SendMessageOrderController{
	private Log logger = LogFactory.getLog(SendMessageOrderController.class);
	@Resource
	private SendMessageService sendMessageService;
	
	public void execute() throws Exception {
		logger.info("--------扫描已预约，已结束，未支付的订单定时任务开始-------------");
		
		try {
			/**
			 *得到所有已预约的订单,并记录到消息表中
			 */
			sendMessageService.getorderStatusOne();
			/**
			 *得到所有已结束，未支付的订单,并记录到消息表中
			 */
			sendMessageService.getorderStatusThree();
		} catch (Exception e) {
			logger.error("---扫描已预约，已结束，未支付的订单出错，错误信息：" + e.getMessage(), e);
			return;
		}
		logger.info("--------扫描已预约，已结束，未支付的订单定时任务结束-------------");
	}
	
	
	
	
	
}
