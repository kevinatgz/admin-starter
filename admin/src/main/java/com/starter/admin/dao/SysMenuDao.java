package com.starter.admin.dao;

import com.starter.admin.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统菜单
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-22 21:38:55
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
	
}
