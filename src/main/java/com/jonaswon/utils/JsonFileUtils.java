package com.jonaswon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFileUtils {

    public static String getContent(String fileName) throws IOException {
        InputStream path = JsonFileUtils.class.getResourceAsStream("/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));

        StringBuffer stringBuffer = new StringBuffer("");
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }

        String result = stringBuffer.toString();
        return result;
    }
}
