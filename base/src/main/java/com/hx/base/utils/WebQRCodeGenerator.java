package com.hx.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class WebQRCodeGenerator {


    // ====== 使用示例 ======
    public static void main(String[] args) {
        // 示例参数
        Map<String, String> params = new HashMap<>();
        params.put("user_id", "u1001");
        params.put("session_token", "s3cr3t_t0k3n");
        params.put("redirect", "/dashboard");
        String base64Logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAYAAADFw8lbAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAOhSURBVHgB7VhdctowEP4kA+NOH5KcoL5ByAmqnKDcAF4yTZ+aniD0BIWnkPah5AbkBBU3oCeIcwM602koia2ubPNjWwiTJsWd4ZvJDFnJq0+r1e5qgR12KDeYSShOpnU4qBum+3ArI9lhY/wFxKnygAcvNxCoMV5WfZN+lldy1wKcr7BDgvG+vKhcoTC5ewGoBhSatOq+dTLjraxunp/FPRRYFyrsi9PpDRE4xxqCNO8bkaQ/vF9LUkOFR1mRgSg8FAfNVe2Y8J3IDop3k7OEoMBm2MsKDETZK2wOj1SJnFSxOh6F8CAr4SgleBGL6hu5dXhZQZ6oYuud/bmh8hcuT7TIrXxusDVE6eZ6KAnipLBAJTXKnX2E1u99Sh8fjSNBMMoLwz50cjBBOZ82Ob000VD7p7LN92XvRR8FQXPlqjGKvU1Y4+vEQ2SYGGkfDcPt++cMqpoKUWmivEREWTroZ259oTz/b5A53bSP6hjKrJ8nBYYBhmpKvJ3qIqSxShds4MxClKl9rGG6cgEVDHMypqgKYub5a5GuObhtcLuw+miZwC23vhwFyQze8j/Zo/dQUjzh0RuqLpavKzeAl1I1+xEXJM4NygS3ejB7kS6Fp4o3z/MKetCnHz4YTVQUthhZTNET+inKwEi/kpHuuSwKjR7pXzxfJtFaWaIPPhQfUOrq4oW78u0uTn7WwWuvKUaebeTTmhwDJYRgIC9txUpUatIr16FkMZnLU9FdnNGu7u6b8a6USDJVYkGyrm5AQA0RsIH8UhvFPQB+XoBwl46xrTcf1Znq/g241q/XWfqWYRSv4wxozpCqL38xlNrN7z6JmiiCSCnv6LS5krCeo4IPutxLGhC6ByBQCOG17Lnz9Pv4W6+tkTQhEBXH4TEJl3N9V17UjqigHi81IAQKIx0xKitmSSIyQDgdwnHIV13Me0WzqoZRutW5XLFDMB0tWFv2qi2yrkRcYMukKOkkbtMnnSOw4Aeii5osrftNTjCmDe3TWuQKjrGgzhy9PkJS4rojTB5okahXZG4ixJdDPz++0++biIBy9uRlrRvrmrZIvke+eEubESQ6tEYNrStEn/RcxwZhDfnZ7cBEdEF4aj6myCKRw9+SJj//ZYUsWZWxDvJJbf1cMU41ryLSYOlQNEfaN+easR6SzueKrDzYpN04I2xDFGV+TQXdlMbiEpuzmc2i0C9O2wPtKRHHT97Wpabs1Y6LfaSD+pZg6grusMP/gD9ghGIK8JLblwAAAABJRU5ErkJggg==";
        String encoded = base64Logo.split(",")[1];
        byte[] logoBytes = Base64.getDecoder().decode(encoded);

        // 生成二维码字符串
        String qrCode = generateWebQRCode(
                "https://yourwebapp.com/auth",
                params,
                253,
                logoBytes
        );
        System.out.println(qrCode);

        // 在Web应用中可直接这样使用：
         String html = "<img src='" + qrCode + "' alt='Auth QR Code'>";
    }

    /**
     * 生成带参数的URL二维码（Base64格式）
     *
     * @param baseUrl   基础URL
     * @param params    URL参数Map
     * @param size      二维码尺寸（正方形）
     * @param logoBytes
     * @return 可直接用于img标签的base64字符串
     */
    public static String generateWebQRCode(String baseUrl, Map<String, String> params, int size, byte[] logoBytes) {
        try {
            // 1. 构建带参数的URL
            String urlWithParams = buildParamUrl(baseUrl, params);

            // 2. 生成二维码矩阵
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 30%容错
            hints.put(EncodeHintType.MARGIN, 0); // 最小白边

            BitMatrix matrix = new MultiFormatWriter().encode(
                    urlWithParams, BarcodeFormat.QR_CODE, size, size, hints
            );

            // 3. 转换为BufferedImage
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(
                    matrix,
                    new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF) // 黑白色
            );

            // 4. 添加Logo
            BufferedImage logoImage = ImageIO.read(new ByteArrayInputStream(logoBytes));
            BufferedImage combined = addLogoToCenter(qrImage, logoImage);

            // 5. 转换为Base64数据URI
            return convertToDataURI(combined);

        } catch (Exception e) {
            throw new RuntimeException("二维码生成失败: " + e.getMessage());
        }
    }


    /**
     * 构建带参数的URL
     */
    private static String buildParamUrl(String baseUrl, Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name())).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name())).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        return urlBuilder.toString();
    }

    /**
     * 在二维码中心添加Logo
     */
    private static BufferedImage addLogoToCenter(BufferedImage qrImage, BufferedImage logoImage) {
        // 计算Logo尺寸（二维码宽度的1/5）
        int logoWidth = logoImage.getWidth();
        int logoHeight = logoImage.getHeight();
//        int logoWidth = Math.min(qrImage.getWidth() / 5, logoImage.getWidth());
//        int logoHeight = Math.min(qrImage.getHeight() / 5, logoImage.getHeight());

        // 创建新图像
        BufferedImage combined = new BufferedImage(qrImage.getWidth(), qrImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = combined.createGraphics();

        // 绘制二维码
        g.drawImage(qrImage, 0, 0, null);

        // 计算Logo位置（居中）
        int x = (qrImage.getWidth() - logoWidth) / 2;
        int y = (qrImage.getHeight() - logoHeight) / 2;

        // 添加白色背景（提高Logo可见性）
        g.setColor(Color.WHITE);
        g.fillRoundRect(x - 4, y - 4, logoWidth + 8, logoHeight + 8, 20, 20);

        // 绘制Logo
        g.drawImage(logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH), x, y, null);

        g.dispose();
        return combined;
    }

    /**
     * 转换为Base64数据URI
     */
    private static String convertToDataURI(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "PNG", baos);
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("图片转换失败: " + e.getMessage());
        }
    }
}