package com.jonaswon.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jonaswon.constants.ParseConstants;
import com.jonaswon.enums.ERequestUrlEnum;
import com.jonaswon.enums.EUseTypeEnum;
import com.jonaswon.utils.CompareUtils;
import com.jonaswon.utils.JsonContentUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 楼盘销售service
 */
public class SaleService {

    private static Logger logger = Logger.getLogger(SaleService.class.getName()); // 日志打印类

    /**
     * 解析数据
     * @param jsonFileName  json文件名称
     * @param excelFolder   excel保存的文件夹路径
     * @throws IOException
     */
    public static void parse(String jsonFileName, String excelFolder) throws IOException {

        // 从文件中读取json数据
        //String content = JsonFileUtils.getContent(jsonFileName);

        // 生成xlsx的Excel
        Workbook workbook = new SXSSFWorkbook();

        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 1、地区列表
        Map<String, String> areaListCompareMap = new LinkedHashMap<>();
        // 查询字段修改，无则使用默认的
        Map<String, String> areaListDefineMapVal = null;
        // 请求url获取数据，并写入到工作簿对象内
        List<Map<String, Object>> areaList = getData(workbook, ERequestUrlEnum.AREA_LIST,
                areaListCompareMap, areaListDefineMapVal, "地区列表");

        // 保存到excel文件中并存储到硬盘中
        ExcelWriter.flushToExcel(workbook, excelFolder + "\\地区列表.xlsx");

        // 2、详情
        if (null != areaList && !areaList.isEmpty()) {
            String district = null;
            int i = 0;
            // 工作簿名称
            String sheetName = "";
            // 列表排序规则字段
            Map<String, String> detailCompareMap = null;
            // 查询字段修改
            Map<String, String> detailDefineMapVal = null;

            Workbook detailWorkbook = null;
            for (Map<String, Object> areaInfo : areaList) {
                detailCompareMap = new LinkedHashMap<>();
                detailCompareMap.put(ParseConstants.LOCATION, ParseConstants.LOCATION_CONDITION);
                detailCompareMap.put(ParseConstants.REGIST_TIME, null);

                detailDefineMapVal = new HashMap<>();
                district = (String)areaInfo.get(ParseConstants.DISTRICT);
                detailDefineMapVal.put(ParseConstants.DISTRICT, district);

                // 工作簿、excel文件名称
                sheetName = (++i) + "、" + district;

                // TODO
                if (i <= 10) {
                    continue;
                }

                // 每查一个地区时，都生成一个excel文件
                detailWorkbook = new SXSSFWorkbook();

                EUseTypeEnum[] useTypeEnumArr = EUseTypeEnum.values();

                for (EUseTypeEnum curUseType : useTypeEnumArr) {

                    detailDefineMapVal.put(ParseConstants.USE_TYPE, curUseType.getVal());

                    // 请求url获取数据，并写入到工作簿对象内
                    getData(detailWorkbook, ERequestUrlEnum.DETAIL, detailCompareMap, detailDefineMapVal, sheetName+"-" + curUseType.getName());
                }

                logger.info("写入【" + sheetName + "】楼盘销售情况到excel中");

                // 保存到excel文件中并存储到硬盘中
                ExcelWriter.flushToExcel(detailWorkbook, excelFolder + "\\" + sheetName + ".xlsx");
            }
        }
    }

    /**
     * 请求url获取数据，并写入到工作簿对象内
     * @param workbook          工作簿对象
     * @param requestUrlEnum    请求地址
     * @param compareMap        列表排序规则字段
     * @param defineMapVal      查询字段修改
     * @param sheetName         excel文件、工作簿的名称
     * @return
     * @throws IOException
     */
    private static List<Map<String, Object>> getData(Workbook workbook, ERequestUrlEnum requestUrlEnum,
                                                     Map<String, String> compareMap,
                                                     Map<String, String> defineMapVal,
                                                     String sheetName) throws IOException {
        // 通过okhttp请求晋江住建局接口地址，获取返回的内容
        String str = JsonContentUtils.getContentByOkHttp(requestUrlEnum, defineMapVal);
        // 对返回的内容进行解析
        JSONObject json = JSONObject.parseObject(str);
        String arr = json.get(ParseConstants.JSON_KEY).toString();

        List rows = JSONArray.parseArray(arr, Map.class);
        // 进行排序
        CompareUtils.sort(rows, compareMap);

        // 写入数据到工作簿对象内
        ExcelWriter.exportData(workbook, rows, sheetName);

        return rows;
    }
}
