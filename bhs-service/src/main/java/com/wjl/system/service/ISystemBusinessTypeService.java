package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessType;
import com.wjl.system.entity.vo.SystemBusinessTypeVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-08-24
 */
public interface ISystemBusinessTypeService extends IService<SystemBusinessType> {

    /**
     * 数据字典类型列表
     * @param page
     * @param systemBusinessTypeVO
     * @return
     */
    @Cacheable(value = "businessTypePage", unless = "#result == null")
    IPage<SystemBusinessType> page(Page<SystemBusinessType> page, SystemBusinessTypeVO systemBusinessTypeVO);

    /**
     * 添加数据字典类型，code不能重复，
     * 需要首先检查code是否已经存在，如果存在，添加失败
     * @param businessTypeVO 数据类型字典
     * @return 是否添加成功
     */
    @CacheEvict(value = "businessTypePage", allEntries = true)
    SystemBusinessEnum add(SystemBusinessTypeVO businessTypeVO);

    /**
     * 更新数据字典类型，code不能重复
     * 需要判断code是否已经存在，如果已经存在，修改失败
     * @param businessTypeVO 更新的数据字典实体
     * @return 返回是否更新成功
     */
    @CacheEvict(value = "businessTypePage", allEntries = true)
    SystemBusinessEnum updateById(SystemBusinessTypeVO businessTypeVO);

    /**
     * 通过id查询数据字典类型
     * @param id 数据字典id
     * @return 返回查询到的字典类型值
     */
    SystemBusinessType getById(Integer id);

    /**
     * 通过code获取字典分类
     * @param code 字典分类code
     * @return
     */
    SystemBusinessType getByCode(String code);

    /**
     * 通过id删除数据字典类型
     * 需要判断该类型下是否存在字典值，如果存在不允许删除
     * @param id 数据字典类型id
     * @return 返回删除条数
     */
    @CacheEvict(value = "businessTypePage", allEntries = true)
    SystemBusinessEnum deleteById(Integer id);
}
