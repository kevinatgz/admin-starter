package com.starter.admin.dao;

//import io.renren.modules.generator.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.admin.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-21 20:00:49
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {
	
}
