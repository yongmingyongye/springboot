package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WeightProduce汇总信息与WeightProduceDetials对照
 * @author fx
 * @version 1.0.0
 */
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor //生成一个无参构造方法
@AllArgsConstructor //会生成一个包含所有变量的构造方法
public class WeightProduceSummaryAndDetailsVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private WeightProduceSummaryVO weightProduceSummary;
    private List<CopyBrandDetails> weightProduceDetails;

}
