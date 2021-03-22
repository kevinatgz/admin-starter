package com.starter.admin.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.common.utils.PageUtils;
import com.starter.common.utils.Query;

import com.starter.admin.dao.SysRolesMenusDao;
import com.starter.admin.entity.SysRolesMenusEntity;
import com.starter.admin.service.SysRolesMenusService;


@Service("sysRolesMenusService")
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusDao, SysRolesMenusEntity> implements SysRolesMenusService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<SysRolesMenusEntity> page = this.page(
//                new Query<SysRolesMenusEntity>().getPage(params),
//                new QueryWrapper<SysRolesMenusEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}