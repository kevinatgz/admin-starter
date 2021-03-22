package com.starter.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.admin.entity.SysMenuEntity;
import com.starter.common.utils.PageUtils;
//import com.starter.common.utils.PageUtils;
//import com.starter.admin.entity.SysMenuEntity;

import java.util.Map;

/**
 * 系统菜单
 *
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-22 21:38:55
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

