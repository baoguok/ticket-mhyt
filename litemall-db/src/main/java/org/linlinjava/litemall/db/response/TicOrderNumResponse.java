package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicOrder;

public class TicOrderNumResponse extends TicOrder {
	private String catName;

	private Integer num;

	private String actul;

	private String gooName;

	private String orgGoodsName;
	
	private Integer day;

	public String getOrgGoodsName() {
		return orgGoodsName;
	}

	public void setOrgGoodsName(String orgGoodsName) {
		this.orgGoodsName = orgGoodsName;
	}

	private Integer catPeopleNum;

	public Integer getCatPeopleNum() {
		return catPeopleNum;
	}

	public void setCatPeopleNum(Integer catPeopleNum) {
		this.catPeopleNum = catPeopleNum;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getActul() {
		return actul;
	}

	public void setActul(String actul) {
		this.actul = actul;
	}

	public String getGooName() {
		return gooName;
	}

	public void setGooName(String gooName) {
		this.gooName = gooName;
	}
}
