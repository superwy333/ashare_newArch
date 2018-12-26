package com.zynsun.platform.edoc.dubboWebApi.impl;

import com.zynsun.openplatform.dto.BaseDTO;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dubboRestApi.web.CommonWebApi;
import com.zynsun.platform.edoc.facade.scanTaskManage.BaseFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocImageFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-15 21:14
 **/
@Path("commonWebApi")
@Service("commonWebApi")
public class CommonWebApiImpl implements CommonWebApi {

    @Autowired private EdocHeaderFacade edocHeaderFacade;
    @Autowired private EdocImageFacade edocImageFacade;
    @Autowired private InvoiceFacade invoiceFacade;

    private BaseFacade getFacade(Integer processType) throws Exception{
        BaseFacade facade = null;
        switch (processType) {
            case 1: facade =  edocHeaderFacade; break;
            case 2: facade =  edocImageFacade; break;
            case 3: facade =  invoiceFacade; break;
            // TODO 根据业务补充
        }
        if (facade==null) throw new Exception("invaild processType");
        return facade;

    }

    /*private ExecuteResult<Object> invokeFacade(Integer processType) throws Exception {
        ExecuteResult<Object> result = null;
        switch (processType) {
            case 1: result =  edocHeaderFacade.a(); break;
            case 2: result =  edocHeaderFacade.b(); break;
            case 3: result =  edocHeaderFacade.c(); break;
            case 4: result =  edocHeaderFacade.d(); break;
            // TODO 根据业务补充
        }
        if (result==null) throw new Exception("invaild processType");
        return result;
    }*/

    private BaseDTO doMap2DTO(Map<String,Object> param, Integer processType) throws Exception {
        BaseDTO dto = null;
        switch (processType) {
            case 1: dto = map2DTO(param, EdocHeaderDTO.class); break;
            // TODO 根据业务补充
        }
        if (dto==null) throw new Exception("invaild processType");
        return dto;
    }

    private BaseDTO map2DTO(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        if (obj instanceof BaseDTO) {
            return (BaseDTO) obj;
        }else {
            throw new Exception("map2DTO error");
        }

    }

    private Integer validate(Object processType) throws Exception {
        if(processType != null && StringUtils.isNotEmpty(processType.toString())){
            return Integer.parseInt(processType.toString());
        }else {
            throw new Exception("invaild processType");
        }
    }


    /**
     * 列表查询公共接口
     * @param map
     * @return
     */
    @POST
    @Path("dataGrid")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    public ExecuteResult<Object> dataGrid(Map<String,Object> map) {
        ExecuteResult<Object> result = new ExecuteResult<>();
        try {
            Integer proecssType = validate(map.get("processType"));
            Map<String,Object> map1 = (Map<String, Object>) map.get("param");
            result = this.getFacade(proecssType).dataGrid(doMap2DTO(map1,proecssType));
        }catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }

    @POST
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public ExecuteResult<Object> save(Map<String,Object> map) {
        ExecuteResult<Object> result = new ExecuteResult<>();
        try {
            Integer proecssType = validate(map.get("processType"));
            Map<String,Object> map1 = (Map<String, Object>) map.get("param");
            result = this.getFacade(proecssType).save(doMap2DTO(map1,proecssType));
        }catch (Exception e) {

        }
        return result;
    }

    @Override
    public ExecuteResult<Object> update(Map<String,Object> map) {
        // TODO 公共更新接口
        return null;
    }

    @Override
    public ExecuteResult<Object> remove(Map<String,Object> map) {
        // TODO 公共删除接口
        return null;
    }

    @Override
    public ExecuteResult<Object> combobox(Map<String, Object> map) {
        // TODO 公共列表页接口
        return null;
    }

    @Override
    public ExecuteResult<Object> doFunc(Map<String, Object> map) {
        // TODO 调用facade定制化方法的公共接口
        return null;
    }
}
