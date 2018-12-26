package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.model.InvoiceItemModel;
import org.apache.ibatis.annotations.Param;

public interface InvoiceItemMapper extends IMapper<InvoiceItem> {

    void deleteInvoiceItemByEdocHeaderId(Long edocHeaderId);

    Page<InvoiceItem> selectByPage(@Param("invoiceItemModel") InvoiceItemModel invoiceItemModel);

    void deleteInvoiceItemByInvoiceId(Long invoiceId);

    Page<InvoiceItem> editInvItemByInvItemId(@Param("invoiceItemModel")InvoiceItemModel invoiceItemModel);


}