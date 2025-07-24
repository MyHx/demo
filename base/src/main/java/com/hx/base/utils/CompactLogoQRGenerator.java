package com.hx.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class CompactLogoQRGenerator {

    // ====== 关键控制参数 ======
    private static final int LOGO_BACKGROUND_PADDING = 8;  // 留白大小（原10 → 缩小为5）
    private static final int LOGO_DISPLAY_RATIO = 6;      // Logo尺寸比例（原6 → 缩小为5，相当于增大Logo）
    private static final int LOGO_CORNER_RADIUS = 8;      // Logo背景圆角（原15 → 缩小为8）
    private static final Color LOGO_BACKGROUND_COLOR = Color.WHITE;
    private static final Color QR_CODE_COLOR = new Color(30, 30, 30);
    private static final Color QR_BACKGROUND_COLOR = Color.WHITE;

    /**
     * 生成带紧凑Logo的二维码
     */
    public static String generateQRWithCompactLogo(String baseUrl, Map<String, String> params, int size, byte[] logoBytes) {
        try {
            // 1. 构建带参数的URL
            String url = buildParamUrl(baseUrl, params);

            // 2. 生成二维码矩阵
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, size, size, hints);

            // 3. 转换为BufferedImage
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, new MatrixToImageConfig(QR_CODE_COLOR.getRGB(), QR_BACKGROUND_COLOR.getRGB()));

            // 4. 添加紧凑Logo
            BufferedImage logoImage = ImageIO.read(new ByteArrayInputStream(logoBytes));
            BufferedImage combined = addCompactLogo(qrImage, logoImage);

            // 5. 转换为Base64数据URI
            return convertToDataURI(combined);

        } catch (Exception e) {
            throw new RuntimeException("二维码生成失败: " + e.getMessage());
        }
    }

    /**
     * 添加紧凑Logo（缩小留白区域）
     */
    private static BufferedImage addCompactLogo(BufferedImage qrImage, BufferedImage logoImage) {
        int qrSize = qrImage.getWidth();

        // 计算Logo尺寸（比例增大，留白相对缩小）
        int logoDisplaySize = qrSize / LOGO_DISPLAY_RATIO;  // 使用新的比例

        // 创建新图像
        BufferedImage combined = new BufferedImage(qrSize, qrSize, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = combined.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(qrImage, 0, 0, null);

        // 计算Logo位置（居中）
        int x = (qrSize - logoDisplaySize) / 2;
        int y = (qrSize - logoDisplaySize) / 2;

        // 计算留白背景尺寸（使用缩小后的留白值）
        int bgSize = logoDisplaySize + 2 * LOGO_BACKGROUND_PADDING;  // 两侧各5px留白
        int bgX = (qrSize - bgSize) / 2;
        int bgY = (qrSize - bgSize) / 2;

        // 绘制留白背景（圆角更小）
        g.setColor(LOGO_BACKGROUND_COLOR);
        g.fill(new RoundRectangle2D.Float(bgX, bgY, bgSize, bgSize, LOGO_CORNER_RADIUS, LOGO_CORNER_RADIUS));

        // 添加轻微阴影效果（更紧凑）
        g.setColor(new Color(0, 0, 0, 15)); // 更淡的阴影
        g.fillRoundRect(bgX + 1, bgY + 1, bgSize, bgSize, LOGO_CORNER_RADIUS, LOGO_CORNER_RADIUS);

        // 再次绘制背景
        g.setColor(LOGO_BACKGROUND_COLOR);
        g.fill(new RoundRectangle2D.Float(bgX, bgY, bgSize, bgSize, LOGO_CORNER_RADIUS, LOGO_CORNER_RADIUS));

        // 绘制Logo（尺寸增大，留白相对减小）
        g.drawImage(logoImage.getScaledInstance(logoDisplaySize, logoDisplaySize, Image.SCALE_SMOOTH), x, y, null);

        g.dispose();
        return combined;
    }

    // ====== 辅助方法保持不变 ======
    private static String buildParamUrl(String baseUrl, Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(URLEncoder.encode(entry.getKey(), String.valueOf(StandardCharsets.UTF_8)))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), String.valueOf(StandardCharsets.UTF_8)))
                        .append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        return urlBuilder.toString();
    }

    private static String convertToDataURI(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "PNG", baos);
            return "data:image/png;base64," +
                    Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("图片转换失败: " + e.getMessage());
        }
    }

    // ====== 使用示例 ======
    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("product", "P2025");
        params.put("promo", "SUMMER25");

        String base64Logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAYAAADFw8lbAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAOhSURBVHgB7VhdctowEP4kA+NOH5KcoL5ByAmqnKDcAF4yTZ+aniD0BIWnkPah5AbkBBU3oCeIcwM602koia2ubPNjWwiTJsWd4ZvJDFnJq0+r1e5qgR12KDeYSShOpnU4qBum+3ArI9lhY/wFxKnygAcvNxCoMV5WfZN+lldy1wKcr7BDgvG+vKhcoTC5ewGoBhSatOq+dTLjraxunp/FPRRYFyrsi9PpDRE4xxqCNO8bkaQ/vF9LUkOFR1mRgSg8FAfNVe2Y8J3IDop3k7OEoMBm2MsKDETZK2wOj1SJnFSxOh6F8CAr4SgleBGL6hu5dXhZQZ6oYuud/bmh8hcuT7TIrXxusDVE6eZ6KAnipLBAJTXKnX2E1u99Sh8fjSNBMMoLwz50cjBBOZ82Ob000VD7p7LN92XvRR8FQXPlqjGKvU1Y4+vEQ2SYGGkfDcPt++cMqpoKUWmivEREWTroZ259oTz/b5A53bSP6hjKrJ8nBYYBhmpKvJ3qIqSxShds4MxClKl9rGG6cgEVDHMypqgKYub5a5GuObhtcLuw+miZwC23vhwFyQze8j/Zo/dQUjzh0RuqLpavKzeAl1I1+xEXJM4NygS3ejB7kS6Fp4o3z/MKetCnHz4YTVQUthhZTNET+inKwEi/kpHuuSwKjR7pXzxfJtFaWaIPPhQfUOrq4oW78u0uTn7WwWuvKUaebeTTmhwDJYRgIC9txUpUatIr16FkMZnLU9FdnNGu7u6b8a6USDJVYkGyrm5AQA0RsIH8UhvFPQB+XoBwl46xrTcf1Znq/g241q/XWfqWYRSv4wxozpCqL38xlNrN7z6JmiiCSCnv6LS5krCeo4IPutxLGhC6ByBQCOG17Lnz9Pv4W6+tkTQhEBXH4TEJl3N9V17UjqigHi81IAQKIx0xKitmSSIyQDgdwnHIV13Me0WzqoZRutW5XLFDMB0tWFv2qi2yrkRcYMukKOkkbtMnnSOw4Aeii5osrftNTjCmDe3TWuQKjrGgzhy9PkJS4rojTB5okahXZG4ixJdDPz++0++biIBy9uRlrRvrmrZIvke+eEubESQ6tEYNrStEn/RcxwZhDfnZ7cBEdEF4aj6myCKRw9+SJj//ZYUsWZWxDvJJbf1cMU41ryLSYOlQNEfaN+easR6SzueKrDzYpN04I2xDFGV+TQXdlMbiEpuzmc2i0C9O2wPtKRHHT97Wpabs1Y6LfaSD+pZg6grusMP/gD9ghGIK8JLblwAAAABJRU5ErkJggg==";
        String encoded = base64Logo.split(",")[1];
        byte[] logoBytes = Base64.getDecoder().decode(encoded);

        String qrCode = generateQRWithCompactLogo(
                "https://example.com/offer",
                params,
                253,
                logoBytes
        );

        System.out.println("<img src=\"" + qrCode + "\">");
    }
}