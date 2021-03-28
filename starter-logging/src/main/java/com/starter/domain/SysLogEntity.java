package com.starter.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统日志
 *
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-26 22:22:09
 */
@Data
@TableName("sys_log")
public class SysLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long logId;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private String logType;
    /**
     *
     */
    private String method;
    /**
     *
     */
    private String params;
    /**
     *
     */
    private String requestIp;
    /**
     *
     */
    private Long time;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String browser;
    /**
     *
     */
//	private String exceptionDetail;

    /**
     * 异常详细
     */
    private byte[] exceptionDetail;
    /**
     *
     */
    private Date createTime;

    public SysLogEntity(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

}
