package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.StringUtil;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.EdocHeaderCredentials;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.mapper.EdocHeaderMapper;
import com.zynsun.platform.edoc.model.*;
import com.zynsun.platform.edoc.service.AccountingSectionItemService;
import com.zynsun.platform.edoc.service.EdocCredentialsService;
import com.zynsun.platform.edoc.service.EdocHeaderCredentialsService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.vo.VoucherDetailsVo;
import constant.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 16:18 2017/12/26
 * @Modified By:
 */
@Service
public class EdocHeaderServiceImpl extends BaseServiceImpl<EdocHeader> implements EdocHeaderService {
    private static final Logger LOGGER= LoggerFactory.getLogger(EdocHeaderServiceImpl.class);

    @Autowired
    private EdocHeaderMapper edocHeaderMapper;
    /*@Autowired
    private TopicProducerSpringProvider topicProducer;*/

    @Autowired
    private AccountingSectionItemService accountingSectionItemService;

    @Autowired
    private EdocCredentialsService edocCredentialsService;

    @Autowired
    private EdocHeaderCredentialsService edocHeaderCredentialsService;


    @Override
    protected IMapper<EdocHeader> getBaseMapper() {
        return edocHeaderMapper;
    }

    @Override
    public EdocHeader checkEdocTaxCodeWhithInvoiceTaxCode(Invoice invoice, EdocHeader edocHeader) throws Exception {
        // TODO 根据edocHeadr.edocType判断调用接口获取任务的税号，和invoice.buyerTaxCode比较，如果不一致，则把任务置为异常
        return edocHeader;
    }

    @Override
    public void updateVoucherDetails(VoucherDetailsVo voucherDetailsVo) throws Exception {
        EdocHeader edocHeader = edocHeaderMapper.selectSectionByEdocNo(voucherDetailsVo.getCoverNo());
        EdocCredentials edocCredentials = null;

        edocCredentials = new EdocCredentials();
        edocCredentials.setCredentialsNum(voucherDetailsVo.getCredentialsNum());
        edocCredentials.setEdocHeaderId(edocHeader.getId());
        edocCredentials.setCredentialsType(voucherDetailsVo.getType());
        edocCredentials.setCredentialsStatus(voucherDetailsVo.getCredentialStatus());
        edocCredentials.setCreateDate(voucherDetailsVo.getCreateDate());
        edocCredentials.setAccountinLedger(voucherDetailsVo.getAccountingLedger());
        edocCredentials.setErpId(voucherDetailsVo.getErpId());

        //更新凭证信息
//        if (voucherDetailsVo.getId() == null && voucherDetailsVo.getWriteBackStatus().equals("1")) edocCredentialsService.add(edocCredentials);
        if (voucherDetailsVo.getId() == null) edocCredentialsService.add(edocCredentials);
        else {
            EdocCredentials queryEdocCredentials = edocCredentialsService.queryById(voucherDetailsVo.getId());
            if (voucherDetailsVo.getWriteBackStatus().equals("2")) edocCredentials.setDeleted(1);
            edocCredentials.setId(queryEdocCredentials.getId());
            edocCredentials.setVersion(queryEdocCredentials.getVersion());
            edocCredentialsService.updateById(edocCredentials);
        }
        Long id = edocCredentials.getId();

        //更新任务信息
        edocHeader.setEdocVoucherNo(voucherDetailsVo.getCredentialsNum());
        edocHeader.setEdocVoucherStatus(Integer.valueOf(voucherDetailsVo.getCredentialStatus()));
        edocHeaderMapper.updateCASByPrimaryKey(edocHeader);

        //添加中间表
        EdocHeaderCredentials eHCParam = new EdocHeaderCredentials();
        eHCParam.setEdocCredentialsId(id);
        eHCParam.setEdocHeaderId(edocHeader.getId());
        List<EdocHeaderCredentials> eHCList = edocHeaderCredentialsService.query(eHCParam);
        if (BeanUtil.isEmpty(eHCList)) edocHeaderCredentialsService.add(eHCParam);
        else {
            eHCList.get(0).setEdocCredentialsId(id);
            eHCList.get(0).setEdocHeaderId(edocHeader.getId());
            edocHeaderCredentialsService.updateByIdSelective(eHCList.get(0));
        }
    }

    @Override
    public EdocHeaderModel selectTaskByEdocId(EdocHeader edocHeader) {
        return edocHeaderMapper.selectTaskByEdocId(edocHeader);
    }

    @Override
    public EdocHeader queryEdocHeaderByBusinessKey(String businessKey) {
        if(StringUtils.isNotEmpty(businessKey)){
            return this.queryById(Long.parseLong(businessKey));
        }else{
            return null;
        }
    }

    @Override
    public EdocHeader queryEdocHeaderByEdocNo(String edocNo, String bizType) {
        if(StringUtils.isEmpty(edocNo)||StringUtils.isEmpty(bizType)){
            return null;
        }
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setDeleted(Constant.DeleteFlag.DEL_NO);
        edocHeader.setEdocNo(edocNo);
        edocHeader.setEdocType(bizType);
        return this.queryOne(edocHeader);
    }

    @Override
    public Page<EdocHeaderModel> queryDoneWorkList(EdocHeaderModel edocHeaderModel) {
        return edocHeaderMapper.queryDoneWorkList(edocHeaderModel);
    }

    @Override
    public Page<EdocHeaderModel> queryToBeWorkList(EdocHeaderModel edocHeaderModel) {
        return edocHeaderMapper.queryToBeWorkList(edocHeaderModel);
    }

    @Override
    public Page<EdocHeaderModel> queryAllWorkList(EdocHeaderModel edocHeaderModel) {
        return edocHeaderMapper.queryAllWorkList(edocHeaderModel);
    }

    @Override
    public List<EdocHeaderDTO> selectEdocHeaderList(EdocHeaderDTO edocHeaderDTO) {
        return edocHeaderMapper.selectEdocHeaderList(edocHeaderDTO);
    }

    @Override
    public boolean checkEdocIsExist(String edocNo,String bizType) {
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocNo(edocNo);
        edocHeader.setEdocType(bizType);
        return this.queryOne(edocHeader)==null?false:true;
    }

    @Override
    public Long createEdocFlowNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gen_num", "");
        edocHeaderMapper.callEdocProceDure(map);
        return Long.parseLong(map.get("gen_num").toString());
    }

    @Override
    public List<EdocHeader> findDataListByEdocNoAndCategoryCode(String edocNo, String categoryCode) {
        return edocHeaderMapper.findDataListByEdocNoAndCategoryCode(edocNo, categoryCode);
    }

    @Override
    public int saveAndSendMessage(EdocHeader edocHeader){
        int count=this.add(edocHeader);
        //创建影像任务成功，发送消息到消息队列
        //topicProducer.publishNoWrapperMessage("edoc-workflow-topic", edocHeader);
        return count;
    }

    @Override
    public Long selectEdocHeaderIdByBillId(Long billId,Integer edocLevel) {
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocType(Constant.BizTypeCode.ACCOUNT_TYPE_CODE);
        edocHeader = edocHeaderMapper.selectOne(edocHeader);
        if(!BeanUtil.isEmpty(edocHeader)&&edocHeader.getId()!=null){
            return edocHeader.getId();
        }else{
            return null;
        }
    }

    @Override
    public Page<EdocHeaderModel> queryEdocHeaderPage(EdocHeaderModel headerModel) {
        return edocHeaderMapper.selectEdocHeaderPage(headerModel);
    }

    @Override
    public List<EdocHeaderModel> queryEdocHeaders(EdocHeaderModel headerModel) {
        return edocHeaderMapper.selectEdocHeaders(headerModel);
    }

    @Override
    public Page<EdocHeaderModel> queryEdocHeaderPageTw(EdocHeaderModel headerModel) {
        return edocHeaderMapper.selectEdocHeaderPageTw(headerModel);
    }

    @Override
    public String initMenu(String edocType) {
        EdocHeaderModel edocHeaderModel = edocHeaderMapper.selectEdocHeaderAndFormTemplate(edocType);
        return StringUtils.isBlank(edocHeaderModel.getFormTemplateJson())? "" : edocHeaderModel.getFormTemplateJson() ;
    }

    @Override
    public List<EdocHeader> findValidEdocHeader(EdocHeader edocHeader) {
        return edocHeaderMapper.findValidEdocHeader(edocHeader);
    }

    @Override
    public Page<EdocHeader> findBillHeaderToBeSection(EdocHeader edocHeader) {
        return edocHeaderMapper.findBillHeaderToBeSection(edocHeader);
    }

    @Override
    public EdocHeader findSectionByEdocNo(String edocNo) {
        return edocHeaderMapper.selectSectionByEdocNo(edocNo);
    }

    @Override
    public List<EdocHeader> findSectionByIds(List<Long> ids) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<EdocHeader> edocHeaderList = edocHeaderMapper.findSectionByIds(ids);
        /*if(edocHeaderList!=null&&!edocHeaderList.isEmpty()){
            for(EdocHeader model:edocHeaderList){
                if(model.get()!=null){
                    model.setScanDate_1(sdf.format(model.getScanDate()));
                }
            }
        }*/
        return edocHeaderList;
    }


    @Override
    public Page<ArchivedAccountModel> findArchivedAccounts(ArchivedAccountModel archivedAccountModel) {
        Page<ArchivedAccountModel> result = edocHeaderMapper.selectArchivedAccount(archivedAccountModel);
        return result;
    }

    @Override
    public Page<EdocHeaderModel> querArchiveds(EdocHeaderModel edocHeaderModel) {
        Page<EdocHeaderModel> result=edocHeaderMapper.selectArchivedEdocHeader(edocHeaderModel);
        return result;
    }


    @Override
    public List<EdocHeader> findBillHeaderBySectionId(Long id) {
        List<Long> billHeaderIds = accountingSectionItemService.findBillHeaderIdsBySectionId(id.toString());
        return findEditSectionByIds(billHeaderIds);
    }

    @Override
    public List<EdocHeader> findEditSectionByIds(List<Long> ids) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setEdocTaskStatus(Constant.BizStatus.TOBEARCHIVED);
        List<EdocHeader> models = this.query(edocHeader);*/
        /*if(models!=null&&!models.isEmpty()){
            for(EdocHeader model:models){
                if(model.getScanDate()!=null){
                    model.setScanDate_1(sdf.format(model.getScanDate()));
                }
            }
        }*/
        List<EdocHeader> models = new ArrayList<>();
        for (Long id:ids) {
            EdocHeader edocHeader = this.queryById(id);
            if (!BeanUtil.isEmpty(edocHeader)) {
                models.add(edocHeader);
            }
        }
        return models;
    }

    @Override
    public Page<EdocHeaderModel> queryExmaineToBeWorkList(EdocHeaderModel edocHeaderModel) {
         return edocHeaderMapper.queryExmaineToBeWorkList(edocHeaderModel);
    }

    @Override
    public Page<AccountingArchivesBorrowModel> queryArchBorrow(AccountingArchivesBorrowModel accountingArchivesBorrowModel) {
        return  edocHeaderMapper.queryArchBorrow(accountingArchivesBorrowModel);
    }

    @Override
    public Page<EdocHeaderModel> selectWaitEdocByCrete(EdocHeaderModel edocHeaderModel) {
        return edocHeaderMapper.selectWaitEdocByCrete(edocHeaderModel);
    }

    @Override
    public List<EdocHeader> queryWaitCertEdoc() {
        return edocHeaderMapper.selectWaitCertEdoc();
    }

    @Override
    public List<EdocHeader> queryNoExpenseVoucher() {
        return edocHeaderMapper.selectNoExpenseVoucher();
    }

    @Override
    public List<EdocHeader> queryNoPayVoucher() {
        return edocHeaderMapper.selectNoPayVoucher();
    }

    @Override
    public List<EdocHeader> queryWaitArchive() {
        return edocHeaderMapper.selectWaitArchive();
    }

    @Override
    public List<EdocHeader> queryExpense() {
        return edocHeaderMapper.selectExpense();
    }

    @Override
    public Page<EdocHeaderModel> selectExcEdoc(EdocHeaderModel edocHeaderModel) {
        return edocHeaderMapper.selectExcEdoc(edocHeaderModel);
    }

    @Override
    public List<EdocReportModel> selectEdocReport(Map<String,Object> queryTime) {
        return edocHeaderMapper.selectEdocReportNew(queryTime);
    }

    @Override
    public int updatePhysicalStatus(EdocHeader edocHeader,Integer billStatus) {
        int i=0;
        if (BeanUtil.isEmpty(edocHeader)) {
            return i;
        }
        if(billStatus==1) {
            edocHeader.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.WAIT_SEND);
           i= this.updateByIdSelective(edocHeader);
        }if(billStatus==2){
            edocHeader.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.WAIT_RECEIVER);
           i= this.updateByIdSelective(edocHeader);
        }
        return i;
    }
    @Override
    //查询客户端未匹配到单据任务列表==即异常影像件列表
    public Page<EdocHeaderModel> queryEdocHeaderPageTw_noMatch(EdocHeaderModel headerModel) {
        return edocHeaderMapper.selectEdocHeaderPageTw_noMatch(headerModel);
    }

    @Override
    public List<EdocHeaderModel> changeNormal(EdocHeaderModel headerModel) {
        return edocHeaderMapper.changeNormal(headerModel);
    }

    @Override
    public int updateEdocNoByEdoc(EdocHeaderModel headerModel) {
        return edocHeaderMapper.updateEdocNoByEdoc(headerModel);
    }

    @Override
    public int deleteEdocNoByEdoc(Long id) {
        return edocHeaderMapper. deleteEdocNoByEdoc(id);
    }

    @Override
    public int updateEdocNo_HeaderByEdoc(EdocHeaderModel headerModel) {
        return edocHeaderMapper.updateEdocNo_HeaderByEdoc(headerModel);
    }

    @Override
    public Integer findEdocPhysicalStatus(String edocNo) {
        return edocHeaderMapper.findEdocPhysicalStatus(edocNo);
    }

    @Override
    public List<EdocHeaderModel> queryEdocNoCheckRealResult(String edocNo) {
        return edocHeaderMapper.selectEdocNoCheckRealResult(edocNo);
    }

    @Override
    public List<EdocHeader> queryAbnormalEdocHeader() {
        return edocHeaderMapper.selectAbNormalEdocHeader();
    }

    @Override
    public List<EdocHeaderModel> getBillImagesByBillHeaderId(EdocHeaderModel headerModel) {
        return edocHeaderMapper.getBillImagesByBillHeaderId(headerModel);
    }

    @Override
    public List<EdocHeaderModel> selectEdocHeaderByEdocCredentialsId(Long edocCredentialsId) {
        return edocHeaderMapper.selectEdocHeaderByEdocCredentialsId(edocCredentialsId);
    }
}
