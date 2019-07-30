package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicAd;

public class TicAdResponse extends TicAd {
	// 类目名称
	private String adcName;

	public String getAdcName() {
		return adcName;
	}

	public void setAdcName(String adcName) {
		this.adcName = adcName;
	}
}
