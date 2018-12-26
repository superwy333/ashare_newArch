package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.dto.TreeDTO;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AccountingFileStore;
import com.zynsun.platform.edoc.domain.AccountingLoc;
import com.zynsun.platform.edoc.dto.archive.AccountingLocDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingLocFacade;
import com.zynsun.platform.edoc.model.AccountingFileStoreModel;
import com.zynsun.platform.edoc.model.AccountingLocModel;
import com.zynsun.platform.edoc.service.AccountingFileStoreService;
import com.zynsun.platform.edoc.service.AccountingLocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service(value = "accountingLocFacade")
public class AccountingLocFacadeImpl implements AccountingLocFacade {
    private static final Logger FILESTORE_LOGGER = LoggerFactory.getLogger(AccountingLocFacadeImpl.class);

    @Autowired
    private AccountingLocService accountingLocService;
    @Autowired
    private AccountingFileStoreService accountingFileStoreService;

    @Override
    public ExecuteResult<PageInfo<AccountingLocDTO>> findLocListByPage(AccountingLocDTO accountingLocDTO) {
        ExecuteResult<PageInfo<AccountingLocDTO>> result = new ExecuteResult<>();
        try {
            Page<AccountingLocDTO> page = accountingLocService.queryLocPage(DozerUtil.map(accountingLocDTO, AccountingLocModel.class));
            PageInfo<AccountingLocDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), AccountingLocDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        } catch (Exception e) {
            result.addErrorMessage("分页查询异常");
            FILESTORE_LOGGER.error("分页查询异常， 原因：{}", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createLoc(AccountingLocDTO accountingLocDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(accountingLocDTO, "添加信息不能为空");
            accountingLocService.add(DozerUtil.map(accountingLocDTO, AccountingLoc.class));
            result.setSuccessMessage("新建档案库位成功");
        } catch (Exception e) {
            result.addErrorMessage("新建档案库位失败");
            FILESTORE_LOGGER.error("新建档案库位异常，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<AccountingLocDTO> findLocById(Long id) {
        ExecuteResult<AccountingLocDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(id, "id不能为空");
            AccountingLoc loc = accountingLocService.queryById(id);
            if(null == loc) {
                result.addErrorMessage("该档案库位可能已被其他人删除，请刷新后重试！");
                return result;
            }
            AccountingLocDTO resultLocDTO = DozerUtil.map(loc, AccountingLocDTO.class);
            if (resultLocDTO.getStoreId() != null) {
                AccountingFileStore fileStore = accountingFileStoreService.queryById(resultLocDTO.getStoreId());
                if (fileStore != null) {
                    resultLocDTO.setStoreName(fileStore.getStoreName());
                }
            }
            result.setResult(resultLocDTO);
            result.setSuccessMessage("查询档案库位成功");
        } catch (Exception e) {
            result.addErrorMessage("查询档案库位失败");
            FILESTORE_LOGGER.error("根据id查询档案库位失败，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<String> editLoc(AccountingLocDTO accountingLocDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(accountingLocDTO, "编辑信息不能为空");
            AccountingLoc loc = accountingLocService.queryById(accountingLocDTO.getId());
            if(null == loc) {
                result.addErrorMessage("该档案库位可能已被其他人删除，请刷新后重试！");
                return result;
            }

            accountingLocService.updateByIdSelective(DozerUtil.map(accountingLocDTO, AccountingLoc.class));
            result.setSuccessMessage("编辑档案库位成功");
        } catch (Exception e) {
            result.addErrorMessage("编辑档案库位失败");
            FILESTORE_LOGGER.error("编辑档案库位异常，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<String> deleteLocById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();

        try {
            Assert.notNull(id, "id不能为空");
            AccountingLoc loc = accountingLocService.queryById(id);
            if(null == loc) {
                result.addErrorMessage("该档案库位可能已被其他人删除，请刷新后重试！");
                return result;
            }
            accountingLocService.remove(loc);
            result.setSuccessMessage("删除档案库位成功");
        } catch (Exception e) {
            result.addErrorMessage("删除档案库位失败");
            FILESTORE_LOGGER.error("删除档案库位异常，原因：{}", e);
        }

        return result;
    }

    @Override
    public ExecuteResult<List<TreeDTO>> queryAllStore() {
        ExecuteResult<List<TreeDTO>> result = new ExecuteResult<>();
        try {
            List<TreeDTO> list = new ArrayList<>();
            List<AccountingFileStore> fileStores = accountingFileStoreService.queryAll();
            for (AccountingFileStore fs : fileStores) {
                TreeDTO treeDTO = new TreeDTO();
                treeDTO.setId(Long.valueOf(fs.getId()));
                treeDTO.setText(fs.getStoreName());
                list.add(treeDTO);
            }
            result.setResult(list);
            result.setSuccessMessage("查询信息成功");
            return result;
        } catch (Exception e) {
            result.addErrorMessage("查询信息失败");
            return result;
        }
    }

}
