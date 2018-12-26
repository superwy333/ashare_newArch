package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.AccountingArchivesBorrow;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EdocHeaderMapper extends IMapper<EdocHeader> {

    EdocHeaderModel selectTaskByEdocId(EdocHeader edocHeader);

    Page<EdocHeaderModel> queryDoneWorkList(EdocHeaderModel edocHeaderModel);

    Page<EdocHeaderModel> queryToBeWorkList(EdocHeaderModel edocHeaderModel);

    Page<EdocHeaderModel> queryAllWorkList(EdocHeaderModel edocHeaderModel);
    /**
     * 存储过程生成EDOC_NO
     * @param map
     * @return
     */
    public String callEdocProceDure(Map<String, Object> map);

    List<EdocHeaderDTO> selectEdocHeaderList(EdocHeaderDTO edocHeaderDTO);

    /**
     * 通过单证编号以及业务代码获取影像任务
     *
     * @param edocNo
     * @param categoryCode
     * @return
     */
    public List<EdocHeader> findDataListByEdocNoAndCategoryCode(@Param(value = "edocNo") String edocNo,
                                                                @Param(value = "categoryCode") String categoryCode);

    Page<EdocHeaderModel> selectEdocHeaderPage(@Param(value = "edocHeaderModel") EdocHeaderModel headerModel);

    List<EdocHeaderModel> selectEdocHeaders(@Param(value = "edocHeaderModel") EdocHeaderModel headerModel);

    Page<EdocHeaderModel> selectEdocHeaderPageTw(@Param(value = "edocHeaderModel") EdocHeaderModel headerModel);

    EdocHeaderModel selectEdocHeaderAndFormTemplate(@Param(value = "edocType") String edocType);

    List<EdocHeader> findValidEdocHeader(@Param(value = "edocHeader") EdocHeader edocHeader);

    Page<EdocHeader> findBillHeaderToBeSection(@Param(value="edocHeader")EdocHeader edocHeader);

    EdocHeader selectSectionByEdocNo(@Param(value = "edocNo") String edocNo);

    List<EdocHeader> findSectionByIds(@Param(value="ids")List<Long> ids);

    Page<ArchivedAccountModel> selectArchivedAccount(@Param(value="model") ArchivedAccountModel archivedAccountModel);

    Page<EdocHeaderModel> selectArchivedEdocHeader(@Param(value="model") EdocHeaderModel edocHeaderModels);

    /**
     * 查询借阅待办任务列表
     * @return
     */
    Page<EdocHeaderModel> queryExmaineToBeWorkList(EdocHeaderModel edocHeaderModel);

    /**
     * 查询借阅待办任务列表
     * @return
     * @param accountingArchivesBorrowModel
     */
    /*Page<EdocHeaderModel> queryArchBorrow(@Param(value="loginName")String loginName);*/
    Page<AccountingArchivesBorrowModel> queryArchBorrow(@Param(value="accountingArchivesBorrowModel")AccountingArchivesBorrowModel accountingArchivesBorrowModel);

    /**
     * 根据创建人寻找任务列表
     *
     */
   Page<EdocHeaderModel> selectWaitEdocByCrete(@Param(value = "edocHeaderModel") EdocHeaderModel edocHeaderModel);

    /**
     * 查询已采集状态未认证确认的单据， 限制每次50条
     * @return
     */
    List<EdocHeader> selectWaitCertEdoc();
    /**
     * 查询已采集状态未获取相应凭证的单据， 限制每次50条
     *  1.已获取费用凭证，2.已获取付款凭证 3.已获取pdf
     */
    List<EdocHeader> selectNoExpenseVoucher();

    List<EdocHeader> selectNoPayVoucher();

    List<EdocHeader> selectWaitArchive();

    List<EdocHeader> selectExpense();

    /**
     *查询异常任务
     */
    Page<EdocHeaderModel> selectExcEdoc(@Param(value = "edocHeaderModel")EdocHeaderModel edocHeaderModel);

    List<EdocReportModel> selectEdocReport(Map<String,Object> map);

    List<EdocReportModel> selectEdocReportNew(Map<String,Object> map);


    Page<EdocHeaderModel> selectEdocHeaderPageTw_noMatch(@Param(value = "edocHeaderModel")EdocHeaderModel headerModel);

    List<EdocHeaderModel> changeNormal(@Param(value = "edocHeaderModel")EdocHeaderModel edocHeaderModel);

    int updateEdocNoByEdoc(@Param(value = "edocHeaderModel")EdocHeaderModel edocHeaderModel);

    int deleteEdocNoByEdoc(@Param(value="id")Long id);

    int updateEdocNo_HeaderByEdoc(@Param(value = "edocHeaderModel")EdocHeaderModel headerModel);

    Integer findEdocPhysicalStatus(String edocNo);

    List<EdocHeaderModel> selectEdocNoCheckRealResult(String edocNo);

    List<EdocHeader> selectAbNormalEdocHeader();

    List<EdocHeaderModel> getBillImagesByBillHeaderId(@Param(value = "edocHeaderModel")EdocHeaderModel edocHeaderModel);

    List<EdocHeaderModel> selectEdocHeaderByEdocCredentialsId(@Param(value = "edocCredentialsId") Long edocCredentialsId);


}