package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicFeedback;

public class TicFeedBackResponse extends TicFeedback {
	// 用户名称
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
