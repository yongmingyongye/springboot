package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TB_WEIGHT")
@Data// IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class TBWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "REQCODE")
	private String reqcode;//计量通知单编码
	@Column(name = "VEHICLENO")
	private String vehicleno;//车号
	@Column(name = "GROWEIGHT")
	private Double groweight;//毛重
	@Column(name = "TARE")
	private Double tare;//皮重
	@Column(name = "SUTTLE")
	private Double suttle;//净重
	@Column(name = "QUANTITY")
	private Integer quantity;//数量
	@Column(name = "PERCENT")
	private Double percent;//百分数（水分、含量）
	@Column(name = "NETVALUE")
	private Double netvalue;//净含量(干基)
	@Column(name = "TIME")
	private Date time;//计量时间	
	@Column(name = "ASSESSCODE")
	private String assesscode;//评定单号
	@Column(name = "SUBTRACT")
	private Double subtract;//扣杂
	@Column(name = "AVERAGE")
	private Double average;//均重
	@Column(name = "CANCELLATION")
	private Byte cancellation;//是否作废1为正常，0为作废
	@Column(name = "MASK")
	private String mask;//备注
	@Column(name = "SERIALNO")
	private Integer serialno;//序号
	@Column(name = "WORKNO")
	private String workno;//计量员工号
	@Column(name = "CNNAME")
	private String cnname;//计量员姓名
	@Column(name = "SCALES")
	private String scales;//计量磅点
	@Column(name = "DYCS")
	private Integer dycs;//打印次数
	@Column(name = "PDZT")
	private String pdzt;//评定状态
	@Column(name = "SHEAF")
	private String sheaf;//捆号由两位字母加10位数字组成
	@Column(name = "STOVENO")
	private String stoveno;//炉批号
	@Column(name = "GRADE")
	private String grade;//等级
	@Column(name = "AMOUNT")
	private Integer amount;//支数
	@Column(name = "ELIGIBLE")
	private String eligible;//是否合格,默认'T'
	@Column(name = "CMZT")
	private String cmzt;//出门状态0未开出门证 1已开出门证
	@Column(name = "CMZH")
	private String cmzh;//出门证号
	@Column(name = "CKBS")
	private String ckbs;//出库标识0未出库1已出库,默认'0'
	@Column(name = "CKID")
	private String ckid;//出库id号
	

}
