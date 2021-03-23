package com.starter.admin.dao;

import com.starter.admin.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-23 23:26:05
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
}
