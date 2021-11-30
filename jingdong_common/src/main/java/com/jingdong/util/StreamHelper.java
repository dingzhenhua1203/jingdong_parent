package com.jingdong.util;


import java.io.*;

public class StreamHelper {
    /**
     * 输入流转字节流
     * */
    public static byte[] InputStreamToByte(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int ch = 0;
        while (-1 != (ch = input.read(buffer))) {
            output.write(buffer, 0, ch);
        }
        byte data[] = output.toByteArray();
        // output.flush();
        output.close();
        return data;
    }

    public static InputStream ByteToInputStream(byte[] buffer) throws IOException {
        InputStream stream = new ByteArrayInputStream(buffer);
        return stream;
    }

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        // 获取文件大小
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large " + file.getName());
        }

        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int) length];
        // 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }
}
