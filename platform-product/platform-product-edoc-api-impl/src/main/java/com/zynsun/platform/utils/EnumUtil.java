package com.zynsun.platform.utils;

import com.zynsun.platform.edoc.dto.baseDTO.BaseEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-21 10:26
 **/
public class EnumUtil {

    public static <T extends BaseEnum> Map<String, Object> IntEnumToMap(Class<T> enumType) {
        Map<String, String> data = null;
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> dataMapList = new ArrayList<>();
        for (T enumInt : enumType.getEnumConstants()) {
            data = new HashMap<>();
            data.put("id", String.valueOf(enumInt.getCode()));
            data.put("value", enumInt.getName());
            dataMapList.add(data);
        }
        result.put("combobox", dataMapList);
        return result;
    }

}
