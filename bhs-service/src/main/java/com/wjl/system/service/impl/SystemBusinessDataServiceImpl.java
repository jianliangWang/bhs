package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessData;
import com.wjl.system.entity.vo.SystemBusinessDataVO;
import com.wjl.system.mapper.SystemBusinessDataMapper;
import com.wjl.system.service.ISystemBusinessDataService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SystemBusinessDataServiceImpl extends ServiceImpl<SystemBusinessDataMapper, SystemBusinessData> implements
    ISystemBusinessDataService {

    private final SystemBusinessDataMapper systemBusinessDataMapper;

    public SystemBusinessDataServiceImpl(SystemBusinessDataMapper systemBusinessDataMapper) {
        this.systemBusinessDataMapper = systemBusinessDataMapper;
    }

    @Override
    public IPage<SystemBusinessData> list(Page<SystemBusinessData> page, SystemBusinessDataVO systemBusinessDataVO) {
        return null;
    }

    @Override
    public SystemBusinessEnum add(SystemBusinessDataVO systemBusinessDataVO) {
        SystemBusinessData systemBusinessData = new SystemBusinessData();
        BeanUtils.copyProperties(systemBusinessDataVO, systemBusinessData);
        systemBusinessData.setCreateDate(LocalDateTime.now());
        // TODO 检查code是否存在
        int result = systemBusinessDataMapper.insert(systemBusinessData);
        if (result > 0) {
            return SystemBusinessEnum.SUCCESS;
        }
        return SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessEnum update(SystemBusinessDataVO systemBusinessDataVO) {
        if (systemBusinessDataVO.getId() != null) {
            return SystemBusinessEnum.FAIL;
        }
        // TODO 检查code是否存在

        SystemBusinessData systemBusinessData = new SystemBusinessData();
        BeanUtils.copyProperties(systemBusinessDataVO, systemBusinessData);
        systemBusinessData.setUpdateDate(LocalDateTime.now());
        int result = systemBusinessDataMapper.updateById(systemBusinessData);
        if (result > 0) {
            return SystemBusinessEnum.SUCCESS;
        }
        return SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessData getById(Integer id) {
        assert id != null;
        return systemBusinessDataMapper.selectById(id);
    }

    @Override
    public List<SystemBusinessData> getListByTypeCode(String typeCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("type_code", typeCode);
        return systemBusinessDataMapper.selectByMap(map);
    }

    @Override
    public SystemBusinessEnum delete(Integer id) {
        assert id != null;
        if (systemBusinessDataMapper.deleteById(id) > 0) {
            return SystemBusinessEnum.SUCCESS;
        }
        return SystemBusinessEnum.FAIL;
    }
}
