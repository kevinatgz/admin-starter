package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.starter.common.base.BaseEntity;
import lombok.Data;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-20 01:49:44
 */
@Data
@TableName("sys_user")
public class SysUserEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	private List<Role> roles = new ArrayList<>();

	/**
	 * ID
	 */
	@TableId
	private Long userId;
	/**
	 * 部门名称
	 */
	private Long deptId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 头像地址
	 */
	private String avatarName;
	/**
	 * 头像真实路径
	 */
	private String avatarPath;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否为admin账号
	 */
	private Boolean isAdmin;
	/**
	 * 状态：1启用、0禁用
	 */
	private Long enabled;
	/**
	 * 创建者
	 */
//	private String createBy;
//	/**
//	 * 更新着
//	 */
//	private String updateBy;
	/**
	 * 修改密码的时间
	 */
	private Date pwdResetTime;
	/**
	 * 创建日期
	 */
//	private Date createTime;
//	/**
//	 * 更新时间
//	 */
//	private Date updateTime;

}
