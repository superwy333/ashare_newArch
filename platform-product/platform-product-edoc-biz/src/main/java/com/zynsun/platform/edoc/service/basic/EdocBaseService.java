package com.zynsun.platform.edoc.service.basic;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocHeader;

/**
 * 影像任务基础service
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/19 08:56
 */
public interface EdocBaseService<T extends BaseDomain> extends BaseService {

    /**
     * 获取级联的影像任务
     * @param t
     * @return
     */
    public EdocHeader queryCascadeEdocHeader(T t);

    /**
     * 插入业务表是级联插入影像任务
     *
     * @param t
     * @return
     */
    //public Integer addCascadeEdocHeader(T t);

    /**
     * 删除业务数据时级联删除影像任务
     *
     * @param t
     * @return
     */
    public Integer delCascadeEdocHeader(T t);

    /**
     * 删除业务数据时级联删除影像信息
     *
     * @param t
     * @return
     */
    public Integer delCascadeEdocImage(T t);

    /**
     * 删除业务数据时级联删除发票信息
     *
     * @param t
     * @return
     */
    public Integer delCascadeInvoice(T t);

    /**
     * 删除业务数据时级联删除影像任务以及影像信息
     *
     * @param t
     * @return
     */
    public Integer delCascadeEdocHeaderAndEdocImage(T t);

    /**
     * 删除业务数据时级联删除影像任务、影像信息、发票信息
     *
     * @param t
     * @return
     */
    public Integer delCascadeEdocHeaderAndEdocImageAndInvoice(T t);

    /**
     * 锁定业务表时级联锁定影像任务
     *
     * @param t
     * @return
     */
    public Integer lockCascadeEdocHeader(T t);

    /**
     * 删除业务级联的影像任务
     *
     * @param t
     * @return
     */
    public Integer delCascadedEdocHeader(T t) ;

    /**
     * 删除业务级联的影像信息
     *
     * @param t
     * @return
     */
    public Integer delCascadedEdocImage(T t) ;

    /**
     * 删除业务级联的发票信息
     *
     * @param t
     * @return
     */
    public Integer delCascadedInvoice(T t) ;
}
