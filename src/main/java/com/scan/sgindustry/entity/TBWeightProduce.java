package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TB_WEIGHT_PRODUCE")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class TBWeightProduce implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "GROWEIGHT")
	private Double groweight;//毛重
	@Column(name = "TARE")
	private Double tare;//皮重
	@Column(name = "SUTTLE")
	private Double suttle;//净重
	@Column(name = "TIME")
	private Date time;//计量时间	
	@Column(name = "ASSESSCODE")
	private String assesscode;//评定单号
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
	@Column(name = "IDENTIFITIME")
	private Date identifitime;//读取时间	
	@Column(name = "IDENTIFIER")
	private String identifier;//读取标识，F：初始化；T：ERP中入库完成
	@Column(name = "STOVENO")
	private String stoveno;//炉批号
	@Column(name = "ELIGIBLE")
	private String eligible;//是否合格,默认'T'
	@Column(name = "REQCODE")
	private String reqcode;//计量通知单编码
	@Column(name = "RECEIVEID")
	private String receiveid;//接受单ID
	@Column(name = "BY1")
	private String by1;//二维码扫描时的通知单号
	@Column(name = "BY2")
	private String by2;//二维码扫描人工号
	@Column(name = "BY3")
	private String by3;//二维码扫描人姓名
	@Column(name = "BY4")
	private String by4;
	@Column(name = "BY5")
	private String by5;
	@Column(name = "QUANTITY")
	private Integer quantity;//件数
	@Column(name = "AVERAGE")
	private Double average;//均重
	@Column(name = "STATUS")
	private String status;//0:初始化；99：实物出库完成
	@Column(name = "STEELNO")
	private String steelno;//钢铁号
	@Column(name = "SHEAF")
	private String sheaf;//捆号由两位字母加炉批号加3位数字组成
	@Column(name = "FORMNO")
	private String formno;//形状编码
	@Column(name = "LENGTH")
	private Double length;//长度
	@Column(name = "WIDTH")
	private Double width;//宽度（直径）
	@Column(name = "HIGH")
	private Double high;//高度
	@Column(name = "STANDARD")
	private String standard;//标准
	@Column(name = "EQUIPMENTNO")
	private String equipmentno;//设备编码
	@Column(name = "FORM")
	private String form;//形状（品名）
	@Column(name = "TEAMNO")
	private String teamno;//班次
	@Column(name = "EQUIPMENT")
	private String equipment;//设备名称
	@Column(name = "GRADE")
	private String grade;//等级
	@Column(name = "STANDARDNO")
	private String standardno;//标准编码
	@Column(name = "PRINTTIME")
	private Integer printtime;//标签打印次数
	@Column(name = "CONCODE")
	private String concode;//合同号
	@Column(name = "GAGE")
	private String gage;//是否定尺,默认'T'
	@Column(name = "RKTIME")
	private Date rktime;//入库时间
	@Column(name = "SCANTIME")
	private Date scanTime;//二维码扫描时间
	
}
