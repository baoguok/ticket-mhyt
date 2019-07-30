package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicGoods;
import org.springframework.beans.factory.annotation.Value;

public class TicGoodRequest extends TicGoods{

	private Integer page;

	private Integer limit;
	
	// 类目名称
	private String catName;
	
	// 退票规则名称
	private String refName;

	// 景点集合
	private Integer[] viewIds;
	
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
	public Integer[] getViewIds() {
		return viewIds;
	}
	public void setViewIds(Integer[] viewIds) {
		this.viewIds = viewIds;
	}
}
