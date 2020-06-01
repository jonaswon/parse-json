package com.jonaswon.run;

import com.jonaswon.service.SaleService;

import java.io.IOException;

public class ParseJsonMain {

    public static void main(String[] args) {
        try {
            String jsonFileName = "sales.json";
            String excelFolder = "E:\\test-20200601";
            // 请求晋江住建局，将地区列表、各地区的楼盘销售情况导出到excel中
            SaleService.parsePreSale(excelFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
