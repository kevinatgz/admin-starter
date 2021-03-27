package com.starter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starter.admin.dao.SysDeptDao;
import com.starter.admin.dao.SysUserDao;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.admin.entity.SysUserEntity;
import com.starter.admin.entity.User;
import com.starter.admin.service.RoleService;
import com.starter.admin.service.mapstruct.UserMapper;
import com.starter.admin.service.system.dto.DeptSmallDto;
import com.starter.admin.service.system.dto.RoleSmallDto;
import com.starter.admin.service.system.dto.UserDto;
import com.starter.common.exception.EntityExistException;
import com.starter.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements com.starter.admin.service.user.UserService {
//    private final UserMapper userMapper;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private RoleService roleService;

    private final SysUserDao userRepository;

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUserEntity resources) {

        if (userRepository.getUserByName(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",resources.getEmail());
        int userExist = userRepository.selectCount(queryWrapper);
        if (userExist>0) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        QueryWrapper<SysUserEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("phone",resources.getEmail());
        int userExistPhone = userRepository.selectCount(queryWrapper2);
        if (userExistPhone>0) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        userRepository.insert(resources);
    }

    @Override
    public void update(User resources) throws Exception {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    @Override
    public UserDto findByName(String userName) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
//        SysUserEntity sysUser = sysUserDao.selectOne(queryWrapper);
        SysUserEntity sysUser = sysUserDao.getUserByName(userName);
        log.info("SysUserEntity:"+sysUser);
//        User user = userRepository.findByUsername(userName);
        if (sysUser == null) {
            log.info("SysUserEntity2:"+sysUser);
            throw new EntityNotFoundException(SysUserEntity.class, "username", userName);
        } else {
            log.info("toDto:");
            UserDto dto = new UserDto();
            dto.setUsername(sysUser.getUsername());
            dto.setPassword(sysUser.getPassword());
            dto.setId(sysUser.getUserId());
            dto.setEnabled(sysUser.getEnabled());
            dto.setDeptId(sysUser.getDeptId());
            DeptSmallDto dept= new DeptSmallDto();

            SysDeptEntity sysDeptEntity = sysDeptDao.selectById(sysUser.getDeptId());
            dept.setId(sysDeptEntity.getDeptId());
            dept.setName(sysDeptEntity.getName());
            dto.setDept(dept);
            List<RoleSmallDto> roles = roleService.findByUsersId(sysUser.getUserId());
            Set<RoleSmallDto> rolesSet = new HashSet<>(roles);
            dto.setRoles(rolesSet);
            log.info("UserDto:"+dto);
            return dto;
//            return userMapper.toDto(sysUser);
        }
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void updateCenter(User resources) {

    }
}
