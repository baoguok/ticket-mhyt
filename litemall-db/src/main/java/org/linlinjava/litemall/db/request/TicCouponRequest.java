package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicCoupon;
import org.springframework.beans.factory.annotation.Value;

public class TicCouponRequest extends TicCoupon {
	@Value("1")
	private Integer page;
	@Value("20")
	private Integer limit;
	private String sort;
	private String order;
	
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
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
