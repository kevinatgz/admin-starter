package com.starter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starter.admin.dao.SysDeptDao;
import com.starter.admin.dao.SysUserDao;
import com.starter.admin.dao.UserRepository;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.admin.entity.SysUserEntity;
import com.starter.admin.entity.User;
import com.starter.admin.entity.vo.UserPassVo;
import com.starter.admin.service.RoleService;
import com.starter.admin.service.mapstruct.SysUserMapper;
import com.starter.admin.service.mapstruct.UserMapper;
import com.starter.admin.service.security.UserCacheClean;
import com.starter.admin.service.system.dto.DeptSmallDto;
import com.starter.admin.service.system.dto.RoleSmallDto;
import com.starter.admin.service.system.dto.UserDto;
import com.starter.admin.service.system.dto.UserQueryCriteria;
import com.starter.common.config.FileProperties;
import com.starter.common.config.RsaProperties;
import com.starter.common.exception.EntityExistException;
import com.starter.common.exception.EntityNotFoundException;
import com.starter.common.utils.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    private final FileProperties properties;
    @Autowired
    private RoleService roleService;
    private final UserCacheClean userCacheClean;
    private final SysUserDao sysUserRepository;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public UserDto findById(long id) {
        return null;
    }

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(sysUserMapper::toDto));
    }

    @Override
    public List<UserDto> queryAll(UserQueryCriteria criteria) {
        List<User> users = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return sysUserMapper.toDto(users);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUserEntity resources) {

        if (sysUserRepository.getUserByName(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",resources.getEmail());
        int userExist = sysUserRepository.selectCount(queryWrapper);
        if (userExist>0) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        QueryWrapper<SysUserEntity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("phone",resources.getEmail());
        int userExistPhone = sysUserRepository.selectCount(queryWrapper2);
        if (userExistPhone>0) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        sysUserRepository.insert(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (userRepository.findByPhone(resources.getPhone()) != null) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        userRepository.save(resources);
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
//        log.info("SysUserEntity:"+sysUser);
//        User user = userRepository.findByUsername(userName);
        if (sysUser == null) {
//            log.info("SysUserEntity2:"+sysUser);
            throw new EntityNotFoundException(SysUserEntity.class, "username", userName);
        } else {
//            log.info("toDto:");
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

//    @Override
//    public void updatePass(String username, String encryptPassword) {
//
//    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
        flushCache(username);
    }


//    @Override
//    public Map<String, String> updateAvatar(MultipartFile file) {
//        return null;
//    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateAvatar(MultipartFile multipartFile) {
        User user = userRepository.findByUsername(SecurityUtils.getCurrentUsername());
        String oldPath = user.getAvatarPath();
        File file = FileUtil.upload(multipartFile, properties.getPath().getAvatar());
        user.setAvatarPath(Objects.requireNonNull(file).getPath());
        user.setAvatarName(file.getName());
        userRepository.save(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
        @NotBlank String username = user.getUsername();
        flushCache(username);
        return new HashMap<String, String>(1) {{
            put("avatar", file.getName());
        }};
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

    /**
     * 清理 登陆时 用户缓存信息
     *
     * @param username /
     */
    private void flushCache(String username) {
        userCacheClean.cleanUserCache(username);
    }
}
