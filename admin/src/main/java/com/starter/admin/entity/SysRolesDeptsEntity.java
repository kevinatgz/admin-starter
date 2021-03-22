package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色部门关联
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-22 21:38:55
 */
@Data
@TableName("sys_roles_depts")
public class SysRolesDeptsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long roleId;
	/**
	 * 
	 */
	private Long deptId;

}
