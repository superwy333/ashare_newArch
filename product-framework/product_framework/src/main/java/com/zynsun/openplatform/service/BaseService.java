package com.zynsun.openplatform.service;

import java.util.List;
import java.util.Map;

import com.zynsun.openplatform.domain.BaseDomain;

/**
 * 
 * @ClassName: BaseService 
 * @Description: 基础Service接口
 * @author yechuan
 * @date 2016年5月19日 上午12:36:04 
 *
 */
public interface BaseService<T extends BaseDomain> {

    /**
     * 根据实体中的属性值进行查询（等于）
     * @param record
     * @return
     */
    List<T> query(T record);

    /**
     * 根据主键ID进行查询
     * @param id
     * @return
     */
    T queryById(Long id);

    /**
     * 根据主键ID进行查询
     * @param ids
     * @return
     */
    List<T> queryById(List<Long> ids);

    /**
     * 查询全部结果
     * @return
     */
    List<T> queryAll();

    /**
     * 根据实体中的属性进行查询（等于），只能有一个返回值，有多个结果是抛出异常
     * @param record
     * @return
     */
    T queryOne(T record);

    /**
     * 根据实体中的属性查询总数（等于）
     * @param record
     * @return
     */
    int queryCount(T record);

    /**
     * 保存一个实体
     * @param record
     * @return
     */
    int add(T record);
    
    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param record
     * @return
     */
    int addSelective(T record);

    /**
     * 根据主键ID更新实体全部字段
     * @param record
     * @return
     */
    int updateById(T record);

    /**
     * 根据主键ID更新属性不为null的值
     * @param record
     * @return
     */
    int updateByIdSelective(T record);

    /**
     * （物理删除）根据主键字段进行删除
     * @param key
     * @return
     */
    //    int deleteById(Long key);

    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int remove(T record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int addAll(List<T> list);

    /**
     * 根据ID进行逻辑删除
     * @param id
     * @return
     */
    public int deleteByIdLogic(Long id);
}
