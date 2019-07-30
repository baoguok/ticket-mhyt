package org.linlinjava.litemall.wx.service;

import java.math.BigDecimal;

import org.linlinjava.litemall.core.express.dao.AliPayInfo;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

@Service
public class AliPayService {
	
	
	@Autowired
	private AliPayInfo alipay = new AliPayInfo();
	@Autowired
	private TicOrderService orderService;
	

	/**
	 * 二维码支付
	 * @param orderId
	 * @return
	 * @throws AlipayApiException
	 */
	public Object qrCode(Integer orderId) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(alipay.getOpen_api_domain(), alipay.getAppid(),
						alipay.getPrivate_key(), "json", "UTF-8", alipay.getPublic_key(), alipay.getSign_type());
		// 查询订单信息
		TicOrder ticOrder = orderService.findById(orderId);
		//创建API对应的request类
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		AlipayTradePayModel model = new AlipayTradePayModel();
	
		model.setOutTradeNo( ticOrder.getOrdPayId());
		model.setSellerId(alipay.getAppid());
		model.setTotalAmount(ticOrder.getOrdActualPrice()+"");
		model.setSubject("望湖庄园");
		request.setBizModel(model);
		/*request.setBizContent("{" +
						"    \"out_trade_no\":\"" + ticOrder.getOrdPayId() + "\"," +      // 订单编号
						"    \"seller_id\":\"" + alipay.getAppid() + "\"," +                       // 商户的Pid
						"    \"total_amount\":\""+ticOrder.getOrdActualPrice()+"\"," +    // 金额
						"    \"subject\":\"桂阳望湖生态园\"," +                            // 订单标题
						"    }");*/
		
		//通过alipayClient调用API，获得对应的response类
		AlipayTradePrecreateResponse response = alipayClient.execute(request);
		System.out.print(response.getBody());
		return response;
	}


	/**
	 * 付款码支付
	 * @param authCode
	 * @return
	 * @throws AlipayApiException
	 */
	public Object authCode( String authCode ,Integer orderId) throws AlipayApiException {
		System.out.println(authCode+","+orderId);
		AlipayClient alipayClient = new DefaultAlipayClient(alipay.getOpen_api_domain(), alipay.getAppid(),
				alipay.getPrivate_key(), "json", "UTF-8", alipay.getPublic_key(), alipay.getSign_type());
		// 查询订单信息
		TicOrder ticOrder = orderService.findById(orderId);

		// 支付价格
		BigDecimal ordActualPrice = ticOrder.getOrdActualPrice();
		
		AlipayTradePayRequest request = new AlipayTradePayRequest(); //创建API对应的request类
		AlipayTradePayModel model = new AlipayTradePayModel();
		model.setOutTradeNo(ticOrder.getOrdPayId());
		model.setScene("bar_code");
		model.setAuthCode(authCode);
		model.setSubject("望湖生态园");
		model.setTimeoutExpress("1m");
		model.setTotalAmount(ordActualPrice+"");
		request.setBizModel(model);

	/*	request.setBizContent("{" +
						"    \"out_trade_no\":\""+ticOrder.getOrdPayId()+"\"," +
						"    \"scene\":\"bar_code\"," +
						"    \"auth_code\":\""+authCode+"\"," +
						"    \"subject\":\"桂阳望湖生态园\"," +
						"    \"store_id\":\"NJ_001\"," +
						"    \"timeout_express\":\"2m\"," +
						"    \"total_amount\":\""+fee+"\"" +
						"  }"); //设置业务参数
		*/
		
		AlipayTradePayResponse response = alipayClient.execute(request); //通过alipayClient调用API，获得对应的response类
		System.out.print(response.getBody());
		// 根据response中的结果继续业务逻辑处理
		return response;
	}
	
	/**
	 * 支付宝退款
	 * @return
	 * @throws Exception
	 */
	public Object AliRefund(Integer orderId) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient(alipay.getOpen_api_domain(), alipay.getAppid(),
				alipay.getPrivate_key(), "json", "UTF-8", alipay.getPublic_key(), alipay.getSign_type());
		TicOrder ticOrder = orderService.findById(orderId);
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
	    model.setOutTradeNo(ticOrder.getOrdPayId()); //与预授权转支付商户订单号相同，代表对该笔交易退款
	    model.setRefundAmount(ticOrder.getOrdActualPrice()+"");		//金额
	    model.setRefundReason("望湖庄园");    
	    //model.setOutRequestNo("refund0000001");//标识一次退款请求，同一笔交易多次退款需要保证唯一，如部分退款则此参数必传。
	    AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
	    request.setBizModel(model);
	    AlipayTradeRefundResponse response = alipayClient.execute(request);
	    return response;
	}
}
