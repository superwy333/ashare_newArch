package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.InvoiceModel;

import java.io.IOException;
import java.util.List;

/**
 * 发票信息service
 */
public interface InvoiceService extends BaseService<Invoice> {

    Invoice selectInvoiceByImgId(long edocImageId);

    void deleteInvoiceByEdocHeaderId(Long edocHeaderId);

    Page<InvoiceModel> queryInvoices(InvoiceModel invoiceModel);

    List<InvoiceModel> queryInvoicesNoPage(InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInvoicesDataGrid(InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInvoicesByEdocNo(String edocNo);

    Page<InvoiceModel> queryInvoicesByEdocNoForCheck(InvoiceModel invoiceModel);

    String CheckInvoice(Long id) throws IOException;

    Invoice queryByInvId(Long id);

    /**
     * 根据图片id查询发票信息
     * @param imgId
     * @return
     */
    Invoice queryInvoiceByImgId(Long imgId);

    /**
     * 根据图片id查询发票list
     */
    List<Invoice> queryInvoiceListByImgId(Long imgId);

    /**
     * 查询重复发票
     * @param queryInv
     * @return
     */
    List<Invoice> queryRepeatInvList(Invoice queryInv);

    /**
     * 查询重复发票,手工验重时使用这个方法
     * @param queryInv
     * @return
     */
    List<Invoice> queryRepeatInvListNew(Invoice queryInv);

    /**
     * 查询验证结果为NUll的数据
     * @param
     * @return
     */
    List<Invoice> queryCheckRealNullList();

    /**
     * 更新发票信息
     * @param waitEditInvModel
     * @return
     */
    Invoice updateInvoice(InvoiceModel waitEditInvModel);

    /**
     * 根据图片id删除发票和发票附件
     * @param imgId
     * @return
     */
    int deleteInvoiceByImgId(long imgId);

    void invoiceCheckRule(Invoice invoice);

    Invoice invoiceCheckRuleNoUpdate(Invoice invoice);


    Page<InvoiceModel> queryInputInvoiceTaxPage(InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInputInvoiceItem(InvoiceModel invoiceModel);

    /**
     * 根据发票id查询 发票明细的 实际报销金额和实际报销税额 的总和
     * @param invId
     * @return
     */
    InvoiceItem queryByInvId_sum(Long invId);

    Page<InvoiceModel> exportWaitCertInv_Part(InvoiceModel map);

    Page<InvoiceModel> exportWaitCertInv_All(InvoiceModel invoiceModel);

    int deleteInvoiceByImageId(Long imageId);

    List<Invoice> queryInvsByEdocHeaderId(String edocNo);

    /**
     * 查询所有待验真发票
     * @return
     */
    List<Invoice> queryWaitCheckRealInv();

    List<Invoice> queryInvByEdocHeader(Long edocHeaderId);

    Long getEdocHeaderIdByInvId(Long invId);


}
