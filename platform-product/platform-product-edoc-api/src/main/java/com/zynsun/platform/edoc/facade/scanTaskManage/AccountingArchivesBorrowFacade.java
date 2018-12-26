package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;

public interface AccountingArchivesBorrowFacade {

    /**
     * 查询申请借阅的历史列表
     * @return
     */
    ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> queryList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);


    ExecuteResult<Integer> insertArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);


    ExecuteResult<Integer> updateArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO);

    ExecuteResult<String> applyForBorrow(Long edocId , Long archivesId, String createBy ,String createTime, String saveBackTime);

    ExecuteResult<String> applyForBorrowBySection(Long sectionId , String createBy ,String createTime, String saveBackTime);

    ExecuteResult<Boolean> finishExamineWorkTask (String taskId , String param , Long edocId);

    /**
     * 借阅档案归还
     * @return
     */
    ExecuteResult<String> archiveSaveBack(Long id);

    /**
     * 借阅档案归还
     * @return
     */
    ExecuteResult<String> archiveSaveBackBySection(Long sectionId);

}
