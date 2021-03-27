package com.starter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.common.utils.PageUtils;
import com.starter.domain.Log;
import com.starter.domain.SysLogEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
//import com.starter.admin.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 *
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-26 22:22:09
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //    /**
//     * 保存日志数据
//     * @param username 用户
//     * @param browser 浏览器
//     * @param ip 请求IP
//     * @param joinPoint /
//     * @param log 日志实体
//     */
    @Async
    void saveLog(String username, String browser, String ip, ProceedingJoinPoint joinPoint, SysLogEntity log);

}

