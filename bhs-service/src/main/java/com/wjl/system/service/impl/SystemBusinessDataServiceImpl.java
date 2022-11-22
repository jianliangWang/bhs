package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessData;
import com.wjl.system.entity.vo.SystemBusinessDataVO;
import com.wjl.system.mapper.SystemBusinessDataMapper;
import com.wjl.system.service.ISystemBusinessDataService;
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
 *  数据字典服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-11-20
 */
@Service
public class SystemBusinessDataServiceImpl extends ServiceImpl<SystemBusinessDataMapper, SystemBusinessData> implements
    ISystemBusinessDataService {

    private final SystemBusinessDataMapper systemBusinessDataMapper;

    private final ISystemBusinessTypeService businessTypeService;

    public SystemBusinessDataServiceImpl(
        SystemBusinessDataMapper systemBusinessDataMapper, ISystemBusinessTypeService businessTypeService) {
        this.systemBusinessDataMapper = systemBusinessDataMapper;
        this.businessTypeService = businessTypeService;
    }

    @Override
    public IPage<SystemBusinessData> list(Page<SystemBusinessData> page, SystemBusinessDataVO systemBusinessDataVO) {
        systemBusinessDataMapper.selectPage(page, new QueryWrapper<SystemBusinessData>().like(
            StringUtils.hasText(systemBusinessDataVO.getName()), "name",
            systemBusinessDataVO.getName()).eq(StringUtils.hasText(systemBusinessDataVO.getCode()), "code",
            systemBusinessDataVO.getCode()).eq(StringUtils.hasText(systemBusinessDataVO.getTypeCode()), "type_code",
            systemBusinessDataVO.getTypeCode()));
        return super.page(page);
    }

    @Override
    public SystemBusinessEnum add(SystemBusinessDataVO systemBusinessDataVO) {
        // 需要校验typecode是否存在
        if (businessTypeService.getByCode(systemBusinessDataVO.getTypeCode()) == null) {
            log.error(SystemBusinessEnum.TYPE_CODE_ERROR.getMsg());
            return SystemBusinessEnum.TYPE_CODE_ERROR;
        }
        // 校验code是否存在
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", systemBusinessDataVO.getCode());
        if (systemBusinessDataMapper.selectByMap(paramMap) != null){
            log.error(SystemBusinessEnum.TYPE_CODE_ERROR.getMsg());
            return SystemBusinessEnum.CODE_REPEAT_ERROR;
        }
        SystemBusinessData systemBusinessData = new SystemBusinessData();
        BeanUtils.copyProperties(systemBusinessDataVO, systemBusinessData);
        systemBusinessData.setCreateDate(LocalDateTime.now());
        return systemBusinessDataMapper.insert(systemBusinessData) > 0 ?
            SystemBusinessEnum.SUCCESS : SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessEnum update(SystemBusinessDataVO systemBusinessDataVO) {
        // 需要校验typecode是否存在
        if (businessTypeService.getByCode(systemBusinessDataVO.getTypeCode()) == null) {
            log.error(SystemBusinessEnum.TYPE_CODE_ERROR.getMsg());
            return SystemBusinessEnum.TYPE_CODE_ERROR;
        }
        SystemBusinessData systemBusinessData = new SystemBusinessData();
        BeanUtils.copyProperties(systemBusinessDataVO, systemBusinessData);
        systemBusinessData.setUpdateDate(LocalDateTime.now());
        return systemBusinessDataMapper.updateById(systemBusinessData) > 0 ?
            SystemBusinessEnum.SUCCESS : SystemBusinessEnum.FAIL;
    }

    @Override
    public SystemBusinessData getById(Integer id) {
        return systemBusinessDataMapper.selectById(id);
    }

    @Override
    public List<SystemBusinessData> getListByTypeCode(String typeCode) {
        if (!StringUtils.hasText(typeCode)) {
            log.error("typeCode 为空");
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("type_code", typeCode);
        return systemBusinessDataMapper.selectByMap(paramMap);
    }

    @Override
    public SystemBusinessEnum delete(Integer id) {
        if (id == null) {
            log.error(SystemBusinessEnum.ID_NULL.getMsg());
            return SystemBusinessEnum.ID_NULL;
        }
        return systemBusinessDataMapper.deleteById(id) > 1 ? SystemBusinessEnum.SUCCESS
            : SystemBusinessEnum.FAIL;
    }

}
