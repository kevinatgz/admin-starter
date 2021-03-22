package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色菜单关联
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-22 21:38:55
 */
@Data
@TableName("sys_roles_menus")
public class SysRolesMenusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId
	private Long menuId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
