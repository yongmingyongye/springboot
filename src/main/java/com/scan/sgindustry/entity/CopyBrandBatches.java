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
 * 同一计量通知号在不同地点多次抄牌记录表 分批抄牌
 * 
 * @author Administrator
 * @date 2019年8月7日
 * @version 1.0
 *
 */
@Table(name = "t_erp_copybrand_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyBrandBatches implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 分批抄牌的id
    @Id
    @Column(name = "id")
    private Integer id;
    // 分批抄牌的编号,例如:计量通知号_01
    @Column(name = "batche_id")
    private String batcheId;
    // 销售通知单号
    @Column(name = "notice_number")
    private String noticeNumber;
    // 操作人工号
    @Column(name = "operator")
    private String operator;
    // 操作人姓名
    @Column(name = "operator_name")
    private String operatorName;
    // 操作时间时间
    @Column(name = "operator_time")
    private Date operationTime;
    // 状态,0：新建，99：作废
    @Column(name = "status")
    private String status;

}
