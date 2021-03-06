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
 * 抄牌单对象
 * 
 * @author fx
 *
 */
@Table(name = "t_erp_copybrand")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString
// @EqualsAndHashCode
@NoArgsConstructor // 生成一个无参构造方法
@AllArgsConstructor // 会生成一个包含所有变量的构造方法
public class CopyBrand implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select
    // erp_copybrand_seq.nextval from dual")
    @Id
    @Column(name = "copybrand_id")
    private Integer copybrandId;// 抄牌主表Id
    @Column(name = "notice_number")
    private String noticeNumber;// 销售通知单号
    @Column(name = "user_number")
    private String userNumber;// 抄牌人(作废人)
    @Column(name = "vehicle_number")
    private String vehicleNumber;// 车船号
    @Column(name = "status")
    private String status;// 状态，表示是否未打印：'0'未打印,'1'已打印,'99'作废
    @Column(name = "print_count")
    private Integer printCount;// 打印次数,默认0次
    @Column(name = "create_time")
    private Date createTime;// 添加时间
    // 捆号由两位字母加炉批号加3位数字组成
    @Column(name = "SHEAF")
    private String sheaf;
    // 件数
    @Column(name = "QUANTITY")
    private Integer quantity;
    // 备注
    @Column(name = "REMARK")
    private String remark;

}
