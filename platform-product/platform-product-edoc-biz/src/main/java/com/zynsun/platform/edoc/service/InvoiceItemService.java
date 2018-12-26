package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.InvoiceItemModel;

/**
 * 发票明细service
 */
public interface InvoiceItemService extends BaseService<InvoiceItem> {

    void deleteInvoiceItemByEdocHeaderId(Long edocHeaderId);

    /**
     * 分页查询发票明细
     *
     * @param map
     * @return
     */
    Page<InvoiceItem> queryInvItemsByPage(InvoiceItemModel map);

    /**
     * 根据发票id删除发票明细
     * @param invoiceId
     */
    void deleteInvoiceItemByInvoiceId(Long invoiceId);

    Page<InvoiceItem> editInvItemByInvItemId(InvoiceItemModel map);

  /*  InvoiceItem editInvItemByInvItemId_one(InvoiceItemModel map);*/
}
