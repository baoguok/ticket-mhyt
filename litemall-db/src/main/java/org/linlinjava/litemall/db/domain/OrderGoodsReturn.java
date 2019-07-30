package org.linlinjava.litemall.db.domain;

import java.math.BigDecimal;

public class OrderGoodsReturn extends TicOrderGoods {
	
	private String gooName;	// 门票名称
	private BigDecimal gooRetailPrice;	//	门票价格
	
	
	public String getGooName() {
		return gooName;
	}
	public void setGooName(String gooName) {
		this.gooName = gooName;
	}
	public BigDecimal getGooRetailPrice() {
		return gooRetailPrice;
	}
	public void setGooRetailPrice(BigDecimal gooRetailPrice) {
		this.gooRetailPrice = gooRetailPrice;
	}
	
	
}
