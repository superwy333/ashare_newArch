package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.model.AccountingSectionModel;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 12:42
 * @Modified By:
 */
public interface AccountingSectionService extends BaseService<AccountingSection> {

    /**
     * 判断分册是否存在 不存在插入一条分册 然后分册明细数据
     * @param accountingSection
     * @return
     */
    String findOrCreateAccountingSection(AccountingSection accountingSection,List<Long> ids);

    /**
     * 根据条件返回分册列表
     * @param accountingSectionModel
     * @return
     */
    Page<AccountingSectionModel> findAccountingSections(AccountingSectionModel accountingSectionModel);

    /**
     * 生成分册编号
     * @param accountingSection
     * @return
     */
    String findBookNo(AccountingSection accountingSection);

    /**
     * 根据分册编号查询分册记录
     * @param recordsNo
     * @return
     */
    AccountingSection findAccountingSectionByRecordsNo(String recordsNo);

    /**
     * 根据条件返回总册数
     * @param bizType
     * @return
     */
    Integer findVolumesByBizType(String bizType,String startDate,String endDate,String companyCode);

    /**
     * 保存分册记录
     * @param accountingSection
     * @return
     */
    Integer saveAccountingSection(AccountingSection accountingSection);

    /**
     * 根据id数组返回分册信息数组
     * @param ids
     * @return
     */
    List<AccountingSection> findAccountingSectionsByIds(List<Long> ids);

    /**
     * 生成分册编号
     * @param accountingSection
     * @return
     */
    String findBoxNo(AccountingSection accountingSection);


    List<AccountingSectionModel> findSectionsBoxId(Long id);

}
