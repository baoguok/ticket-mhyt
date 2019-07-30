package org.linlinjava.litemall.wx.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;

import org.linlinjava.litemall.db.service.TicOrderService;
import org.linlinjava.litemall.wx.util.WXPayXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



@RestController
@RequestMapping("/wx/back")
@Validated
public class WxPayCabackController {

    @Autowired
    private TicOrderService orderService;

    @PostMapping("payback")
   public String PayBack(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        StringBuilder content = new StringBuilder();
        ServletInputStream in = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String itemStr = "";// 作为输出字符串的临时串，用于判断是否读取完毕
        while (null != (itemStr = reader.readLine())) {
            content.append(itemStr);
        }
        System.out.println(content.toString());
        try {
			Map<String, String> map = xmlToMap(content.toString());
			if (map.get("out_trade_no")!=null) {
				// 修改订单状态(by 订单编号)
                int i = orderService.updateOrderSn(map.get("out_trade_no"));
			}
			System.out.println(map.get("out_trade_no"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
   }

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            //WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }
}