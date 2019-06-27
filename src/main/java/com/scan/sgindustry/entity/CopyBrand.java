package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 抄牌单对象
 * @author fx
 *
 */
@Table(name = "t_erp_copybrand")
public class CopyBrand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select erp_copybrand_seq.nextval from dual")
	@Id
	@Column(name = "copybrand_id")
	private Integer copybrandId;//抄牌主表Id
	@Column(name = "notice_number")
	private String noticeNumber;//销售通知单号
	@Column(name = "user_number")
	private String userNumber;//抄牌人(作废人)
	@Column(name = "vehicle_number")
	private String vehicleNumber;//车船号
	@Column(name = "status")
	private String status;//状态，表示是否未打印：'0'未打印,'1'已打印,'99'作废
	@Column(name = "print_count")
	private Integer printCount;//打印次数,默认0次
	@Column(name = "create_time")
	private Date createTime;//添加时间
	
	public CopyBrand() {
		super();
	}
	

	public CopyBrand(Integer copybrandId, String noticeNumber, String userNumber, String vehicleNumber, String status,
			Integer printCount, Date createTime) {
		super();
		this.copybrandId = copybrandId;
		this.noticeNumber = noticeNumber;
		this.userNumber = userNumber;
		this.vehicleNumber = vehicleNumber;
		this.status = status;
		this.printCount = printCount;
		this.createTime = createTime;
	}

	public Integer getCopybrandId() {
		return copybrandId;
	}

	public void setCopybrandId(Integer copybrandId) {
		this.copybrandId = copybrandId;
	}

	public String getNoticeNumber() {
		return noticeNumber;
	}


	public void setNoticeNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}


	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "CopyBrand [copybrandId=" + copybrandId + ", NoticeNumber=" + noticeNumber + ", userNumber=" + userNumber
				+ ", vehicleNumber=" + vehicleNumber + ", status=" + status + ", printCount=" + printCount
				+ ", createTime=" + createTime + "]";
	}
	
	

}
