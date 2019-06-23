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
 * 抄牌明细
 * @author fx
 *
 */
@Table(name = "t_erp_copybrand_details")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class CopyBrandDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "copybrand_id")
	private Integer copybrandId;//抄牌主表Id
	@Column(name = "notice_number")
	private String noticeNumber;//销售通知单号
	@Column(name = "steelno")
	private String steelno;//钢铁号
	@Column(name = "width")
	private Double width;//宽度（直径,规格）
	@Column(name = "suttle")
	private Double suttle;//净重
	@Column(name = "stoveno")
	private String stoveno;//炉批号
	@Column(name = "concode")
	private String concode;//合同号（生产号）
	@Column(name = "sheaf")
	private String sheaf;//捆号由两位字母加炉批号加3位数字组成
	@Column(name = "quantity")
	private Integer quantity;//件数
	@Column(name = "weight_produce_id")
	private Integer weightProduceId;//棒材计量编号
	@Column(name = "status")
	private String status;//状态，'0'：正常,'99'：作废,默认'0'
	@Column(name = "create_time")
	private Date createTime;//创建时间
	@Column(name = "create_user")
	private String createUser;//创建人工号

}
