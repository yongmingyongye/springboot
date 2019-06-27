package com.scan.sgindustry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WeightProduce信息的合并汇总
 * 按照stoveno进行分组，对suttle、quantity求和并统计stoveno
 * @author fx
 *s
 */
@Table(name = "TB_WEIGHT_PRODUCE")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class WeightProduceSummaryVO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//炉批号
	@Id
	@Column(name = "STOVENO")
	private String stoveno;
	//钢铁号
    @Column(name = "STEELNO")
    private String steelno;
	//净重
    @Column(name = "SUTTLES")
    private Double suttles;
    //件数
    @Column(name = "QUANTITYS")
    private Integer quantity;
    //数量
    @Column(name = "num")
    private Integer num;
}
