package com.hx.base.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class ImageToBase64Converter {
    
    /**
     * 将图片文件转换为Base64编码字符串
     * @param filePath 图片文件的完整路径
     * @return Base64编码的字符串
     * @throws IOException 如果文件读取失败
     */
    public static String convertImageToBase64(String filePath) throws IOException {
        // 读取图片文件的字节数组
        Path path = Paths.get(filePath);
        byte[] imageBytes = Files.readAllBytes(path);
        
        // 使用Base64编码器进行编码
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void main(String[] args) {
//        try {
////            String imagePath = "C:\\Users\\lamroy\\Desktop\\创维智慧\\300002海王星辰湖滨花园店\\10.png"; // 替换为实际图片路径
//            String imagePath = "C:\\Users\\lamroy\\Desktop\\logo.png"; // 替换为实际图片路径
//            String base64String = convertImageToBase64(imagePath);
//            // 如果需要Data URI格式（可直接在HTML中使用）
//            String mimeType = Files.probeContentType(Paths.get(imagePath));
//            String dataUri = "data:" + mimeType + ";base64," + base64String;
//            System.out.println("Data URI:");
//            System.out.println(dataUri);
//        } catch (IOException e) {
//            System.err.println("Error processing image: " + e.getMessage());
//        }


        try {
            for (int i = 1; i <= 6; i++) {
                String imagePath = "C:\\Users\\lamroy\\Desktop\\创维智慧\\300005海王星辰万科云城店\\"+ i +".png"; // 替换为实际图片路径
                String base64String = convertImageToBase64(imagePath);
                // 如果需要Data URI格式（可直接在HTML中使用）
                String mimeType = Files.probeContentType(Paths.get(imagePath));
                String dataUri = "data:" + mimeType + ";base64," + base64String;
                System.out.println(i + "图片:");
                System.out.println(dataUri);
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error processing image: " + e.getMessage());
        }

    }
}