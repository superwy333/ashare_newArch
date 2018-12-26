package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocInvDiff;
import com.zynsun.platform.edoc.model.InvDiffColumnModel;
import com.zynsun.platform.edoc.model.InvModifyModel;

/**
 * 发票修改记录业务接口
 */
public interface EdocInvDiffService extends BaseService<EdocInvDiff> {
    /***
     * 添加发票字段字段修改记录
     * @param model
     * @return
     */
    int createInvChangeHistory(InvModifyModel model);

    /***
     * 查询发票字段修改列表
     * @param invDiffColumnModel
     * @return
     */
    Page<InvDiffColumnModel> selectByPage(InvDiffColumnModel invDiffColumnModel);
}
