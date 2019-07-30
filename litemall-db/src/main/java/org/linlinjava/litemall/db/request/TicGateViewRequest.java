package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicGateView;
import org.linlinjava.litemall.db.domain.TicGoods;

public class TicGateViewRequest extends TicGateView {

	private Integer page;

	private Integer limit;
	
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
