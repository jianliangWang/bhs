package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessData;
import com.wjl.system.entity.vo.SystemBusinessDataVO;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  数据字典
 * </p>
 *
 * @author jay
 * @since 2022-11-20
 */
public interface ISystemBusinessDataService extends IService<SystemBusinessData> {

    /**
     * 数据字典列表
     * @param page
     * @return
     */
    @Cacheable(value = "systemBusinessData", unless = "#result == null")
    IPage<SystemBusinessData> list(Page<SystemBusinessData>page, SystemBusinessDataVO systemBusinessDataVO);

    /**
     * 添加数据字典
     * @param systemBusinessDataVO 参数类
     * @return
     */
    @CacheEvict(value = "systemBusinessData", allEntries = true)
    SystemBusinessEnum add(SystemBusinessDataVO systemBusinessDataVO);

    /**
     * 更新数据字典
     * @param systemBusinessDataVO 参数类
     * @return
     */
    @CacheEvict(value = "systemBusinessData", allEntries = true)
    SystemBusinessEnum update(SystemBusinessDataVO systemBusinessDataVO);

    /**
     * 通过id获取数据字典
     * @param id 主键
     * @return
     */
    SystemBusinessData getById(Integer id);

    /**
     * 通过分类code获取数据字典
     * @param typeCode 分类code
     * @return
     */
    List<SystemBusinessData> getListByTypeCode(String typeCode);

    /**
     * 通过id删除数据字典
     * @param id 主键
     * @return
     */
    @CacheEvict(value = "systemBusinessData", allEntries = true)
    SystemBusinessEnum delete(Integer id);

}
