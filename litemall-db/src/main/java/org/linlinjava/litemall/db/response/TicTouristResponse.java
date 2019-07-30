package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicTourist;

public class TicTouristResponse extends TicTourist {
	// 用户名
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
