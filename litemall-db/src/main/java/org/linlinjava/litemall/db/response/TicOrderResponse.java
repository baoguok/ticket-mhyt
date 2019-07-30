package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicOrder;

import java.util.List;

public class TicOrderResponse extends TicOrder {
	// 门票类目名称
	private String cateName;

	//	门票介绍
	private String catDesc;

	// 允许人数
	private Integer catPeopleNum;

	//用户门票信息
	List<TicOrderGoodResponse> orderGoodsList;

	public Integer getCatPeopleNum() {
		return catPeopleNum;
	}

	public void setCatPeopleNum(Integer catPeopleNum) {
		this.catPeopleNum = catPeopleNum;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<TicOrderGoodResponse> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<TicOrderGoodResponse> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

}
