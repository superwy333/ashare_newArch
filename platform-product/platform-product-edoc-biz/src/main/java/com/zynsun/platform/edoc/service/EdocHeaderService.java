package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.model.*;
import com.zynsun.platform.edoc.vo.VoucherDetailsVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 16:17 2017/12/26
 * @Modified By:
 */
public interface EdocHeaderService extends BaseService<EdocHeader> {

    EdocHeader checkEdocTaxCodeWhithInvoiceTaxCode(Invoice invoice,EdocHeader edocHeader) throws Exception;

    void updateVoucherDetails(VoucherDetailsVo voucherDetailsVo) throws Exception;

    EdocHeaderModel selectTaskByEdocId(EdocHeader edocHeader);

    EdocHeader queryEdocHeaderByBusinessKey(String businessKey);

    EdocHeader queryEdocHeaderByEdocNo(String edocNo, String bizType);

    Page<EdocHeaderModel> queryDoneWorkList(EdocHeaderModel edocHeaderModel);

    Page<EdocHeaderModel> queryToBeWorkList(EdocHeaderModel edocHeaderModel);

    Page<EdocHeaderModel> queryAllWorkList(EdocHeaderModel edocHeaderModel);

    List<EdocHeaderDTO> selectEdocHeaderList(EdocHeaderDTO edocHeaderDTO);


    /**
     * 判断edocNo是否存在 如果存在返回true
     * @param edocNo
     * @return
     */
    boolean checkEdocIsExist(String edocNo,String bizType);

    /**
     * 通过存储过程获取EdocNo
     * @return
     */
    public Long createEdocFlowNum();

    /**
     * 通过单证编号以及业务代码获取影像任务
     *
     * @param edocNo
     * @param categoryCode
     * @return
     */
    public List<EdocHeader> findDataListByEdocNoAndCategoryCode(String edocNo, String categoryCode);

    /**
     * 创建影像任务并发送启动流程的消息到消息队列（适用高并发启动流程场景）
     * @param edocHeader
     * @return
     */
    public int saveAndSendMessage(EdocHeader edocHeader);

    Long selectEdocHeaderIdByBillId(Long billId,Integer edocLevel);

    Page<EdocHeaderModel> queryEdocHeaderPage(EdocHeaderModel headerModel);

    List<EdocHeaderModel> queryEdocHeaders(EdocHeaderModel headerModel);

    public Page<EdocHeaderModel> queryEdocHeaderPageTw(EdocHeaderModel headerModel);

    /**
     * 初始化任务列表的菜单
     */
    String initMenu(String edocType);

    List<EdocHeader> findValidEdocHeader(EdocHeader edocHeader);

    /**
     * 查询待归档任务池单据
     * @param
     * @return
     */
    Page<EdocHeader> findBillHeaderToBeSection(EdocHeader edocHeader);


    EdocHeader findSectionByEdocNo(String edocNo);

    List<EdocHeader> findSectionByIds(List<Long> ids);

    Page<ArchivedAccountModel> findArchivedAccounts(ArchivedAccountModel archivedAccountModel);

    Page<EdocHeaderModel> querArchiveds(EdocHeaderModel edocHeaderModel);

    /**
     * 根据分册id获取分册的影像任务数据
     * @param id
     * @return
     */
    List<EdocHeader> findBillHeaderBySectionId(Long id);

    List<EdocHeader> findEditSectionByIds(List<Long> ids);

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
    Page<AccountingArchivesBorrowModel> queryArchBorrow(AccountingArchivesBorrowModel accountingArchivesBorrowModel);

    /**
     * 查询待采集的任务列表
     */
    Page<EdocHeaderModel> selectWaitEdocByCrete(EdocHeaderModel edocHeaderModel);

    /**
     * 查询已采集或凭证冲销状态单据是否可归档
     * @return
     */
    List<EdocHeader> queryWaitArchive();

    /**
     * 查询已获取费用凭证 没有pdf的
     */
    List<EdocHeader> queryExpense();

    /**
     * 查询已采集或凭证冲销状态未认证确认的单据， 限制每次50条
     */
    List<EdocHeader> queryWaitCertEdoc();

    /**
     * 查询已采集或凭证冲销状态未获取相应凭证的单据， 限制每次50条
     *  0.未处理 1.已获取费用凭证，2.已获取付款凭证 3.已获取pdf
     * @return
     */
    List<EdocHeader> queryNoExpenseVoucher();

    List<EdocHeader> queryNoPayVoucher();


    /**
     * 查询异常任务
     */
    Page<EdocHeaderModel> selectExcEdoc(EdocHeaderModel edocHeaderModel);

    List<EdocReportModel> selectEdocReport(Map<String,Object> queryTime);

    /**
     * 根据邮包明细状态同步任务列表实物状态
     * @param edocHeader
     * @param billStatus
     * @return
     */
    int updatePhysicalStatus(EdocHeader edocHeader,Integer billStatus);

    /**
     *查询客户端未匹配到单据任务列表==即异常影像件列表
     * @param headerModel
     * @return
     */
    public Page<EdocHeaderModel> queryEdocHeaderPageTw_noMatch(EdocHeaderModel headerModel);

    /**
     * 查询该任务下是否已经存在表中
     *
     * @return
     */
    List<EdocHeaderModel> changeNormal(EdocHeaderModel headerModel);

    ////需要把该单据下所有发票信息和影像关联的数据--重新绑定
    int updateEdocNoByEdoc(EdocHeaderModel headerModel);

    //原待匹配的影像件进行逻辑删除--前端将待匹配影像件id暂存在PublicType
    int deleteEdocNoByEdoc(Long id);

    //edoc_header 将正常单据下影像状态改为已采集和scan_finished由0改为1
    int updateEdocNo_HeaderByEdoc(EdocHeaderModel headerModel);

    //根据实物的状态判断是否可以签收
    Integer findEdocPhysicalStatus(String edocNo);

    /**
     * 根据单号查询单据(验真结果查询用)
     * @return
     */
    List<EdocHeaderModel> queryEdocNoCheckRealResult(String edocNo);

    /**
     * 查询异常且是待采集的报账单任务单据
     * @return
     */
    List<EdocHeader> queryAbnormalEdocHeader();

    /**
     * 获取条码信息及条码下所有影像
     * @Auther Jaryzhang
     * @param headerModel
     * @return
     */
    List<EdocHeaderModel> getBillImagesByBillHeaderId(EdocHeaderModel headerModel);

    List<EdocHeaderModel> selectEdocHeaderByEdocCredentialsId(Long edocCredentialsId);
}
