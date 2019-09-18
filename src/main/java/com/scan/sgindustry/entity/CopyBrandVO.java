package com.scan.sgindustry.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抄牌单对象VO类
 * 
 * @author fx
 *
 */
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString
      // @EqualsAndHashCode
@NoArgsConstructor // 生成一个无参构造方法
@AllArgsConstructor // 会生成一个包含所有变量的构造方法
public class CopyBrandVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer copybrandId;// 抄牌主表Id
    private String noticeNumber;// 销售通知单号
    private String userNumber;// 抄牌人(作废人)
    private String role;// 抄牌人的角色编号
    private String vehicleNumber;// 车船号
    private String status;// 状态，表示是否未打印：'0'未打印,'1'已打印,'99'作废
    private Integer printCount;// 打印次数,默认0次
    private Date createTime;// 添加时间
    private Date startTime;// 查询开始时间
    private Date endTime;// 查询开始时间

}
