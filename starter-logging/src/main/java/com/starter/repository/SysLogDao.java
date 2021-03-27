package com.starter.repository;

//import com.starter.admin.entity.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.domain.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * 
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-26 22:22:09
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
