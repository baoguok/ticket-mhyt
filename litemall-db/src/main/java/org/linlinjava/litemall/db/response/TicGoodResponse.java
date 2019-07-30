package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicGoods;

public class TicGoodResponse extends TicGoods{
	// 门票类目名称
	private String catName;
	
	//退票规则名称
	private String refName;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}
}
