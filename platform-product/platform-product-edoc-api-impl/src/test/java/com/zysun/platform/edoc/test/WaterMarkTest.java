package com.zysun.platform.edoc.test;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.watermark.SysWatermarkDTO;
import com.zynsun.platform.edoc.facade.watermarkManage.SysWatermarkFacade;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/12/29 0029 下午 1:52
 * @Modified By：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/root.xml"})
public class WaterMarkTest {
    @Test
    public void testImgAddWater(){
        System.out.print(1);
//        String origFilePath = "D:\\testMark\\source.jpg";
//        String waterFilePath = "D:\\testMark\\source_water.jpg";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("markText", "内部资料");
//        params.put("fontColor","GRAY");
//        params.put("opacity", "80");
//        params.put("fontSize", "30");
//        params.put("gradient", "210");
//
////        imgAddWater(origFilePath,waterFilePath,params);
    }
}
