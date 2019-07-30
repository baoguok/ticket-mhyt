package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicOrder;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class TicOrderRequest extends TicOrder{
	@Value("${page:1}")
	private Integer page;
	@Value("${limit:20}")
	private Integer limit;
	private List<Short> lsit = new ArrayList<Short>();
	public List<Short> getLsit() {
		return lsit;
	}
	public void setLsit(List<Short> lsit) {
		this.lsit = lsit;
	}
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
}
