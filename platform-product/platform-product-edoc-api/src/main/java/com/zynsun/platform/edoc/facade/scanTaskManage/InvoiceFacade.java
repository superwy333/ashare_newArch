package com.zynsun.platform.edoc.facade.scanTaskManage;


import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.dto.scanTask.InvDiffColumnDTO;
import com.zynsun.platform.edoc.dto.scanTask.InvoiceItemDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 9:18 2018/1/24
 * @Modified By:
 */
public interface InvoiceFacade extends BaseFacade {


    /**
     * 人人乐发票列表
     * @return
     */
    Map<String,Object> invoceDataGrid(Map<String,Object> map);

    /**
     * 人人乐发票影像url获取
     * @param map
     * @return
     */
    Map<String,Object> invoiceImage(Map<String,Object> map);

    /**
     * 人人乐修改发票
     * @param map
     * @return
     */
    Map<String,Object> updateInv(Map<String,Object> map);

    /**
     * 人人乐发票验真状态下拉框
     * @param map
     * @return
     */
    /*Map<String,Object> checkRealCombobox(Map<String,Object> map);*/


    /**
     * 人人乐发票模块下拉框
     * @param map
     * @return
     */
    Map<String,Object> invoiceCombobox(Map<String,Object> map);


    /**
     * 查询发票分页数据
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<PageInfo<InvoiceDTO>> queryInvoices(InvoiceDTO invoiceDTO);

    /**
     * 根据edocNo查询发票分页数据
     * @param edocNo
     * @return
     */
    ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNo(String edocNo);

    /**
     * 根据edocNo查询发票分页数据
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNoForCheck(InvoiceDTO invoiceDTO);

    /**
     * 查询发票数据
     * @param id
     * @return
     */
    ExecuteResult<InvoiceDTO> queryInvoiceById(Long id);

    ExecuteResult<Boolean> checkInvoices(List<Long> ids);

    /**
     * 根据图片id查询发票信息
      * @param imgId
     * @return
     */
    ExecuteResult<InvoiceDTO> findInvoiceByImgId(Long imgId);

    /**
     * 修改发票数据
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<InvoiceDTO> updateInvNormal(InvoiceDTO invoiceDTO, Long edocImageId, String edocImageType);

    /**
     * 修改发票数据
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<InvoiceDTO> updateInv(InvoiceDTO invoiceDTO);

    /***
     * 查询发票修改字段的列表信息
     * @param invDiffColumnDTO
     * @return
     */
    ExecuteResult<PageInfo<InvDiffColumnDTO>> queryPageInvDiff(InvDiffColumnDTO invDiffColumnDTO);

    /**
     * 查询发票明细
     * @param invoiceItemDTO
     * @return
     */
    ExecuteResult<PageInfo<InvoiceItemDTO>> queryInvItemsByPage(InvoiceItemDTO invoiceItemDTO);

    /**
     * 新增发票明细
     * @param invoiceItemDTO
     * @return
     */
    ExecuteResult<String> createInvItem(InvoiceItemDTO invoiceItemDTO);

    /**
     * 根据id查找发票明细
     * @param id
     * @return
     */
    ExecuteResult<InvoiceItemDTO> findOneInvItem(Long id);

    /**
     * 修改发票明细
     * @param invoiceItemDTO
     * @return
     */
    ExecuteResult<String> updateInvItem(InvoiceItemDTO invoiceItemDTO);

    /**
     * 根据id删除明细
     * @param id
     * @return
     */
    ExecuteResult<String> removeInvItem(Long id);

    /**
     * 添加发票或者附件
     * @param fileNameEnd Controller层传递来的文件名称
     * @param bys  Controller层传递来文件的字节流
     * @param id   Controller层传来的追加位置的图片的ID
     *
     */
     Long addInvoiceAndEdocImage(String fileNameEnd,byte[] bys,Long id);

    /**
     * 根据图片父级id和id删除发票或附件
     *
     * @param imgId
     * @return
     */
    ExecuteResult<String> deleteImage(long imgId);

    /**
     * 发票验真条件判断
     * @param invoiceId
     * @return
     */
    /*ExecuteResult<String> beforeCheckReal(Long invoiceId);*/


    /**
     * 发票验真
     * @param invoiceId
     * @return
     */
    ExecuteResult<InvoiceDTO> checkReal(Long invoiceId);

    /**
     * 发票验真前端接口
     * @param map
     * @return
     */
    Map<String,Object> checkReal(Map<String,Object> map);

    /**
     * 人工验真前端接口
     * @param map
     * @return
     */
    Map<String,Object> checkRealManual(Map<String,Object> map);

    /**
     * 发票验真
     * @return
     */
    ExecuteResult<String> checkReal();


    /**
     * 新增发票
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<String> addInvoice(InvoiceDTO invoiceDTO);

    /**
     * 新增发票录入信息
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<String> createInvInput(InvoiceDTO invoiceDTO);

    /**
     * 根据id删除发票信息
     * @param id
     * @return
     */
    ExecuteResult<String> removeInv(Long id);

    /**
     * 导入待认证发票更新认证状态
     * @param bytes
     * @param isReturn
     * @return
     */
    //ExecuteResult<String> importWaitCertInv(byte[] bytes, boolean isReturn);

    /**
     * 抵账库查询发票
     * @return
     */
    ExecuteResult<String> extractInv(InvoiceDTO invoiceDTO);


    /**
     * 发票规则校验
     * @return
     */
    ExecuteResult<String> invoiceCheckRule();

    /**
     * 发票规则校验,使用id选择发票
     * @return
     */
    ExecuteResult<String> invoiceCheckRule(Long invId);


    /**
     * 进项税转出列表
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<PageInfo<InvoiceDTO>> queryInputInvoiceTaxPage(InvoiceDTO invoiceDTO);

    ExecuteResult<PageInfo<InvoiceDTO>> queryInputInvoiceItem(InvoiceDTO invoiceDTO);



    /**
     * 根据id更新单据下增专票认证状态为待认证： 00
     *
     * @param edocHeaderId 单据id
     * @return
     */
    ExecuteResult<String> updateCertStatus(Long edocHeaderId);


    ExecuteResult<List<InvoiceItemDTO>> queryInvoiceItemsByInvId(Long invId);

    /**
     * 编辑发票费用类型和部门
     * @param invoiceDTO
     * @return
     */
    ExecuteResult<String> updateInvDeptAndCostType(InvoiceDTO invoiceDTO);

    /**
     * 根据图片id查询发票信息
     * @param edocImageId
     * @return
     */
    ExecuteResult<List<InvoiceDTO>> queryInvsByImgId(long edocImageId);

    /**
     * 根据任务id删除任务下发票和影像
     *
     * @param edocHeaderId 任务id
     * @return
     */
    ExecuteResult<String> deleteInvAndImagByEdocHeaderId(Long edocHeaderId);

    /**
     * 根据发票明细id查询发票明细
     * @param invoiceItemDTO
     * @return
     */
    ExecuteResult<PageInfo<InvoiceItemDTO>> editInvItemByInvItemId(InvoiceItemDTO invoiceItemDTO);

    ExecuteResult<InvoiceItemDTO> editInvItemByInvItemId_one(InvoiceItemDTO invoiceItemDTO);

    ExecuteResult<PageInfo<InvoiceDTO>> exportWaitCertInv_Part(InvoiceDTO invoiceDTO);

    ExecuteResult<PageInfo<InvoiceDTO>> exportWaitCertInv_All(InvoiceDTO invoiceDTO);

    //根据图片id删除发票信息
    int deleteInvoiceByImageId(Long id);

    ExecuteResult<InvoiceDTO> checkStatusByInvId(Long invId);
   ////查询冲突单据中包含该影像件的单据编号
    ExecuteResult<List<InvoiceDTO>> queryInvsByEdocHeaderId(String edocNo);

    ExecuteResult<String> timeCheckReal();

    ExecuteResult<PageInfo<InvoiceDTO>> queryInvoicesByEdocNoForCheck_inv(InvoiceDTO invoiceDTO);
}

