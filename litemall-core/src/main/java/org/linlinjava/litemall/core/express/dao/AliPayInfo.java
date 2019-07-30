package org.linlinjava.litemall.core.express.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value={"classpath:zfb.properties"})
public class AliPayInfo {

	@Value("${open_api_domain}")
	private String open_api_domain;
	
	@Value("${pid}")
	private String pid;
	
	@Value("${appid}")
	private String appid;
	
	@Value("${private_key}")
	private String private_key;
	
	@Value("${public_key}")
	private String public_key;
	
	@Value("${alipay_public_key}")
	private String alipay_public_key;
	
	@Value("${sign_type}")
	private String sign_type;

	public String getOpen_api_domain() {
		return open_api_domain;
	}

	public void setOpen_api_domain(String open_api_domain) {
		this.open_api_domain = open_api_domain;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getAlipay_public_key() {
		return alipay_public_key;
	}

	public void setAlipay_public_key(String alipay_public_key) {
		this.alipay_public_key = alipay_public_key;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

}
