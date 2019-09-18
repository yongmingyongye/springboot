package com.scan.sgindustry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户对象
 * 
 * @author fx
 *
 */
@Table(name = "t_bas_user")
@Data // IDE必须有lombok插件才能使用，该注解 包含@Getter @Setter @RequiredArgsConstructor @ToString
      // @EqualsAndHashCode
@NoArgsConstructor // 生成一个无参构造方法
@AllArgsConstructor // 会生成一个包含所有变量的构造方法
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    private String userId;// 用户编号
    @Column(name = "user_name")
    private String userName;// 用户名
    @Column(name = "login_name")
    private String loginName;// 登录名
    @Column(name = "password")
    private String password;// 登录密码
    @Column(name = "status")
    private String status;// 状态,'0':正常,'99':作废
    @Column(name = "create_time")
    private String createTime;// 创建时间
    @Column(name = "role")
    private String role;// 抄牌人角色编号，0为普通抄牌员，1为管理员

}
