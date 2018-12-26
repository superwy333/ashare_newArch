package com.zynsun.platform.edoc.service.basic.impl;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.openplatform.exception.SysException;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.service.basic.EdocBaseService;
import constant.Constant;

import javax.persistence.OptimisticLockException;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;

/**
 * 影像任务基础service实现类
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/19 09:09
 */
public abstract class EdocBaseServiceImpl<T extends BaseDomain> extends BaseServiceImpl implements EdocBaseService<T> {

    protected abstract IMapper<EdocHeader> getEdocHeaderMapper();
    protected abstract IMapper<EdocImage> getEdocImageMapper();
    protected abstract IMapper<Invoice> getInvoiceMapper();
    protected abstract IMapper<InvoiceItem> getInvoiceItemMapper();
    protected abstract IMapper<T> getBaseMapper();

    /**
     * 获取级联的影像任务(只支持查询edoc_levle = 0 的影像任务) TODO
     * @param t
     * @return
     */
    @Override
    public EdocHeader queryCascadeEdocHeader(T t) {
        Class<? extends BaseDomain> tClass = t.getClass();
        EdocHeader queryEdocHeader = new EdocHeader();
        try {
            queryEdocHeader.setDeleted(Constant.DeleteFlag.DEL_NO);
            queryEdocHeader.setEdocNo((String) new PropertyDescriptor("edocNo", tClass).getReadMethod().invoke(t));
            queryEdocHeader.setEdocType((String) new PropertyDescriptor("bizType", tClass).getReadMethod().invoke(t));
        } catch (Exception e) {
            throw new SysException(e);
        }
        List<EdocHeader> edocHeaders = getEdocHeaderMapper().select(queryEdocHeader);
        if (null != edocHeaders && !edocHeaders.isEmpty()) {
            return  edocHeaders.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除业务数据时级联删除影像任务
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadeEdocHeader(T t) {
        // 1.先删除业务信息表
        int count = super.remove(t);
        // 2.级联删除影像任务
        count += delCascadedEdocHeader(t);
        return count;
    }

    /**
     * 删除业务数据时级联删除影像信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadeEdocImage(T t) {
        // 1.先删除业务信息表
        int count = super.remove(t);
        // 2.级联删除影像信息
        count += delCascadedEdocImage(t);
        return count;
    }

    /**
     * 删除业务数据时级联删除发票信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadeInvoice(T t) {
        // 1.先删除业务信息表
        int count = super.remove(t);
        // 2.级联删除发票信息
        count += delCascadedInvoice(t);
        return count;
    }

    /**
     * 删除业务数据时级联删除影像任务以及影像信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadeEdocHeaderAndEdocImage(T t) {
        int count = super.remove(t);
        count += delCascadedEdocImage(t);
        count += delCascadedEdocHeader(t);
        return count;
    }

    /**
     * 删除业务数据时级联删除影像任务、影像信息、发票信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadeEdocHeaderAndEdocImageAndInvoice(T t) {
        int count = super.remove(t);
        count += delCascadedInvoice(t);
        count += delCascadedEdocImage(t);
        count += delCascadedEdocHeader(t);
        return count;
    }

    /**
     * 删除业务级联的影像任务
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadedEdocHeader(T t) {
        // 先删除级联影像任务
        EdocHeader edocHeader = queryCascadeEdocHeader(t);
        if (null == edocHeader) {return 0;}
        edocHeader.setDeleted(Constant.DeleteFlag.DEL_YES); // 删除标记 1：已删除
        return updateCASByPrimaryKey(edocHeader, getEdocHeaderMapper());
    }

    /**
     * 删除业务级联的影像信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadedEdocImage(T t) {
        int count = 0;
        // 1.获取级联影像任务
        EdocHeader edocHeader = queryCascadeEdocHeader(t);
        if (null == edocHeader) {return 0;}

        // 2.获取级联影像信息
        EdocImage queryImages = new EdocImage();
        queryImages.setDeleted(Constant.DeleteFlag.DEL_NO);
        queryImages.setEdocHeaderId(edocHeader.getId());
        List<EdocImage> imagesList = getEdocImageMapper().select(queryImages);
        if (null == imagesList || imagesList.isEmpty()) {return 0;}
        // 3.级联删除影像任务
        for (EdocImage edocImage : imagesList) {
            edocImage.setDeleted(Constant.DeleteFlag.DEL_YES); // 删除标记 1：已删除
            count += updateCASByPrimaryKey(edocImage, getEdocImageMapper());
        }
        return count;
    }

    /**
     * 删除业务级联的发票信息
     *
     * @param t
     * @return
     */
    @Override
    public Integer delCascadedInvoice(T t) {
        int count = 0;
        // 1.获取级联影像任务
        EdocHeader edocHeader = queryCascadeEdocHeader(t);
        if (null == edocHeader) {return 0;}

        // 2.获取级联影像信息
        EdocImage queryImages = new EdocImage();
        queryImages.setDeleted(Constant.DeleteFlag.DEL_NO);
        queryImages.setEdocHeaderId(edocHeader.getId());
        List<EdocImage> imagesList = getEdocImageMapper().select(queryImages);
        if (null == imagesList || imagesList.isEmpty()) {return 0;}

        // 3.级联删除影像任务
        for (EdocImage edocImage : imagesList) {
            // 4.获取级联发票信息
            Invoice queryInvoice = new Invoice();
            queryInvoice.setDeleted(Constant.DeleteFlag.DEL_NO);
            queryInvoice.setEdocImageId(edocImage.getId());
            List<Invoice> invoiceList = getInvoiceMapper().select(queryInvoice);

            if (null == invoiceList || invoiceList.isEmpty()) {continue;}
            // 5.级联删除发票信息
            for (Invoice invoice : invoiceList) {
                invoice.setDeleted(Constant.DeleteFlag.DEL_YES); // 删除标记 1：已删除
                count += updateCASByPrimaryKey(invoice, getInvoiceMapper());

                // 6. 继续删除发票明细
                InvoiceItem queryInvoiceItem = new InvoiceItem();
                queryInvoiceItem.setInvId(invoice.getId());
                queryInvoiceItem.setDeleted(Constant.DeleteFlag.DEL_NO);
                List<InvoiceItem> invoiceItems = getInvoiceItemMapper().select(queryInvoiceItem);
                if (null == invoiceItems || invoiceItems.isEmpty()) {continue;}
                for (InvoiceItem invoiceItem : invoiceItems) {
                    invoiceItem.setDeleted(Constant.DeleteFlag.DEL_YES); // 删除标记 1：已删除
                    count += updateCASByPrimaryKey(invoiceItem, getInvoiceMapper());
                }
            }
        }
        return count;
    }

    /**
     * 锁定业务表时级联锁定影像任务
     *
     * @param t
     * @return
     */
    @Override
    public Integer lockCascadeEdocHeader(T t) {
        // 1.先锁定业务信息表 TODO
        // t.setIsLocked(Constant.YesNo.YES);
        int count = super.updateByIdSelective(t);
        // 2.级联锁定影像任务
        EdocHeader edocHeader = queryCascadeEdocHeader(t);
        if (null == edocHeader) {return 0;}
        edocHeader.setIsLocked(Constant.YesNo.YES);
        count += updateCASByPrimaryKey(edocHeader, getEdocHeaderMapper());
        return count;
    }

    /**
     * 更新对象信息
     *
     * @param t
     * @param mapper
     * @return
     */
    private Integer updateCASByPrimaryKey(BaseDomain t, IMapper mapper) {
        int count = 0;
        t.setLastModifyBy(getCurrentUser(t));
        t.setLastModifyTime(new Date());
        if (mapper.updateCASByPrimaryKey(t) <= 0) {
            throw new OptimisticLockException("根据ID逻辑删除实体对象时乐观锁版本不一致, 更新失败。");
        } else {
            count++;
            long version = t.getVersion() + 1L;
            t.setVersion(version);
        }
        return count;
    }
}
