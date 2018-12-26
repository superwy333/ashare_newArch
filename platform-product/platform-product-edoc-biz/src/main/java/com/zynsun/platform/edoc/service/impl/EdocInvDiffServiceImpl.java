package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.EdocInvDiff;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.mapper.EdocInvDiffMapper;
import com.zynsun.platform.edoc.model.InvDiffColumnModel;
import com.zynsun.platform.edoc.model.InvModifyModel;
import com.zynsun.platform.edoc.service.EdocInvDiffService;
import com.zynsun.platform.edoc.service.InvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发票修改记录业务实现
 */
@Service
public class EdocInvDiffServiceImpl extends BaseServiceImpl<EdocInvDiff> implements EdocInvDiffService {

    @Autowired
    private EdocInvDiffMapper edocInvDiffMapper;
    @Autowired
    private InvoiceService invoiceService;

    @Override
    protected IMapper<EdocInvDiff> getBaseMapper() {
        return edocInvDiffMapper;
    }

    @Override
    public int createInvChangeHistory(InvModifyModel newInvModel) {
        int i = 0;
        Invoice oldInv = invoiceService.queryById(newInvModel.getId());
        InvModifyModel oldInvModel = DozerUtil.map(oldInv, InvModifyModel.class);
        i = checkSameCollumn(newInvModel, oldInvModel, i);
        if (StringUtils.isNotEmpty(newInvModel.getInvTotal())) {
            if (!newInvModel.getInvTotal().equals(oldInvModel.getInvTotal())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("价税合计");
                diff.setOldVal(oldInvModel.getInvTotal());
                diff.setNewVal(newInvModel.getInvTotal());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getInvTax())) {
            if (!newInvModel.getInvTax().equals(oldInvModel.getInvTax())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("发票税额");
                diff.setOldVal(oldInvModel.getInvTax());
                diff.setNewVal(newInvModel.getInvTax());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getSalerName())) {
            if (!newInvModel.getSalerName().equals(oldInvModel.getSalerName())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("供应商名称");
                diff.setOldVal(oldInvModel.getSalerName());
                diff.setNewVal(newInvModel.getSalerName());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getBuyerName())) {
            if (!newInvModel.getBuyerName().equals(oldInvModel.getBuyerName())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("购方名称");
                diff.setOldVal(oldInvModel.getBuyerName());
                diff.setNewVal(newInvModel.getBuyerName());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getBuyerTaxCode())) {
            if (!newInvModel.getBuyerTaxCode().equals(oldInvModel.getBuyerTaxCode())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("购方税号");
                diff.setOldVal(oldInvModel.getBuyerTaxCode());
                diff.setNewVal(newInvModel.getBuyerTaxCode());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getSalerTaxCode())) {
            if (!newInvModel.getSalerTaxCode().equals(oldInvModel.getSalerTaxCode())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("供应商税号");
                diff.setOldVal(oldInvModel.getSalerTaxCode());
                diff.setNewVal(newInvModel.getSalerTaxCode());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getInvMachineNo())) {
            if (!newInvModel.getInvMachineNo().equals(oldInvModel.getInvMachineNo())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("机器编码");
                diff.setOldVal(oldInvModel.getInvMachineNo());
                diff.setNewVal(newInvModel.getInvMachineNo());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(newInvModel.getRemarks())) {
            if (!newInvModel.getRemarks().equals(oldInvModel.getRemarks())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("备注");
                diff.setOldVal(oldInvModel.getRemarks());
                diff.setNewVal(newInvModel.getRemarks());
                diff.setInvId(newInvModel.getId());
                this.add(diff);
                i++;
            }
        }
        return i;
    }

    @Override
    public Page<InvDiffColumnModel> selectByPage(InvDiffColumnModel invDiffColumnModel) {
        return edocInvDiffMapper.selectByPage(invDiffColumnModel);
    }

    private int checkSameCollumn(InvModifyModel invoice, InvModifyModel oldInvModel, int i) {
        if (StringUtils.isNotEmpty(invoice.getInvCode())) {
            if (!invoice.getInvCode().equals(oldInvModel.getInvCode())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("发票代码");
                diff.setOldVal(oldInvModel.getInvCode());
                diff.setNewVal(invoice.getInvCode());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }
        if (StringUtils.isNotEmpty(invoice.getInvNo())) {
            if (!invoice.getInvNo().equals(oldInvModel.getInvNo())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("发票号码");
                diff.setOldVal(oldInvModel.getInvNo());
                diff.setNewVal(invoice.getInvNo());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(invoice.getInvDate())) {
            if (!invoice.getInvDate().equals(oldInvModel.getInvDate().trim())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("发票日期");
                diff.setOldVal(oldInvModel.getInvDate());
                diff.setNewVal(invoice.getInvDate());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(invoice.getInvAmount())) {
            if (!invoice.getInvAmount().equals(oldInvModel.getInvAmount())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("发票金额");
                diff.setOldVal(oldInvModel.getInvAmount());
                diff.setNewVal(invoice.getInvAmount());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(invoice.getBuyerCompanyCode())) {
            if (!invoice.getBuyerCompanyCode().equals(oldInvModel.getBuyerCompanyCode())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("购方公司代码");
                diff.setOldVal(oldInvModel.getBuyerCompanyCode());
                diff.setNewVal(invoice.getBuyerCompanyCode());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }

        if (StringUtils.isNotEmpty(invoice.getSalerCompanyCode())) {
            if (!invoice.getSalerCompanyCode().equals(oldInvModel.getSalerCompanyCode()) && StringUtils.isNotEmpty(invoice.getSalerCompanyCode())) {
                EdocInvDiff diff = new EdocInvDiff();
                diff.setInvColumn("供应商公司代码");
                diff.setOldVal(oldInvModel.getSalerCompanyCode());
                diff.setNewVal(invoice.getSalerCompanyCode());
                diff.setInvId(invoice.getId());
                this.add(diff);
                i++;
            }
        }
        return i;
    }

}
