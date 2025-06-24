package com.hx.base.utils;

import com.sun.jna.Platform;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;


@Slf4j
@Component
public class MarkdownToPdfConverter {

    public byte[] convertMarkdownToPdf(String content, String replace, String css, String headerHtml, String footerHtml, String marginTop, String marginBottom, String marginLeft, String marginRight) {
        String html = convertMarkdownToHtml(content, css);

        // 获取用户桌面路径
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        // 构造完整文件路径
        Path path = desktopPath.resolve("健康报告预览deep.html");
        // 打印调试信息（可选）
        System.out.println("正在写入 HTML 文件到: " + path.toAbsolutePath());
        // 写入 HTML 文件
        try {
            Files.write(path, html.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("异常");
        }

        if (replace == null) {
            replace = "";
        }
        html = html.replace("{HEALTHINFO}", replace);
        return convertHtmlToPdf(html, headerHtml, footerHtml, marginTop, marginBottom, marginLeft, marginRight);
    }

    /**
     * markdown转html
     *
     * @param markdown
     * @return
     */
    public String convertMarkdownToHtml(String markdown, String css) {
        MutableDataSet options = new MutableDataSet();
        // 启用表格扩展
        options.set(Parser.EXTENSIONS, Collections.singletonList(TablesExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        String nmd = changeMd(markdown);
        return beautifyHtml(renderer.render(parser.parse(nmd)), css);
    }

    /**
     * 有一种表格的情况不会被识别，这里需要补充
     *
     * @param md
     * @return
     */
    private String changeMd(String md) {
        String[] lines = md.split("\\r?\\n");
        StringBuffer nl = new StringBuffer();
        for (int i = 0; i < lines.length; i++) {
            if (isMdFh(lines[i])) {
                nl.append(lines[i]).append("\n");
            } else {
                nl.append(lines[i]).append("\n").append("\n");
            }
        }
        return nl.toString();
    }

    private boolean isMdFh(String md) {
        md = md.trim();
        if (md.isEmpty()) {
            return true;
        }
        if (Pattern.matches("^(#+)[^#].*", md) ||
                Pattern.matches("^(\\d+\\.|[-*+])\\s+.*", md) ||
                Pattern.matches(".*([*_]{1,2}[^\\s*_]+[*_]{1,2}).*", md) ||
                Pattern.matches("^\\|.*\\|$", md)) {
            return true;
        }
        return false;
    }

    private String beautifyHtml(String rawHtml, String css) {
        // 使用 JSoup 处理 HTML 结构
        org.jsoup.nodes.Document doc = Jsoup.parse(rawHtml, "UTF-8");

        doc.head().append("<meta charset=\"UTF-8\"></meta>\n");
        if (StringUtils.isNotBlank(css)) {
            // 添加表格样式和字体设置
            doc.head().append("<style>\n" + css + "</style>\n");
        }

        // 选择所有 h3 标签
        Elements h3Tags = doc.select("h3");

        // 遍历每个 h3 标签并将其内容用 span 包裹
        for (Element h3 : h3Tags) {
            // 获取 h3 标签的原始文本内容
            String h3Text = h3.text();
            // 创建一个 div 元素
            Element div = doc.createElement("div");
            // 设置 div 元素的文本内容为 h3 标签的原始内容
            div.text(h3Text);
            div.addClass("text");
            // 清空 h3 标签的原始内容
            h3.empty();
            // 将div 元素添加到 h3 标签内
            h3.appendChild(div);

            Element beforeDiv = doc.createElement("div");
            // 设置 div 元素的文本内容为 h3 标签的原始内容
            beforeDiv.addClass("before");
            // 将div 元素添加到 h3 标签内
            h3.appendChild(beforeDiv);

            Element afterDiv = doc.createElement("div");
            // 设置 div 元素的文本内容为 h3 标签的原始内容
            afterDiv.addClass("after");
            // 将div 元素添加到 h3 标签内
            h3.appendChild(afterDiv);
        }

        // 优化表格显示
        doc.select("table").attr("rules", "all");
        //html转xhtml过程中，标签没有闭合
        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.html();
    }

    private byte[] convertHtmlToPdf(String html, String headerHtml, String footerHtml, String marginTop, String marginBottom, String marginLeft, String marginRight) {
        File tmpHtmlFile = null;
        File tmpHeaderHtmlFile = null;
        File tmpFooterHtmlFile = null;
        File tmpPdfFile = null;

        try {
            List<String> command = new ArrayList<>();
            // 根据操作系统设置 wkhtmltopdf 命令路径
            if (Platform.isWindows()) {
                command.add("E:\\soft\\wkhtmltopdf\\bin\\wkhtmltopdf.exe");
            } else {
                command.add("wkhtmltopdf");
            }
//            command.add("E:\\code\\demo\\base\\src\\main\\resources\\wkhtmltopdf");
            tmpHtmlFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".html");
            tmpPdfFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".pdf");

            IOUtils.write(html, new FileOutputStream(tmpHtmlFile));

            if (StringUtils.isNotBlank(headerHtml)) {
                tmpHeaderHtmlFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".html");
                IOUtils.write(headerHtml, new FileOutputStream(tmpHeaderHtmlFile));
                command.add("--header-html");
                command.add(tmpHeaderHtmlFile.getAbsolutePath());
            }
            if (StringUtils.isNotBlank(footerHtml)) {
                tmpFooterHtmlFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".html");
                IOUtils.write(footerHtml, new FileOutputStream(tmpFooterHtmlFile));
                command.add("--footer-html");
                command.add(tmpFooterHtmlFile.getAbsolutePath());
            }

            command.add("--margin-top");
            command.add(StringUtils.isNotBlank(marginTop) ? marginTop : "25mm");
            command.add("--margin-bottom");
            command.add(StringUtils.isNotBlank(marginBottom) ? marginBottom : "20mm");
            command.add("--margin-left");
            command.add(StringUtils.isNotBlank(marginLeft) ? marginLeft : "15mm");
            command.add("--margin-right");
            command.add(StringUtils.isNotBlank(marginRight) ? marginRight : "15mm");

            command.add("--print-media-type");

            command.add(tmpHtmlFile.getAbsolutePath());
            command.add(tmpPdfFile.getAbsolutePath());

            ProcessUtil.exec(command);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(new FileInputStream(tmpPdfFile), baos);
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("HTML 转换为 PDF 失败！错误信息：{}", e.getMessage());
            return "HTML 转换为 PDF 失败".getBytes(StandardCharsets.UTF_8);
        } finally {
            if (Objects.nonNull(tmpHtmlFile)) {
                tmpHtmlFile.delete();
            }
            if (Objects.nonNull(tmpPdfFile)) {
                tmpPdfFile.delete();
            }
            if (Objects.nonNull(tmpHeaderHtmlFile)) {
                tmpHeaderHtmlFile.delete();
            }
            if (Objects.nonNull(tmpFooterHtmlFile)) {
                tmpFooterHtmlFile.delete();
            }
        }
    }

}
