package com.scan.sgindustry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户对象
 * @author fx
 *
 */
@Table(name = "t_bas_user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	private String userId;//用户编号
	@Column(name = "user_name")
	private String userName;//用户名
	@Column(name = "login_name")
	private String loginName;//登录名
	@Column(name = "password")
	private String password;//登录密码
	@Column(name = "status")
	private String status;//状态,'0':正常,'99':作废
	@Column(name = "create_time")
	private String createTime;//创建时间
	
	
	public User() {
		super();
	}
	

	public User(String userId, String userName, String loginName, String password, String status, String createTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.status = status;
		this.createTime = createTime;
	}




	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", loginName=" + loginName + ", password="
				+ password + ", status=" + status + ", createTime=" + createTime + "]";
	}
	
	

}
