package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统菜单
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-22 21:38:55
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long menuId;
	/**
	 * 上级菜单ID
	 */
	private Long pid;
	/**
	 * 子菜单数目
	 */
	private Integer subCount;
	/**
	 * 菜单类型
	 */
	private Integer type;
	/**
	 * 菜单标题
	 */
	private String title;
	/**
	 * 组件名称
	 */
	private String name;
	/**
	 * 组件
	 */
	private String component;
	/**
	 * 排序
	 */
	private Integer menuSort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 链接地址
	 */
	private String path;
	/**
	 * 是否外链
	 */
	private Boolean iFrame;
	/**
	 * 缓存
	 */
	private Boolean cache;
	/**
	 * 隐藏
	 */
	private Boolean hidden;
	/**
	 * 权限
	 */
	private String permission;
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
