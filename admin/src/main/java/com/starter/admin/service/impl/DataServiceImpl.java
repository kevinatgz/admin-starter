package com.starter.admin.service.impl;

import com.starter.admin.service.DataService;
import com.starter.admin.service.system.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataServiceImpl implements DataService {
    @Override
    public List<Long> getDeptIds(UserDto user) {
        return null;
    }
}
