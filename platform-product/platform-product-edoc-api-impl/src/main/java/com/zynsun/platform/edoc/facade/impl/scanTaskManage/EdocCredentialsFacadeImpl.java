package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DateUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.dto.EdocCredentialsDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocCredentialsFacade;
import com.zynsun.platform.edoc.model.EdocCredentialsModel;
import com.zynsun.platform.edoc.model.EdocHeaderModel;
import com.zynsun.platform.edoc.service.EdocCredentialsService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.utils.EnumUtil;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-25 下午 2:48
 * @Modified By:
 */
@Path("edocCredentialsFacade")
@Service("edocCredentialsFacade")
public class EdocCredentialsFacadeImpl implements EdocCredentialsFacade {

    private static final Logger EDOCCREDENTIALS_LOGGOR = LoggerFactory.getLogger(AccountingSectionFacadeImpl.class);

    @Autowired
    private EdocCredentialsService edocCredentialsService;
    @Autowired
    private EdocHeaderService edocHeaderService;


    @POST
    @Path("edocCredentialsCombobox")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocCredentialsCombobox(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer processType = (Integer)map.get("processType");
            if (BeanUtil.isEmpty(processType)) {
                executeResult.put("code","001");
                executeResult.put("msg","processType为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            switch (processType) {
                case 1: result = EnumUtil.IntEnumToMap(EdocCredentialsDTO.CredentialsStatus.class); break;
                case 2: result = EnumUtil.IntEnumToMap(EdocCredentialsDTO.CredentialsType.class); break;
            }
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("获取下拉框数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","获取下拉框数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @POST
    @Path("edocCredentialsPage")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> edocCredentialsPage(Map<String,Object> map) {
        Map<String,Object> executeResult = new HashMap<>();
        try {
            Integer inputPage = (Integer) map.get("page");
            Integer rows = (Integer) map.get("limit");
            if (BeanUtil.isEmpty(inputPage)||BeanUtil.isEmpty(rows)) {
                executeResult.put("code","001");
                executeResult.put("msg","分页参数不能为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            EdocCredentialsModel edocCredentialsModel = new EdocCredentialsModel();
            edocCredentialsModel.setPage(inputPage);
            edocCredentialsModel.setPageSize(rows);
            edocCredentialsModel.setAccountinLedger(BeanUtil.isEmpty(map.get("accountingLedger"))?null:(String) map.get("accountingLedger"));
            edocCredentialsModel.setCredentialsType(BeanUtil.isEmpty(map.get("credentialsType"))?null:(String) map.get("credentialsType"));
            edocCredentialsModel.setCredentialsNum(BeanUtil.isEmpty(map.get("credentialsNum"))?null:(String) map.get("credentialsNum"));
            edocCredentialsModel.setCredentialsStatus(BeanUtil.isEmpty(map.get("credentialsStatus"))?null:(String) map.get("credentialsStatus"));

            List<String> createDateList = (List<String>)map.get("createDate");
            if (!BeanUtil.isEmpty(createDateList)) {
                edocCredentialsModel.setCreateDateStart(BeanUtil.isEmpty(createDateList.get(0))?null:createDateList.get(0));
                edocCredentialsModel.setCreateDateEnd(BeanUtil.isEmpty(createDateList.get(1))?null:createDateList.get(1));
            }

            Page<EdocCredentialsModel> page = edocCredentialsService.edocCredentialsPage(edocCredentialsModel);
            if (!BeanUtil.isEmpty(page.getResult())) {
                List<EdocCredentialsModel> edocCredentialsModelList = page.getResult();
                for (EdocCredentialsModel e:edocCredentialsModelList) {
                    if (!BeanUtil.isEmpty(e.getCreateTime())) {
                        e.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateTime()));
                    }
                    if (!BeanUtil.isEmpty(e.getCredentialsStatus())) {
                        EdocCredentialsDTO.CredentialsStatus credentialsStatus = EdocCredentialsDTO.CredentialsStatus.parse(e.getCredentialsStatus());
                        if (!BeanUtil.isEmpty(credentialsStatus)) {
                            e.setCredentialsStatus(credentialsStatus.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(e.getCredentialsType())) {
                        EdocCredentialsDTO.CredentialsType credentialsType = EdocCredentialsDTO.CredentialsType.parse(e.getCredentialsType());
                        if (!BeanUtil.isEmpty(credentialsType)) {
                            e.setCredentialsType(credentialsType.getName());
                        }
                    }
                    // 填充凭证下的所有单据
                    List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.selectEdocHeaderByEdocCredentialsId(e.getId());
                    e.setEdocHeaderModelList(edocHeaderModelList);
                }
            }
            Map<String,Object> result = new HashMap<>();
            result.put("dataGrid",page.getResult());
            edocCredentialsModel.setPage(null);
            edocCredentialsModel.setPageSize(null);
            List<EdocCredentialsModel> edocCredentialsModelList = edocCredentialsService.edocCredentialsList(edocCredentialsModel);
            result.put("totalRows",BeanUtil.isEmpty(edocCredentialsModelList)?0:edocCredentialsModelList.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("凭证列表分页查询失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","凭证列表分页查询失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<PageInfo<EdocCredentialsDTO>> findCredentialsToBeSectionList(EdocCredentialsDTO edocCredentialsDTO) {
        ExecuteResult<PageInfo<EdocCredentialsDTO>> result = new ExecuteResult<>();
        try {
//            if (!BeanUtil.isEmpty(edocCredentialsDTO.getCreateTimeEnd())) {
//                edocCredentialsDTO.setCreateTimeEnd(DateUtil.getNextDay(edocCredentialsDTO.getCreateTimeEnd(), 1));
//            }
            EdocCredentialsModel edocCredentialsModel = DozerUtil.map(edocCredentialsDTO, EdocCredentialsModel.class);
            Page<EdocCredentialsModel> edocCredentialsModelPage= edocCredentialsService.findCredentialsToBeSectionList(edocCredentialsModel);

            //补充公司代码和名称
            //当credentialsType为2的时候
            if (edocCredentialsModelPage!=null && !BeanUtil.isEmpty(edocCredentialsModelPage.getResult())){
                for (EdocCredentialsModel temp : edocCredentialsModelPage.getResult()) {
                    if (Constant.CredentialsType.PAY_CRED.equals(temp.getCredentialsType())){
                        EdocHeader edocHeader = new EdocHeader();
                        edocHeader.setDeleted(Constant.DeleteFlag.DEL_NO);
                        edocHeader.setEdocNo(temp.getEdocVoucherNo());
                        edocHeader = edocHeaderService.queryOne(edocHeader);
                        if (edocHeader != null){
                            temp.setCompanyCode(edocHeader.getCompanyCode());
                            temp.setCompanyName(edocHeader.getCompanyName());
                        }
                    }
                }
            }

            PageInfo<EdocCredentialsDTO> pageInfo = DozerUtil.mapPage(edocCredentialsModelPage.toPageInfo(),EdocCredentialsDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("查询失败{}", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocCredentialsDTO> findCredentialsById(Long id) {
        ExecuteResult<EdocCredentialsDTO> result = new ExecuteResult<>();
        try {
            EdocCredentials edocCredentials = edocCredentialsService.queryById(id);
            result.setResult(DozerUtil.map(edocCredentials,EdocCredentialsDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("查询失败{}", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> saveEdocCredentials(EdocCredentialsDTO edocCredentialsDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocCredentials edocCredentials = DozerUtil.map(edocCredentialsDTO,EdocCredentials.class);
            edocCredentialsService.add(edocCredentials);
            result.setSuccessMessage("凭证保存成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("凭证保存失败{}", e);
            result.addErrorMessage("凭证保存失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateEdocCredentials(EdocCredentialsDTO edocCredentialsDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            edocCredentialsService.updateByIdSelective(DozerUtil.map(edocCredentialsDTO,EdocCredentials.class));
            result.setSuccessMessage("凭证更新成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("凭证更新失败{}", e);
            result.addErrorMessage("凭证更新失败");

        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocCredentialsDTO>> findEdocCredentialsByEdocHeaderId(Long edocHeaderId) {
        ExecuteResult<List<EdocCredentialsDTO>> result = new ExecuteResult<>();
        try {
            EdocCredentials edocCredentials = new EdocCredentials();
            edocCredentials.setEdocHeaderId(edocHeaderId);
            List<EdocCredentials> edocCredentialsList = edocCredentialsService.query(edocCredentials);
            result.setResult(DozerUtil.mapList(edocCredentialsList,EdocCredentialsDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("查询失败{}", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocCredentialsDTO>> queryVoucher(String credentialsNum) {
        ExecuteResult<List<EdocCredentialsDTO>> result = new ExecuteResult<>();
        try {
            List<EdocCredentials> edocCredentialsList = edocCredentialsService.queryVoucher(credentialsNum);
            result.setResult(DozerUtil.mapList(edocCredentialsList,EdocCredentialsDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            EDOCCREDENTIALS_LOGGOR.error("查询失败{}", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }
}
