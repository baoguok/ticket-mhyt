package org.linlinjava.litemall.db.domain;

public class GoodsStock  extends TicGoods{
	private Integer stock;	//库存

	private Integer playStock; // 显示库存(不变化)

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPlayStock() {
		return playStock;
	}

	public void setPlayStock(Integer playStock) {
		this.playStock = playStock;
	}
}
