package com.zynsun.platform.edoc.facade.impl.dynamicFormManage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.facade.bizManage.BizTypeFacade;
import com.zynsun.platform.edoc.facade.dynamicFormManage.FormAreaFacade;
import com.zynsun.platform.edoc.mapper.BizAreaFieldMapper;
import com.zynsun.platform.edoc.vo.FormAreaVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-22 09:27
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class FormAreaFacadeImplTest {

    @Autowired
    FormAreaFacade formAreaFacade;
    @Autowired
    BizTypeFacade bizTypeFacade;
    @Autowired
    private BizAreaFieldMapper bizAreaFieldMapper;

    @Test
    public void findFormAreas() throws Exception {
       //ExecuteResult<List<FormAreaVO>> formAreas = formAreaFacade.findFormAreas();
        //System.out.println( JSON.toJSONString(formAreas.getResult()));

    }

    @Test
    public void test1(){
        ExecuteResult<String> stringExecuteResult = bizTypeFacade.saveForm("[\n" +
                "    {\n" +
                "        \"formAreaId\":\"1\",\n" +
                "        \"formFieldId\":\"1\",\n" +
                "        \"bizId\":1\n" +
                "    },\n" +
                "    {\n" +
                "        \"formAreaId\":\"1\",\n" +
                "        \"formFieldId\":\"2\",\n" +
                "        \"bizId\":1\n" +
                "    },\n" +
                "]\n");
        System.out.println(stringExecuteResult.getResult());
    }

    @Test
    public void tets2(){
        Map<String, Object> fileds = formAreaFacade.findFileds(25L);
    }

    @Test
    public void tets3(){



    }



}