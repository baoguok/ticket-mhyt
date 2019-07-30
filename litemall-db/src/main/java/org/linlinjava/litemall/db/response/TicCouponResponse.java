package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicCoupon;

public class TicCouponResponse extends TicCoupon {
	// 余票数量
	private Integer[] gooValue;

	public Integer[] getGooValue() {
		return gooValue;
	}

	public void setGooValue(Integer[] gooValue) {
		this.gooValue = gooValue;
	}
}
