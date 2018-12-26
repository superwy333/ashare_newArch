package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.InvoiceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvoiceMapper extends IMapper<Invoice> {

    void deleteInvoiceByEdocHeaderId(Long edocHeaderId);

    Page<InvoiceModel> queryInvoices(@Param("invoiceModel")InvoiceModel invoiceModel);

    List<InvoiceModel> queryInvoicesNoPage(@Param("invoiceModel")InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInvoicesDataGrid(@Param("invoiceModel")InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInvoicesByEdocNo(@Param("edocNo")String edocNo);

    Page<InvoiceModel> queryInvoicesByEdocNoForCheck(@Param("invoiceModel")InvoiceModel invoiceModel);

    Invoice selectByImgId(Long imgId);

    List<Invoice> selectInvoiceListByImgId(Long imgId);

    List<Invoice> queryRepeatInvList(@Param("inv")Invoice inv);

    List<Invoice> queryRepeatInvListForMaualCheckStatus(@Param("inv")Invoice inv);

    Page<InvoiceModel> queryInputInvoiceTaxPage(@Param("invoiceModel")InvoiceModel invoiceModel);

    Page<InvoiceModel> queryInputInvoiceItem(@Param("invoiceModel")InvoiceModel invoiceModel);

    /**
     * 查询验证结果为NUll的数据
     * @param
     * @return
     */
    List<Invoice> queryInvCheckRealList();

    InvoiceItem queryByInvId_sum(Long invId);

    Page<InvoiceModel> exportWaitCertInv_Part(@Param("invoiceModel")InvoiceModel invoiceModel);

    Page<InvoiceModel> exportWaitCertInv_All(@Param("invoiceModel")InvoiceModel invoiceModel);

    int deleteInvoiceByImageId(Long imageId);

    List<Invoice> queryInvsByEdocHeaderId(String edocNo);

    /**
     * 查询所有待验真发票
     * @return
     */
    List<Invoice> selectWaitCheckRealInv();

    List<Invoice> selectInvByEdocHeader(@Param("edocHeaderId")Long edocHeaderId);

    Long getEdocHeaderIdByInvId(@Param("invId")Long invId);
}