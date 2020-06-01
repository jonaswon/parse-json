package com.jonaswon.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 比较工具类
 */
public class CompareUtils {

    /**
     * 排序
     * @param rows          数据
     * @param compareMap    比较字段
     */
    public static void sort(List<Map<String, Object>> rows, Map<String, String> compareMap) {
        if (null != compareMap && 1 <= compareMap.size()) {
            int compareLen = compareMap.size();

            // 排序
            Collections.sort(rows, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    for (Map.Entry<String, String> entry : compareMap.entrySet()) {
                        String curCompareKey = entry.getKey();
                        String curCompareVal = entry.getValue();

                        Object o1Location = o1.get(curCompareKey);
                        Object o2Location = o2.get(curCompareKey);

                        boolean strFlag = (o1Location instanceof String);
                        String o1LocationStr = "";
                        String o2LocationStr = "";
                        if (strFlag) {
                            o1LocationStr = (String)o1Location;
                            o2LocationStr = (String)o2Location;
                        } else {
                            o1LocationStr = o1Location + "";
                            o2LocationStr = o2Location + "";
                        }
                        if (null != curCompareVal && strFlag) {
                            o1LocationStr = o1LocationStr.substring(0, o1LocationStr.indexOf(curCompareVal)+1);
                            o2LocationStr = o2LocationStr.substring(0, o2LocationStr.indexOf(curCompareVal)+1);
                        }

                        int diff = o1LocationStr.compareTo(o2LocationStr);
                        if (diff > 0) {
                            return 1;
                        } else if (diff < 0) {
                            return -1;
                        }
                    }
                    return 0;
                }
            });
        }
    }
}
