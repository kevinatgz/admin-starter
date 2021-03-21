package com.starter.admin.dao;

//import io.renren.modules.generator.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.starter.admin.entity.Role;
import com.starter.admin.entity.SysRoleEntity;
import com.starter.admin.entity.SysUserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-20 01:49:44
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
    @Select("SELECT r.role_id as roleId,r.name as roleName,r.level FROM sys_user u join sys_users_roles ur on u.user_id=ur.user_id " +
            "join sys_role r on ur.role_id=r.role_id" +
            " WHERE u.user_id = #{userId} ")
    List<SysRoleEntity> getRoles(long userId);

    @Select("Select user_id as userId,username from sys_user where userId=#{userId}")
    @Results(value = { @Result(property = "userId", column = "userId"), @Result(property = "username", column = "username"),
            @Result(property = "roles", javaType = List.class, column = "user_id", many = @Many(select = "getRoles"))

    })
    public SysUserEntity getUserById(Integer userId);


    @Select("Select user_id as userId,dept_id as deptId,username,enabled,password,email,phone,gender,avatar_path from sys_user where username=#{username}")
    @Results(value = { @Result(property = "userId", column = "userId"), @Result(property = "username", column = "username"),
            @Result(property = "roles", javaType = List.class, column = "username", many = @Many(select = "getRolesByUsername"))

    })
    public SysUserEntity getUserByName(String username);

    @Select("SELECT r.role_id as roleId,r.name as name,r.level as level FROM sys_user u join sys_users_roles ur on u.user_id=ur.user_id " +
            "join sys_role r on ur.role_id=r.role_id" +
            " WHERE u.username = #{username} ")
    List<SysRoleEntity> getRolesByUsername(String username);
}
