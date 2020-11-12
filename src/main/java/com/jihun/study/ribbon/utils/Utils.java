package com.jihun.study.ribbon.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Utils {
    public static String parseResponse(InputStream inputStream) {
        BufferedInputStream bis         = new BufferedInputStream(null);
        StringBuilder builder           = new StringBuilder();

        try {
            bis = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[1024];

            while(bis.read(buffer) != -1) {
                String bufferString = new String(buffer, "utf-8");
                builder.append(bufferString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return builder.toString();
    }
}
