package org.linlinjava.litemall.admin.dao;

import org.linlinjava.litemall.db.domain.TicOrder;

public class TicOrderReq extends TicOrder {
		// 数量
		private Integer num;

		// 售票员ID
		private Integer selId;

		// true: 取票，false:退票
		private boolean bool;

		public Integer getNum() {
				return num;
		}

		public void setNum(Integer num) {
				this.num = num;
		}

		public Integer getSelId() {
				return selId;
		}

		public void setSelId(Integer selId) {
				this.selId = selId;
		}

		public boolean isBool() {
				return bool;
		}

		public void setBool(boolean bool) {
				this.bool = bool;
		}
}
