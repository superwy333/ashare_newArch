package com.zynsun.platform.edoc.facade.scanTaskManage;


import com.zynsun.openplatform.dto.TreeDTO;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.EdocImageDTO;
import com.zynsun.platform.edoc.dto.EdocReportDTO;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingSectionDTO;
import com.zynsun.platform.edoc.dto.archive.ArchivedAccountDTO;
import org.codehaus.jackson.map.Serializers;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:05 2018/1/2
 * @Modified By:
 */
public interface EdocHeaderFacade extends BaseFacade<EdocHeaderDTO>{

    /*Map<String,Object> test(@RequestBody EdocHeaderDTO edocHeaderDTO);*/


    Map<String,Object> edocHeaderDetails(Map<String,Object> map);

    /**
     * 影像只展示Jpg格式
     * @author Jary
     * @param map
     * @return
     */
    Map<String,Object> edocHeaderDetails1(Map<String,Object> map);


    Map<String,Object> edocHeaderDataGrid(Map<String,Object> map);

    Map<String,Object> removeEdocHeader(Map<String,Object> map);

    Map<String,Object> edocHeaderCombobox(Map<String,Object> map);

    Map<String,Object> edocHeaderSeal(Map<String,Object> map);

    Map<String,Object> completeAbnormal(Map<String,Object> map);

    Map<String,Object> edocAbnormalCheck(Map<String,Object> map);

    Map<String,Object> edocAbnormalDetails(Map<String,Object> map);

    boolean edocAbnormalCheck(Long edocHeaderId);



    /**
     * 根据影像任务主键查询影像任务详情
     * @param id
     * @return
     */
    ExecuteResult<EdocHeaderDTO> selectEdocHeaderById(Long id);

    /**
     * 根据邮包状态同步实物状态
     * @param
     * @return
     */
    ExecuteResult<String> updateEdocPhysicalStatus(String billNo,Integer billStatus);


    /**
     * 根据条件查询影像任务详情
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<EdocHeaderDTO> queryEdocHeaderByCondition(EdocHeaderDTO edocHeaderDTO);

    /**
     * 根据条件删除影像任务详情
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<Integer> delEdocHeaderByCondition(EdocHeaderDTO edocHeaderDTO);

    /**
     * 根据流程业务主键查询影像任务详情
     * @param businessKey
     * @return
     */
    ExecuteResult<EdocHeaderDTO> selectEdocHeaderByBusinessKey(String businessKey);

    /**
     * 查询已办任务列表
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> queryDoneWorkList(EdocHeaderDTO edocHeaderDTO);

    /**
     * 查询待办任务列表
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> queryToBeWorkList(EdocHeaderDTO edocHeaderDTO);

    /**
     * 管理员查看进行中的所有流程
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> queryAllWorkList(EdocHeaderDTO edocHeaderDTO);

    /**
     * 客户端创建影像任务
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<EdocHeaderDTO> edocClientSaveEdocHeader(EdocHeaderDTO edocHeaderDTO);

    /**
     * 影像开始上传前校验
     *
     * @param edocNo
     * @param categoryCode
     * @return
     */
    ExecuteResult<EdocHeaderDTO> beforeUploadCheck(String edocNo, String scanFlag, String categoryCode);


    List<EdocHeaderDTO> findEdocHeaderList(EdocHeaderDTO edocHeaderDTO);

    List<EdocHeaderDTO> selectEdocHeaderList(EdocHeaderDTO edocHeaderDTO);

    EdocHeaderDTO queryById1(Long billHeadId);

    /**
     * @Description：[根据主键查询]
     * @Author：PeidongWang
     * @Param：id
     * @Date：2017/6/21 9:19
     */
    public ExecuteResult<EdocHeaderDTO> findEdocHeaderById(Long id);

    ExecuteResult<PageInfo<EdocHeaderDTO>> findEdocHeaderPage(EdocHeaderDTO headerDTO);

    /**
     * 包含了动态表单字段
     * @param headerDTO
     * @return
     */
    String findEdocHeaderPageTw(EdocHeaderDTO headerDTO);

    /**
     * 根据edocHeaderId 查询模版json
     */
    String findFiledJsonData(Long edocHeaderId);

    /**
     * 初始化任务列表的菜单
     */
    String initMenu(String bizType);

    ExecuteResult<Boolean> reviewEdocHeader(EdocHeaderDTO edocHeaderDTO,String reviewState);

    ExecuteResult<EdocHeaderDTO> findValidEdocHeader(EdocHeaderDTO edocHeaderDTO);

    /**
     * 调用E7通知影像状态接口
     */
    //String sendBill(Long edocHeaderId);

    /**
     * 通知影像任务完成
     */
    ExecuteResult<Boolean> completeEdocTask(EdocHeaderDTO edocHeaderDTO);

    ExecuteResult<Integer> updateEdocHeader(EdocHeaderDTO edocHeader);


    /**
     * 追加保存电子发票信息
     * @param edocId
     * @param invoiceDTO
     * @param filePath
     * @return
     */
    ExecuteResult<String> saveElecInvoice(String originalFilename, Long edocId, InvoiceDTO invoiceDTO,String filePath,String createBy);

    /**
     * 替换电子发票信息
     *
     * @param docImageDTO
     * @param invInfoDTO
     * @return
     */
    ExecuteResult<String> replaceElecInvoice(EdocImageDTO docImageDTO, InvoiceDTO invInfoDTO);

    /**
     * 保存或更新影像图片，用于只有影像没有发票的场景，比如pdf上传，发票数据解析不出来
     * @param originalFilename
     * @param edocId
     * @param filePath
     * @return
     */
    ExecuteResult<String> saveImage(String originalFilename,Long edocId,String filePath);
    ExecuteResult<String> saveImage(EdocImageDTO edocImageDTO);
    ExecuteResult<String> saveImage(String originalFilename,Long edocId,String filePath,String edocImageType,String createBy);

    /**
     * 返回待归档池单据列表
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> findBillHeaderToBeSection(EdocHeaderDTO edocHeaderDTO);


    /**
     * 根据edocNo找到待分册的单据
     * @param edocNo
     * @return
     */
    ExecuteResult<EdocHeaderDTO> findSectionByEdocNo(String edocNo);

    ExecuteResult<PageInfo<ArchivedAccountDTO>> findArchivedAccount(ArchivedAccountDTO edocHeaderDTO);

    ExecuteResult<PageInfo<EdocHeaderDTO>> queryArchivedAccount(EdocHeaderDTO edocHeaderDTO);


    /**
     * 根据分册id查询分册的影像任务数据
     * @param id
     * @return
     */
    ExecuteResult<List<EdocHeaderDTO>> findBillHeaderBySectionId(Long id);

    ExecuteResult<EdocHeaderDTO> editSectionByEdocNo(String edocNo,Long sectionId);

    ExecuteResult<Integer> deleteSectionItem(Long billHeaderId,Long sectionId);

    /**
     * 查询借阅待办任务列表
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> queryExmaineToBeWorkList(EdocHeaderDTO edocHeaderDTO);

    /**
     * 查询借阅申请历史列表
     * @return
     */
    ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> queryArchBorrow(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);


    /**
     * 保存凭证影像
     * @param edocHeaderDTO
     * @param fileNameList
     * @return
     */
    ExecuteResult<String> saveCendentialsImages(EdocHeaderDTO edocHeaderDTO,List<String> fileNameList);

    /**
     * 退单接口
     */
    ExecuteResult<EdocHeaderDTO> rejectHeader(Map<String,String> input);

    /**
     * 创建任务之前检查
     */
    ExecuteResult<EdocHeaderDTO> beforeCreateCheck(String edocNo, String categoryCode);


    /**
     * 获取待采集的任务号
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> queryWaitEdoc(EdocHeaderDTO edocHeaderDTO);

    //ExecuteResult<EdocHeaderDTO> qryExpStatus(EdocHeaderDTO edocHeaderDTO);

    /**
     * 新希望共享webservice接口
     *      是否待认证
     *
     */
    //ExecuteResult<String> isWaitCert();

    /**
     * 新希望共享webservice接口
     *      是否可归档
     *
     */
    ExecuteResult<String> isCanArchive();

    /**
     * 新希望共享webservice接口
     *      获取凭证
     */
    //ExecuteResult<String> getExpenseVoucher();
    /**
     * 新希望共享webservice接口
     *      获取付款凭证
     */
    ExecuteResult<String> getPayVoucher();

    /**
     * 获取凭证pdf
     * 通过JDE
     * @return
     */
    //ExecuteResult<String> getVoucherPDF();


    /**
     * 获取凭证pdf
     * 通过JDE
     * @return
     */
    //ExecuteResult<String> getVoucherPDFByJDE();

    /**
     * jde法人公司信息
     * @return
     */
    //ExecuteResult<String> syncJdeCompany();

    /**
     * 获取费用类型
     * @return
     */
    //ExecuteResult<String> getCostType();

    /**
     * 复制资金pdf文件至指定目录
     */
    //ExecuteResult<String> copyZjPdf();

    /**
     * 处理获取的资金pdf文件
     */
    //ExecuteResult<String> handZjPdf();

    ExecuteResult<String> completeUpload(Long id);

    /**
     * 强制匹配
     */
    ExecuteResult<EdocHeaderDTO> matchEdoc(EdocHeaderDTO waitMatchHeaderDTO, EdocHeaderDTO waitScanHeaderDTO);


    /**
     * 查询异常任务
     */
    ExecuteResult<PageInfo<EdocHeaderDTO>> findExcEdoc(EdocHeaderDTO edocHeaderDTO);

    //ExecuteResult<String> inform();

    //ExecuteResult<String> inform(Long edocHeaderId);

    ExecuteResult<EdocHeaderDTO> findEdocHeaderByCredentialsNo(String credentialsNo);


    /**
     * 查询客户端未匹配到单据任务列表==即异常影像件列表
     *
     */
    String findEdocHeaderPageTw_noMatch(EdocHeaderDTO headerDTO);


    ExecuteResult<List<TreeDTO>> queryCostTypeToTree(Integer pageNum, Integer pageSize, String fNameOrFid);

    ExecuteResult<List<EdocReportDTO>> parseEdocReport(Map<String,Object> queryTime);

    /**
     * 创建任务
     * @param input
     * @return
     */
    ExecuteResult<EdocHeaderDTO> createHeader(Map<String,String> input);


    /**
     * 创建影像任务公共方法
     * 只创建单据
     * 不启动流程
     * @param edocHeaderDTO
     * @return 创建成功单据的edocHeaderId
     */
    ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO);


    /**
     * 创建影像任务公共方法
     * 同时创建影像
     * @param edocHeaderDTO
     * @param edocImageDTOList
     * @return
     */
    ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO, List<EdocImageDTO> edocImageDTOList);


    /**
     * 创建影像任务公共方法
     * 同时创建影像
     * 不创建发票！！！
     * @param edocHeaderDTO
     * @param pdfFilePath
     * @return
     */
    //ExecuteResult<Long> createHeader(EdocHeaderDTO edocHeaderDTO, String pdfFilePath, String edocImageType);

    ExecuteResult<String> updateEdocNo(EdocHeaderDTO edocHeaderDTO);





    ExecuteResult<EdocHeaderDTO> changeNormal(EdocHeaderDTO headerDTO);

    /**
     * 根据条件查询任务单据
     * @param queryEdocHeader
     * @return
     */
    ExecuteResult<List<EdocHeaderDTO>> queryEdocHeaderListByCondition(EdocHeaderDTO queryEdocHeader);

    /**
     *  删除异常影像件
     * @param id
     * @return
     */
    ExecuteResult<String> deleteExpEdoc(Long id);

    /**
     * 根据单号查询单据
     * @return
     */
    ExecuteResult<List<EdocHeaderDTO>> queryEdocNoCheckRealResult(String edocNo);

    /**
     * 查询验真结果
     * @param id
     * @return
     */
    String queryRealResultByEdoc(Long id);

    /**
     * 档案岗标记补扫
     * @param edocHeaderId
     * @return
     */
    ExecuteResult<String> archiveRescan(Long edocHeaderId);

    /**
     * 查询单据下发票是否存在：法人公司不匹配,发票数据异常
     * @param id
     * @return
     */
    String getCheckRealRemark(Long id);

    /**
     * 影像任务自动接口
     */

    //void automaticTask();

    /**
     * 获取凭证pdf
     * 通过JDE
     * @return
     */
    //ExecuteResult<String> getExpensePdf();

    /**
     * 根据单据id删除单据及单据下影像
     * @param edocHeaderId
     * @return
     */
    ExecuteResult<String> deleteEdocHeaderById(Long edocHeaderId);
}
