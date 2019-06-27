package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码扫描信息DTO对象
 * @author fx
 * @version 1.0.0
 * 此对象原先为copyBrandDetail表的对象，因业务调整不在使用该表，而客户端已与此对象对接，
 * 在不影响客户端的使用的情况下，不修改此类的名称的，仅仅更换对照表和关联字段
 */
@Table(name = "TB_WEIGHT_PRODUCE")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class CopyBrandDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//棒材计量编号
	@Id
	@Column(name = "ID")
	private Integer id;
	//销售通知单号,用于反写二维扫描后填写对应抄牌的通知单号
	@Column(name = "BY1")
	private String noticeNumber;
	//钢铁号
	@Column(name = "STEELNO")
	private String steelno;
	//宽度（直径,规格）
	@Column(name = "WIDTH")
	private Double width;
	//净重
	@Column(name = "SUTTLE")
	private Double suttle;
	//炉批号
	@Column(name = "STOVENO")
	private String stoveno;
	//合同号（生产号）
	@Column(name = "CONCODE")
	private String concode;
	//捆号由两位字母加炉批号加3位数字组成
	@Column(name = "SHEAF")
	private String sheaf;
	//件数
	@Column(name = "QUANTITY")
	private Integer quantity;
	//扫描操作人工号
	@Column(name = "BY2")
	private String scanOperatorWorkNo;
	//扫描操作人姓名
	@Column(name = "BY3")
	private String scanOperatorName;
	//二维码扫描时间
	@Column(name = "SCANTIME")
	private Date scanTime;
	@Column(name = "STATUS")
    private String status;//0:初始化；99：实物出库完成

}
