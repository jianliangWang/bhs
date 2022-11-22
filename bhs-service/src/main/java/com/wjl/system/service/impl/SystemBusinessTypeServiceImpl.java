package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessData;
import com.wjl.system.entity.SystemBusinessType;
import com.wjl.system.entity.vo.SystemBusinessTypeVO;
import com.wjl.system.mapper.SystemBusinessDataMapper;
import com.wjl.system.mapper.SystemBusinessTypeMapper;
import com.wjl.system.service.ISystemBusinessTypeService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-08-24
 */
@Service
public class SystemBusinessTypeServiceImpl extends ServiceImpl<SystemBusinessTypeMapper, SystemBusinessType> implements
    ISystemBusinessTypeService {

    private final SystemBusinessTypeMapper systemBusinessTypeMapper;

    private final SystemBusinessDataMapper systemBusinessDataMapper;
    public SystemBusinessTypeServiceImpl(
        SystemBusinessTypeMapper systemBusinessTypeMapper, SystemBusinessDataMapper systemBusinessDataMapper) {
        this.systemBusinessTypeMapper = systemBusinessTypeMapper;
        this.systemBusinessDataMapper = systemBusinessDataMapper;
    }

    @Override
    public IPage<SystemBusinessType> page(Page<SystemBusinessType> page, SystemBusinessTypeVO systemBusinessTypeVO) {
        return systemBusinessTypeMapper.selectPage(page,
            new QueryWrapper<SystemBusinessType>().like(StringUtils.hasText(systemBusinessTypeVO.getName()), "name",
                systemBusinessTypeVO.getName()).eq(StringUtils.hasText(systemBusinessTypeVO.getCode()), "code",
                systemBusinessTypeVO.getCode()));
    }

    @Override
    public SystemBusinessEnum add(SystemBusinessTypeVO businessTypeVO) {
        // 先查询该code是否已经存在
        if (this.getByCode(businessTypeVO.getCode()) != null) {
            log.error(SystemBusinessEnum.TYPE_CODE_REPEAT_ERROR.getMsg());
            return SystemBusinessEnum.TYPE_CODE_REPEAT_ERROR;
        }
        SystemBusinessType systemBusinessType = new SystemBusinessType();
        BeanUtils.copyProperties(businessTypeVO, systemBusinessType);
        systemBusinessType.setCreateDate(LocalDateTime.now());
        return systemBusinessTypeMapper.insert(systemBusinessType) > 0 ? SystemBusinessEnum.SUCCESS
            : SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessEnum updateById(SystemBusinessTypeVO businessTypeVO) {
        if (businessTypeVO.getId() == null) {
            log.error(SystemBusinessEnum.ID_NULL.getMsg());
            return SystemBusinessEnum.ID_NULL;
        }
        // 先查询该code是否已经存在
        SystemBusinessType businessType = this.getByCode(businessTypeVO.getCode());
        // 如果已经存在，并且存在的不是它自己本身，说明code已经存在了，就直接返回失败
        if (businessType != null && businessType.getId().intValue() != businessTypeVO.getId().intValue()) {
            log.error(SystemBusinessEnum.TYPE_CODE_REPEAT_ERROR.getMsg());
            return SystemBusinessEnum.TYPE_CODE_REPEAT_ERROR;
        }
        SystemBusinessType systemBusinessType = new SystemBusinessType();
        BeanUtils.copyProperties(businessTypeVO, systemBusinessType);
        return systemBusinessTypeMapper.updateById(systemBusinessType) > 0 ? SystemBusinessEnum.SUCCESS :
            SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessType getById(Integer id) {
        assert (id != null);
        return systemBusinessTypeMapper.selectById(id);
    }

    @Override
    public SystemBusinessType getByCode(String code) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", code);
        List<SystemBusinessType> systemBusinessTypeList = systemBusinessTypeMapper.selectByMap(paramMap);
        if (systemBusinessTypeList.size() > 0) {
            return systemBusinessTypeList.get(0);
        }
        return null;
    }

    @Override
    public SystemBusinessEnum deleteById(Integer id) {
        assert id != null;
        // 查询是否存在字典数据，调用字典service来查询是否存在数据字典
        SystemBusinessType systemBusinessType = systemBusinessTypeMapper.selectById(id);
        if (systemBusinessType == null) {
            return SystemBusinessEnum.TYPE_ID_ERROR;
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", systemBusinessType.getCode());
        List<SystemBusinessData> businessTypeList =
            systemBusinessDataMapper.selectByMap(paramMap);
        if (businessTypeList != null && businessTypeList.size() > 1) {
            return SystemBusinessEnum.TYPE_DELETE_ERROR;
        }
        return systemBusinessTypeMapper.deleteById(id) > 1 ? SystemBusinessEnum.SUCCESS : SystemBusinessEnum.FAIL;
    }
}
