package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.mapper.InvoiceItemMapper;
import com.zynsun.platform.edoc.model.InvoiceItemModel;
import com.zynsun.platform.edoc.service.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/08 11:14
 */
@Service
public class InvoiceItemServiceImpl extends BaseServiceImpl<InvoiceItem> implements InvoiceItemService {

    @Autowired
    private InvoiceItemMapper invoiceItemMapper;

    @Override
    protected IMapper<InvoiceItem> getBaseMapper() {
        return invoiceItemMapper;
    }

    @Override
    public void deleteInvoiceItemByEdocHeaderId(Long edocHeaderId) {
        invoiceItemMapper.deleteInvoiceItemByEdocHeaderId(edocHeaderId);
    }

    @Override
    public Page<InvoiceItem> queryInvItemsByPage(InvoiceItemModel invoiceItemModel) {
        return invoiceItemMapper.selectByPage(invoiceItemModel);
    }

    @Override
    public void deleteInvoiceItemByInvoiceId(Long invoiceId) {
        invoiceItemMapper.deleteInvoiceItemByInvoiceId(invoiceId);
    }

    @Override
    public Page<InvoiceItem> editInvItemByInvItemId(InvoiceItemModel invoiceItemModel) {
        return invoiceItemMapper.editInvItemByInvItemId(invoiceItemModel);
    }

/*    @Override
    public InvoiceItem editInvItemByInvItemId_one(InvoiceItemModel invoiceItemModel) {
        return invoiceItemMapper.editInvItemByInvItemId_one(invoiceItemModel);
    }*/
}
