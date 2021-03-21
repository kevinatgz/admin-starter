package com.starter.admin.dao;

//import io.renren.modules.generator.entity.SysUsersRolesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.admin.entity.Role;
import com.starter.admin.entity.SysUsersRolesEntity;
import com.starter.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户角色关联
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-20 01:49:44
 */
@Mapper
public interface SysUsersRolesDao extends BaseMapper<SysUsersRolesEntity> {

    @Select("SELECT u.user_id as userId,r.role_id as roleId,r.name as roleName,r.level FROM sys_user u join sys_users_roles ur on u.user_id=ur.user_id " +
            "join sys_role r on ur.role_id=r.role_id" +
            " WHERE u.user_id = #{userId} ")
    List<Map<String,Object>> selectUserRoles(long userId);

    @Select("SELECT u.menu_id as id,u.title,u.pid,u.name,rm.role_id as roleId,u.permission FROM sys_menu u join sys_roles_menus rm on u.menu_id=rm.menu_id " +
            " WHERE rm.role_id = #{roleId} ")
    Set<Map<String,Object>> selectMenusByRole(long roleId);

    @Select("SELECT r.role_id as roleId,r.name as roleName,r.level FROM sys_user u join sys_users_roles ur on u.user_id=ur.user_id " +
            "join sys_role r on ur.role_id=r.role_id" +
            " WHERE u.user_id = #{userId} ")
    List<Role> getRoles(long userId);

    @Select("SELECT u.user_id as userId,u.username as username FROM sys_user u join sys_users_roles ur on u.user_id=ur.user_id " +
            "join sys_role r on ur.role_id=r.role_id" +
            " WHERE ur.role_id = #{roleId} ")
    List<User> getUsers(long roleId);
}
