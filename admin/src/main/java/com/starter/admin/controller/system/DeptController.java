/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.starter.admin.controller.system;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starter.admin.entity.SysDeptEntity;
import com.starter.admin.service.DeptService;
//import com.starter.admin.service.SysDeptService;
import com.starter.admin.service.SysDeptService;
import com.starter.admin.service.impl.DeptServiceImpl;
import com.starter.admin.service.system.dto.DeptDto;
import com.starter.common.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
//import me.zhengjie.annotation.Log;
//import me.zhengjie.exception.BadRequestException;
//import me.zhengjie.modules.system.domain.Dept;
//import me.zhengjie.modules.system.service.DeptService;
//import me.zhengjie.modules.system.service.dto.DeptDto;
//import me.zhengjie.modules.system.service.dto.DeptQueryCriteria;
//import me.zhengjie.utils.PageUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@RestController
@RequiredArgsConstructor
@Api(tags = "?????????????????????")
@RequestMapping("/api/dept")
public class DeptController {

    @Autowired
    private final SysDeptService deptService;
    private static final String ENTITY_NAME = "dept";

//    @ApiOperation("??????????????????")
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('dept:list')")
//    public void download(HttpServletResponse response, DeptQueryCriteria criteria) throws Exception {
//        deptService.download(deptService.queryAll(criteria, false), response);
//    }

    @ApiOperation("????????????")
    @GetMapping
    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> query(QueryWrapper<SysDeptEntity> criteria) throws Exception {
        List<SysDeptEntity> deptDtos =  deptService.list(criteria);
//        List<DeptDto> deptDtos = deptService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(deptDtos, deptDtos.size()),HttpStatus.OK);
    }

//    @ApiOperation("????????????:??????ID???????????????????????????")
//    @PostMapping("/superior")
//    @PreAuthorize("@el.check('user:list','dept:list')")
//    public ResponseEntity<Object> getSuperior(@RequestBody List<Long> ids) {
//        Set<DeptDto> deptDtos  = new LinkedHashSet<>();
//        for (Long id : ids) {
//            DeptDto deptDto = deptService.findById(id);
//            List<DeptDto> depts = deptService.getSuperior(deptDto, new ArrayList<>());
//            deptDtos.addAll(depts);
//        }
//        return new ResponseEntity<>(deptService.buildTree(new ArrayList<>(deptDtos)),HttpStatus.OK);
//    }

//    @Log("????????????")
//    @ApiOperation("????????????")
//    @PostMapping
//    @PreAuthorize("@el.check('dept:add')")
//    public ResponseEntity<Object> create(@Validated @RequestBody Dept resources){
//        if (resources.getId() != null) {
//            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
//        }
//        deptService.create(resources);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

//    @Log("????????????")
//    @ApiOperation("????????????")
//    @PutMapping
//    @PreAuthorize("@el.check('dept:edit')")
//    public ResponseEntity<Object> update(@Validated(Dept.Update.class) @RequestBody Dept resources){
//        deptService.update(resources);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    @Log("????????????")
//    @ApiOperation("????????????")
//    @DeleteMapping
//    @PreAuthorize("@el.check('dept:del')")
//    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
//        Set<DeptDto> deptDtos = new HashSet<>();
//        for (Long id : ids) {
//            List<Dept> deptList = deptService.findByPid(id);
//            deptDtos.add(deptService.findById(id));
//            if(CollectionUtil.isNotEmpty(deptList)){
//                deptDtos = deptService.getDeleteDepts(deptList, deptDtos);
//            }
//        }
//        // ????????????????????????????????????
//        deptService.verification(deptDtos);
//        deptService.delete(deptDtos);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}