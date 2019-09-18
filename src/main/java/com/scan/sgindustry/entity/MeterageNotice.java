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
 * 计量通知单视图对象
 * @author fx
 *
 */
@Table(name = "VI_XSCC")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class MeterageNotice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	private String id; //通知单号
	@Column(name = "HTH")
	private String hth; //合同号
	@Column(name = "DJH")
	private String djh; //吊机号
	@Column(name = "DWH")
	private String dwh; //档位号
	@Column(name = "LH")
	private String lh; //炉号
	@Column(name = "CKH")
	private String ckh; //仓库号
	@Column(name = "CH")
	private String ch; //车号
	@Column(name = "TZSJ")
	private Date tzsj; //通知时间
	@Column(name = "BDH")
	private String bdh; //磅点号
	@Column(name = "JLBZ")
	private String jlbz; //计量标记
	@Column(name = "PRINTTIMES")
	private Integer printtimes; //打印次数(0)
	@Column(name = "DYRQ")
	private Date dyrq; //打印日期
	@Column(name = "JLJSRQ")
	private Date jljsrq; //计量接收日期
	@Column(name = "ZL")
	private Double zl; //重量
	@Column(name = "JS")
	private Integer js; //件数
	@Column(name = "FHBY1")
	private Double fhby1; //实发重量
	@Column(name = "FHBY2")
	private String fhby2; //承运日期
	@Column(name = "ZT")
	private String zt; //发货完成标记(T表示做过出库)
	@Column(name = "PERCENT")
	private Integer percent; //化验水份
	@Column(name = "NETVALUE")
	private Integer netvalue; //干基
	@Column(name = "gh")
	private String gh; //钢号
	@Column(name = "dwmc")
	private String dwmc; //单位名称
	@Column(name = "DBGSMC")
	private String dbgsmc; //代办公司名称
	@Column(name = "HPSH")
	private String hpsh; //计量通知标记
	@Column(name = "CC1")
	private Double cc1; //长
	@Column(name = "CC2")
	private Double cc2; //宽
	@Column(name = "CC3")
	private Double cc3; //高
	@Column(name = "XZ")
	private String xz; //形状
	@Column(name = "CPYZ")
	private String cpyz; //是否抄牌：1抄牌，0不抄牌
	
	

}
