package com.starter.admin.service.impl;

import com.starter.admin.dao.SysDeptDao;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.admin.service.SysDeptService;
import com.starter.common.utils.PageUtils;
import com.starter.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import io.renren.common.utils.PageUtils;
//import io.renren.common.utils.Query;
//
//import io.renren.modules.generator.dao.SysDeptDao;
//import io.renren.modules.generator.entity.SysDeptEntity;
//import io.renren.modules.generator.service.SysDeptService;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDeptEntity> page = this.page(
                new Query<SysDeptEntity>().getPage(params),
                new QueryWrapper<SysDeptEntity>()
        );

        return new PageUtils(page);
    }

}