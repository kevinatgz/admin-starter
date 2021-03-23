package com.starter.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.common.utils.PageUtils;
import com.starter.admin.entity.SysRoleEntity;

import java.util.Map;

/**
 * 角色表
 *
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-23 23:26:05
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

