package org.linlinjava.litemall.db.response;

public class TicStaResponse {
	// 余票数量
	private Integer residual;
	
	// 已售数量
	private Integer sold;

	// 已退数量
	private Integer retired;
	
	// 已售总数
	private Integer staTotal;

	public Integer getResidual() {
		return residual;
	}

	public void setResidual(Integer residual) {
		this.residual = residual;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Integer getRetired() {
		return retired;
	}

	public void setRetired(Integer retired) {
		this.retired = retired;
	}

	public Integer getStaTotal() {
		return staTotal;
	}

	public void setStaTotal(Integer staTotal) {
		this.staTotal = staTotal;
	}
	
	
}
