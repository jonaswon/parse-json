package com.jonaswon.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 工具工具类
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class.getName()); // 日志打印类

    /**
     * 创建文件或文件夹
     * @param excelFileName
     * @throws IOException
     */
    public static void createFile(String excelFileName) throws IOException {
        File exportFile = new File(excelFileName);
        int level = 0;
        // 创建的为文件
        if (excelFileName.contains(".")) {
            File pFile = exportFile.getParentFile();
            createParentFolder(pFile, level);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
                logger.info("创建文件【" + exportFile.getPath() + "】成功");
            }
        }
        // 创建的为目录
        else {
            createParentFolder(exportFile, level);
        }
    }

    /**
     * 创建文件夹
     * @param curFile
     * @param level
     * @throws IOException
     */
    private static void createParentFolder(File curFile, int level) throws IOException {
        File pFile = curFile.getParentFile();

        if (!curFile.exists() && !pFile.exists()) {
            createParentFolder(pFile, level+1);
        }
        if (!curFile.exists() && pFile.exists()) {
            ++level;

            curFile.mkdir();

            int operLevel = level;
            int display = level;

            StringBuffer stringBuffer = new StringBuffer();
            String temp = "-";
            while (operLevel>0) {
                stringBuffer.append(temp);
                operLevel--;
            }

            logger.info(display + "【**】" + stringBuffer + "创建文件夹【" + curFile.getPath() + "】成功");
        }
    }
}
