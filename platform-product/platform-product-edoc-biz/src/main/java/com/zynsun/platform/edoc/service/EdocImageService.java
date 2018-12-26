package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.EdocImageModel;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 14:32 2017/12/27
 * @Modified By:
 */
public interface EdocImageService extends BaseService<EdocImage> {

    /**
     * 任务内容删除任务下附件信息，若为发票，同时删除发票信息
     * @author Jary
     * @param edocImageId
     * @throws Exception
     */
    void updateEdocImage(long edocImageId) throws Exception;

    void delImagesByEdocHeaderId(long edocHeaderId);

    /**
     * 根据任务ID以及图片页码删除影像
     * @param edocHeaderId
     * @param pageNo
     */
    void delImagesByEdocHeaderIdAndPageNo(long edocHeaderId, long pageNo);

    /**
     * @Description：查询最大页码号
     * @Author：TanZhang
     * @Param：edocHeaderId
     * @Date：2017-08-10  下午 10:37
     */
    int queryMaxPagNum(Long edocHeaderId);

    /**
     * 图片替换操作
     * @param edocImage 原图片
     * @param copyEdocImage 替换图片
     */
    void updateImages(EdocImage edocImage, EdocImage copyEdocImage);

    List<EdocImageModel> selectImages(EdocImageModel edocImageModel);

    /**
     * 根据docType 为1查询图像文件 为2查询非图像附件
     * @param edocHeaderId
     * @param docType
     * @return
     */
    List<EdocImageModel> selectImagesByEdocHeaderId(Long edocHeaderId, int docType);

    /**
     * 根据edocHeader查询该任务下所有的图片文件
     * @param edocHeaderId
     * @return
     */
    List<EdocImage> selectAllImagesByEdocHeaderId(Long edocHeaderId);

    /**
     * 保存影像信息以及对应发票信息
     *
     * @param edocImage
     * @param invoice
     * @param invoiceItems
     * @return
     */
    public ExecuteResult<EdocImage> saveEdocImageAndInvoice(EdocImage edocImage, Invoice invoice, List<InvoiceItem> invoiceItems);

    List<EdocImageModel> queryEdocImageByIds(List<Long> ids);

    /**
     * 根据条件查询billIMages
     *
     * @param edocImage
     * @return
     */
    List<EdocImage> queryEdocImage(EdocImage edocImage);
    Page<EdocImageModel> queryEdocImageByPage(EdocImageModel edocImageModel);
    List<EdocImage> selectAllImages(Long parentId);
    /**
     * 根据parentId查询图片
     * @param id
     * @return
     */
    List<EdocImage> queryEdocImageByParentId(Long id);
    /**
     * 主要是将当前位置后面的图片的pageNo都加一
     * 便于排序保存数据
     * @param edocImage
     * @param edocImageList
     * @param pageNo
     */
    public void addEdocImage(EdocImage edocImage, List<EdocImage> edocImageList, Long pageNo);

    /**
     * 发票详情替换图片操作
     * @param edocImage
     * @param copyEdocImage
     */
    void operateUpdateImages(EdocImage edocImage, EdocImage copyEdocImage);

    /**
     * 根据凭证
     * @param id
     * @return
     */
    List<EdocImage> queryVouncherById(Long id);
}
