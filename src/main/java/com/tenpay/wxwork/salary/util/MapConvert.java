package com.tenpay.wxwork.salary.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.cglib.beans.BeanMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapConvert{
    private static final Logger logger = LoggerFactory.getLogger(MapConvert.class);

    /**
     * 将javabean转为map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            Set<Map.Entry<String,Object>> entrySet = beanMap.entrySet();
            for (Map.Entry<String,Object> entry : entrySet){
                String key = entry.getKey();
                Object value = entry.getValue();
                map.put(key,value);
                logger.debug("bean key: {}, value: '{}'", key,(value instanceof byte[]) ? "byte[] ..." : value);
            }
            /*for (Object key : beanMap.keySet()) {
                Object value = beanMap.get(key);
                map.put(key+"", value);
                logger.debug("bean key: {}, value: '{}'", key,
                             (value instanceof byte[]) ? "byte[] ..." : value);
            }*/
        }
        return map;
    }

    /**
     * 将javabean转为map ，对于不想要转换的字段，添加 @JsonIgnore 排除
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMapViaJackson(T bean) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.convertValue(bean, Map.class);
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            for (Map.Entry<String, Object> entry : entrySet){
                String key = entry.getKey();
                Object value = entry.getValue();
                logger.debug("jackson bean key: {}, value: '{}'", key,(value instanceof byte[]) ? "byte[] ..." : value);
            }

            /*for (Object key : map.keySet()) {
                Object value = map.get(key);
                logger.debug("jackson bean key: {}, value: '{}'", key,
                             (value instanceof byte[]) ? "byte[] ..." : value);
            }*/

        }

        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(TreeMap<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将json string 转为map
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (jsonStr != null && jsonStr.length() > 0) {

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                map = objectMapper.readValue(jsonStr, Map.class);
            }
            catch (Exception e) {
                logger.error("jsonToMap Error: {}", e.getMessage());
            }

        }

        return map;
    }

    /**
     * 将json string 转为 string map ，仅支持一维
     */
    public static Map<String, String> jsonToStrMap(String jsonStr) {

        Map<String, String> map = new HashMap<String, String>();
        if (jsonStr != null && jsonStr.length() > 0) {

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                map = objectMapper.readValue(jsonStr, Map.class);
            }
            catch (Exception e) {
                logger.error("jsonToMap Error: {}", e.getMessage());
            }

        }

        return map;
    }
}
