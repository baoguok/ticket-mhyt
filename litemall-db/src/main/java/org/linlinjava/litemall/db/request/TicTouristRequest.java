package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicTourist;

public class TicTouristRequest extends TicTourist {

	private Integer page;

	private Integer limit;

	private String userName;


	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
