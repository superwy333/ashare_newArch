package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.github.pagehelper.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocCredentialsDTO;
import com.zynsun.platform.edoc.model.EdocCredentialsModelDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:45
 * @Modified By:
 */
public interface EdocCredentialsFacade {


    /**
     * 人人乐凭证列表分页查询
     * @return
     */
    Map<String,Object> edocCredentialsPage(Map<String,Object> map);

    /**
     * 凭证页面下拉框
     * @param map
     * @return
     */
    Map<String,Object> edocCredentialsCombobox(Map<String,Object> map);



    ExecuteResult<PageInfo<EdocCredentialsDTO>> findCredentialsToBeSectionList(EdocCredentialsDTO edocCredentialsDTO);

    ExecuteResult<EdocCredentialsDTO> findCredentialsById(Long id);

    ExecuteResult<String> saveEdocCredentials(EdocCredentialsDTO edocCredentialsDTO);

    ExecuteResult<String> updateEdocCredentials(EdocCredentialsDTO edocCredentialsDTO);

    ExecuteResult<List<EdocCredentialsDTO>> findEdocCredentialsByEdocHeaderId(Long edocHeaderId);

    ExecuteResult<List<EdocCredentialsDTO>> queryVoucher(String credentialsNum);



}
