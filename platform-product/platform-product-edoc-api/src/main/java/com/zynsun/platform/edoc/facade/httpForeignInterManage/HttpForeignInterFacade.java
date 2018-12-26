package com.zynsun.platform.edoc.facade.httpForeignInterManage;

/**
 * @author jary
 * @creatTime 2018/11/29 3:27 PM
 */
public interface HttpForeignInterFacade {

    /**
     * 603 调阅具体的影像任务数据和图片接口
     *
     * @param req
     * @return
     */
    String getTaskDetailsInter(String req) ;


    /**
     * 604 具体的影像任务评价功能接口
     *
     * @param req
     * @return
     */
    String getTaskEvaluateInter(String req);


    /**
     * 605 影像任务列表接口
     *
     * @param req
     * @return
     */
    String getTaskListInter(String req);

    /**
     * 606 调阅具体某张发票的数据和图片接口
     *
     * @param req
     * @return
     */
    String getInvoiceDetailsInter(String req);

    /**
     * 607 提供按照发票日期范围、扫描日期范围、销方税号进行发票数据的获取
     *
     * @param req
     * @return String
     */
    String getInvoiceListInter(String req);

    /**
     * 608 凭证回写接口
     *
     * @param req
     * @return
     */
    String updateVoucherDetailsInter(String req);

    /**
     * 609 通过凭证号查询影像
     *
     * @param req
     * @return
     */
    String getImageByVoucherInter(String req);

}
