package cn.imust.ys.springbootshiro.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.*;

/**
 * @author YDeity
 */
public class ImportUtils {

    private ImportUtils() {
    }

    public static List getDownloadTemplate(Map template) {
        List listColumns = new ArrayList();
        Iterator iterator = template.entrySet().iterator();
        Map map = null;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            map = new HashMap();
            map.put("title", entry.getKey());
            listColumns.add(map);
        }
        return listColumns;
    }

    public static String getIndexByTitle(String obj, Map<String, String> map) {
        return map.get(obj);
    }

    public static List getColumnsList(JSONArray jsonArrayColumns, Map<String, String> templateMap) {
        Object[] objects = jsonArrayColumns.toArray();
        List listColumns = new ArrayList();
        HashMap mapColumns = null;
        for (Object obj : objects) {
            mapColumns = new HashMap();
            mapColumns.put("title", (String) obj);
            mapColumns.put("index", ImportUtils.getIndexByTitle((String) obj, templateMap));
            listColumns.add(mapColumns);
        }
        return listColumns;
    }

    public static List getListData(Map<String, Object> paramsMap, List listColumns) {
        JSONArray jsonArrayColumns;
        int count = 1;
        List listData = new ArrayList();
        HashMap mapData = null;
        while ((jsonArrayColumns = (JSONArray) paramsMap.get(count + "")) != null) {
            Object[] objects = jsonArrayColumns.toArray();
            mapData = new HashMap();
            for (int i = 0; i < listColumns.size(); i++) {
                mapData.put(((HashMap) listColumns.get(i)).get("index"), (String) objects[i]);
            }
            listData.add(mapData);
            count++;
        }
        return listData;
    }

    public static Map analytical(String params, Map<String, String> templateMap) {
        Map map = new HashMap();
        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        JSONArray jsonArrayColumns = (JSONArray) paramsMap.get("0");// 0 是标题
        List listColumns = ImportUtils.getColumnsList(jsonArrayColumns, templateMap);
        List listData = ImportUtils.getListData(paramsMap, listColumns);
        map.put("columns", listColumns);
        map.put("listData", listData);
        return map;
    }

}
