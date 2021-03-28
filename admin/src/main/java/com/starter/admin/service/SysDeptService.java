package com.starter.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.common.utils.PageUtils;
//import io.renren.common.utils.PageUtils;
//import io.renren.modules.generator.entity.SysDeptEntity;

import java.util.Map;

/**
 * 部门
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-21 20:00:49
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

