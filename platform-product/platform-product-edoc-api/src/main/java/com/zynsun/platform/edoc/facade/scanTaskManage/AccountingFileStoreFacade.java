package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.archive.AccountingFileStoreDTO;

/**
 * @Description：
 * @Author：YongChen
 * @Date：Created in 2018/6/26 13:17
 * @Modified By：
 */
public interface AccountingFileStoreFacade {

    /**
     * 分页查询档案室
     * @param accountingFileStoreDTO
     * @return
     */
    ExecuteResult<PageInfo<AccountingFileStoreDTO>> findFileStoreList(AccountingFileStoreDTO accountingFileStoreDTO);

    /**
     * 新建档案室
     * @param accountingFileStoreDTO
     * @return
     */
    ExecuteResult<String> createFileStore(AccountingFileStoreDTO accountingFileStoreDTO);

    /**
     * 根据id查询档案室
     * @param id
     * @return
     */
    ExecuteResult<AccountingFileStoreDTO> findFileStoreById(Long id);

    /**
     * 编辑档案室信息
     * @param accountingFileStoreDTO
     * @return
     */
    ExecuteResult<String> editFileStore(AccountingFileStoreDTO accountingFileStoreDTO);

    /**
     * 根据id删除档案室信息
     * @param id 档案室id
     * @return
     */
    ExecuteResult<String> deleteFileStoreById(Long id);
}
