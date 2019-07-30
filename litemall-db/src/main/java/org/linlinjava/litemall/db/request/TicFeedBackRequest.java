package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicFeedback;
import org.springframework.beans.factory.annotation.Value;

public class TicFeedBackRequest extends TicFeedback {

	@Value("1")
	private Integer page;
	@Value("20")
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
