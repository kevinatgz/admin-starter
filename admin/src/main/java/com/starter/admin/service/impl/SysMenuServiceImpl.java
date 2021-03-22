package com.starter.admin.service.impl;


import com.starter.admin.entity.SysMenuEntity;
import com.starter.common.utils.PageUtils;
import com.starter.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.starter.admin.dao.SysMenuDao;
//import com.starter.admin.entity.SysMenuEntity;
import com.starter.admin.service.SysMenuService;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<SysMenuEntity> page = this.page(
//                new Query(params).getPage(),
//                new QueryWrapper<SysMenuEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}