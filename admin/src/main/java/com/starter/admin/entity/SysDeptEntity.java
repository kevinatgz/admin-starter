package com.starter.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-21 20:00:49
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long deptId;
	/**
	 * 上级部门
	 */
	private Long pid;
	/**
	 * 子部门数目
	 */
	private Integer subCount;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer deptSort;
	/**
	 * 状态
	 */
	private Boolean enabled;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
