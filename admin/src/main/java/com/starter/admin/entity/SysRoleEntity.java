package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;

/**
 * 角色表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-21 15:36:29
 */
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long roleId;

	@TableField(exist = false)
	private Set<Menu> menus;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 角色级别
	 */
	private Integer level;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 数据权限
	 */
	private String dataScope;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
