package com.starter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starter.admin.dao.RoleRepository;
import com.starter.admin.dao.SysUserDao;
import com.starter.admin.dao.SysUsersRolesDao;
import com.starter.admin.entity.Menu;
import com.starter.admin.entity.Role;
import com.starter.admin.entity.SysUserEntity;
import com.starter.admin.service.RoleService;
import com.starter.admin.service.mapstruct.RoleMapper;
import com.starter.admin.service.system.dto.RoleDto;
import com.starter.admin.service.system.dto.RoleQueryCriteria;
import com.starter.admin.service.system.dto.RoleSmallDto;
import com.starter.admin.service.system.dto.UserDto;
import com.starter.common.utils.PageUtil;
import com.starter.common.utils.QueryHelp;
import com.starter.common.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysUsersRolesDao sysUserRolesDao;

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public List<RoleDto> queryAll() {
        return null;
    }

    @Override
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        Page<Role> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(roleMapper::toDto));
    }

//    @Override
//    public RoleDto findById(long id) {
//        return null;
//    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public RoleDto findById(long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", id);
        return roleMapper.toDto(role);
    }

    @Override
    public void create(Role resources) {

    }

    @Override
    public void update(Role resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
//        QueryWrapper<Map<String,Object>> queryWrapper = new QueryWrapper<>();
        Map queryMap = new HashMap();
        queryMap.put("userId",id);
//        queryWrapper.eq("u.user_id",id);
        List<Map<String,Object>> userRolesList = sysUserRolesDao.selectUserRoles(id);
        List<RoleSmallDto> list=new ArrayList<>();
        for(Map userRole:userRolesList){
            RoleSmallDto role= new RoleSmallDto();
            role.setId((Long)(userRole.get("roleId")));
            role.setLevel((int)userRole.get("level"));
            role.setName(String.valueOf(userRole.get("roleName")));
            role.setDataScope(String.valueOf(userRole.get("dataScope")));
            list.add(role);
        }

        return list;
    }

//    @Override
//    public Integer findByRoles(Set<Role> roles) {
//        return null;
//    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        if (roles.size() == 0) {
            return Integer.MAX_VALUE;
        }
        Set<RoleDto> roleDtos = new HashSet<>();
        for (Role role : roles) {
            roleDtos.add(findById(role.getId()));
        }
        return Collections.min(roleDtos.stream().map(RoleDto::getLevel).collect(Collectors.toList()));
    }

    @Override
    public void updateMenu(Role resources, RoleDto roleDTO) {

    }

    @Override
    public void untiedMenu(Long id) {

    }

    @Override
    public void download(List<RoleDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
//        Map queryMap = new HashMap();
//        queryMap.put("u.user_id",user.getId());
        List<Map<String,Object>> roles = sysUserRolesDao.selectUserRoles(user.getId());
        log.info("selectUserRoles="+roles);
        List<Role> rolelist=new ArrayList<>();
        for(Map userRole:roles){
            Role role= new Role();
            role.setId((Long)(userRole.get("roleId")));
            role.setName(String.valueOf(userRole.get("roleName")));
            Set<Menu> menuSet = new HashSet();
//            Map queryMenuMap = new HashMap();
//            queryMenuMap.put("roleId",userRole.get("roleId"));
            Set<Map<String,Object>> menu =sysUserRolesDao.selectMenusByRole((Long)userRole.get("roleId"));
            for(Map menuItem :menu ){
                Menu aMenu =  new Menu();
                aMenu.setId((Long)menuItem.get("id"));
                aMenu.setPermission((String)menuItem.get("permission"));
                aMenu.setTitle((String)menuItem.get("title"));
                menuSet.add(aMenu);
            }
            role.setMenus(menuSet);
            rolelist.add(role);
        }
        log.info("rolelist="+rolelist);

        permissions = rolelist.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        log.info("permissions="+permissions);
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public void verification(Set<Long> ids) {

    }

    @Override
    public List<Role> findInMenuId(List<Long> menuIds) {
        return null;
    }
}
