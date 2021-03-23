package com.starter.admin.controller.system;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.starter.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starter.admin.entity.SysRoleEntity;
//import com.starter.admin.service.SysRoleService;
import com.starter.common.utils.PageUtils;
import com.starter.common.utils.R;



/**
 * 角色表
 *
 * @author Kelven
 * @email kevinatgz@gmail.com
 * @date 2021-03-23 23:26:05
 */
@RestController
@RequestMapping("admin/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("@el.check('user:list','dept:list')")
//    @RequiresPermissions("admin:sysrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    @PreAuthorize("@el.check('role:info','user:list')")
    public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity sysRole = sysRoleService.getById(roleId);

        return R.ok().put("sysRole", sysRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("admin:sysrole:save")
    public R save(@RequestBody SysRoleEntity sysRole){
		sysRoleService.save(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("admin:sysrole:update")
    public R update(@RequestBody SysRoleEntity sysRole){
		sysRoleService.updateById(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("admin:sysrole:delete")
    public R delete(@RequestBody Long[] roleIds){
		sysRoleService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
