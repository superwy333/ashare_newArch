package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AccountingFileStore;
import com.zynsun.platform.edoc.dto.archive.AccountingFileStoreDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingFileStoreFacade;
import com.zynsun.platform.edoc.model.AccountingFileStoreModel;
import com.zynsun.platform.edoc.service.AccountingFileStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Description：
 * @Author：YongChen
 * @Date：Created in 2018/6/26 13:17
 * @Modified By：
 */
@Service(value = "accountingFileStoreFacade")
public class AccountingFileStoreFacadeImpl implements AccountingFileStoreFacade {

    private static final Logger FILESTORE_LOGGER = LoggerFactory.getLogger(AccountingFileStoreFacadeImpl.class);

    @Autowired
    private AccountingFileStoreService accountingFileStoreService;

    @Override
    public ExecuteResult<PageInfo<AccountingFileStoreDTO>> findFileStoreList(AccountingFileStoreDTO accountingFileStoreDTO) {
        ExecuteResult<PageInfo<AccountingFileStoreDTO>> result = new ExecuteResult<>();
        try {
            Page<AccountingFileStoreDTO> page = accountingFileStoreService.queryFileStorePage(DozerUtil.map(accountingFileStoreDTO, AccountingFileStoreModel.class));
            PageInfo<AccountingFileStoreDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), AccountingFileStoreDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        } catch (Exception e) {
            result.addErrorMessage("分页查询异常");
            FILESTORE_LOGGER.error("分页查询异常， 原因：{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createFileStore(AccountingFileStoreDTO accountingFileStoreDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(accountingFileStoreDTO, "添加信息不能为空");
            accountingFileStoreService.add(DozerUtil.map(accountingFileStoreDTO, AccountingFileStore.class));
            result.setSuccessMessage("新建档案室成功");
        } catch (Exception e) {
            result.addErrorMessage("新建档案失败");
            FILESTORE_LOGGER.error("新建档案异常，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<AccountingFileStoreDTO> findFileStoreById(Long id) {
        ExecuteResult<AccountingFileStoreDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(id, "id不能为空");
            AccountingFileStore fileStore = accountingFileStoreService.queryById(id);
            if(null == fileStore) {
                result.addErrorMessage("该档案室可能已被其他人删除，请刷新后重试！");
                return result;
            }

            result.setResult(DozerUtil.map(fileStore, AccountingFileStoreDTO.class));
            result.setSuccessMessage("查询档案室成功");
        } catch (Exception e) {
            result.addErrorMessage("查询档案室失败");
            FILESTORE_LOGGER.error("根据id查询档案室失败，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<String> editFileStore(AccountingFileStoreDTO accountingFileStoreDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(accountingFileStoreDTO, "编辑信息不能为空");
            AccountingFileStore fileStore = accountingFileStoreService.queryById(accountingFileStoreDTO.getId());
            if(null == fileStore) {
                result.addErrorMessage("该档案室可能已被其他人删除，请刷新后重试！");
                return result;
            }

            accountingFileStoreService.updateByIdSelective(DozerUtil.map(accountingFileStoreDTO, AccountingFileStore.class));
            result.setSuccessMessage("编辑档案室成功");
        } catch (Exception e) {
            result.addErrorMessage("编辑档案室失败");
            FILESTORE_LOGGER.error("编辑档案室异常，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<String> deleteFileStoreById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            AccountingFileStore fileStore = accountingFileStoreService.queryById(id);
            if(null == fileStore) {
                result.addErrorMessage("该档案室可能已被其他人删除，请刷新后重试！");
                return result;
            }
            accountingFileStoreService.remove(fileStore);
            result.setSuccessMessage("删除档案室成功");
        } catch (Exception e) {
            result.addErrorMessage("删除档案室失败");
            FILESTORE_LOGGER.error("删除档案室异常，原因：{}", e);
        }

        return result;
    }

}
