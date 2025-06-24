package com.hx.base.utils;

import com.hx.base.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

@Slf4j
public class ProcessUtil {

    public static void exec(List<String> command) {
        Thread inputThread = null;
        Thread errThread = null;
        ByteArrayOutputStream logOut = new ByteArrayOutputStream();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            inputThread = new Thread(() -> {
                try {
                    copy(process.getInputStream(), logOut, new byte[1024]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            errThread = new Thread(() -> {
                try {
                    copy(process.getErrorStream(), logOut, new byte[1024]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();
            errThread.start();

            // 等待命令执行完成
            int exitCode = process.waitFor();

            // log-thread join
            inputThread.join();
            errThread.join();

            if (exitCode == 0) {
                log.info("HTML 转换为 PDF 成功！");
                return;
            }
            String msg = IOUtils.toString(logOut.toByteArray(), "UTF-8");
            throw new ServiceException("HTML 转换为 PDF 失败！错误信息：" + msg);
        } catch (InterruptedException | IOException e) {
            throw new ServiceException("HTML 转换为 PDF 失败！错误信息：{}", e.getMessage());
        } finally {
            if (inputThread != null && inputThread.isAlive()) {
                inputThread.interrupt();
            }
            if (errThread != null && errThread.isAlive()) {
                errThread.interrupt();
            }
        }
    }

    /**
     * 数据流Copy（Input自动关闭，Output不处理）
     *
     * @param inputStream
     * @param outputStream
     * @param buffer
     * @return
     * @throws IOException
     */
    private static long copy(InputStream inputStream, OutputStream outputStream, byte[] buffer) throws IOException {
        try {
            long total = 0;
            for (; ; ) {
                int res = inputStream.read(buffer);
                if (res == -1) {
                    break;
                }
                if (res > 0) {
                    total += res;
                    if (outputStream != null) {
                        outputStream.write(buffer, 0, res);
                    }
                }
            }
            outputStream.flush();

            inputStream.close();
            inputStream = null;
            return total;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
