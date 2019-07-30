package org.linlinjava.litemall.admin.dao;

public class passData {
		// 人数
		private Integer number = 0;
		// 是否通过
		private Boolean pass = false;
		// 姓名
		private String name = "游客";
		// 时间
		private String time = "2020-07-07 77:77:77";

		public Integer getNumber() {
				return number;
		}

		public void setNumber(Integer number) {
				this.number = number;
		}

		public Boolean getPass() {
				return pass;
		}

		public void setPass(Boolean pass) {
				this.pass = pass;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getTime() {
				return time;
		}

		public void setTime(String time) {
				this.time = time;
		}
}
