package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.starter.common.base.BaseEntity;
import lombok.Data;

/**
 * 用户角色关联
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-20 01:49:44
 */
@Data
@TableName("sys_users_roles")
public class SysUsersRolesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
