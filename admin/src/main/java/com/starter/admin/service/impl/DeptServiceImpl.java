package com.starter.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.admin.dao.SysDeptDao;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.admin.service.DeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements DeptService {


}
