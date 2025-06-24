package com.hx.base.controller;

import com.hx.base.dao.entity.Consultant;
import com.hx.base.dao.entity.Dic;
import com.hx.base.dao.entity.OrgConfigVO;
import com.hx.base.dao.entity.PdfSettingVO;
import com.hx.base.service.ConfigService;
import com.hx.base.service.ConsultantService;
import com.hx.base.service.DicService;
import com.hx.base.service.PdfSettingService;
import com.hx.base.utils.MarkdownToPdfConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/ai/deep")
public class TestContentConversionDeep {

    @Resource
    private PdfSettingService pdfSettingService;
    @Resource
    private MarkdownToPdfConverter markdownToPdfConverter;
    @Resource
    private ConfigService configService;
    @Resource
    private ConsultantService consultantService;
    @Resource
    private DicService dicService;
//    @Resource
//    private PromptService promptService;

    @PostMapping("/test")
    public Boolean test() throws IOException {
        String orgCode = "300002";
        String data = "\n### 一、饮食禁忌\n" +
                "#### 1、服用仁和的复方氨酚烷胺胶囊期间能喝酒吗？\n" +
                "不能喝酒，酒精会增加肝损伤风险。\n" +
                "#### 2、吃仁和复方氨酚烷胺胶囊时不能吃哪些辛辣食物？\n" +
                "避免所有辛辣食物，如辣椒、胡椒、生姜和大蒜。\n" +
                "#### 3、服用此药期间，油腻食物是不是不能吃？\n" +
                "是的，避免油腻食物，如油炸食品和肥肉，以免影响药物吸收。\n" +
                "#### 4、吃仁和复方氨酚烷胺胶囊时对生冷食物有禁忌吗？\n" +
                "是的，避免生冷食物，如冰淇淋和生鱼片，以防胃肠道不适。\n" +
                "### 二、深度总结说明\n" +
                "1. 在服用复方氨酚烷胺胶囊期间，应严格避免饮酒、辛辣、油腻和生冷食物，以降低肝损伤、胃肠道刺激等不良反应风险，并确保药物疗效。建议保持清淡饮食，多饮水，如有异常症状及时就医。\n" +
                "\n" +
                "\n" +
                "**本报告为AI助医机输出，仅作为参考。**\n" +
                "**AI助医机可专业解读医院的病历、检查单和处方等报告，洞察疾病风险点。它覆盖上万种疾病，是您身边的健康管理专家。欢迎您带上报告来店体验！具体请与健康顾问联系！**";
        OrgConfigVO orgConfigVO = configService.getOrgConfigByOrgCode(orgCode);
        PdfSettingVO pdfSetting = pdfSettingService.getByOrgCodeAndModule(orgCode, 8, orgConfigVO.getShowName(), orgConfigVO.getOrgImage());

        String uid = "300002|SKYA2TZH2025032500016|11|v2|552|DEEP|132";
        String [] uidArr = uid.split("\\|DEEP");
        if (uidArr.length  == 2) {
            String content = "### 一、服药计划表\n" +
                    "由于仅给出了“复方氨酚烷胺胶囊”这一种药品，且未明确具体的病情和医嘱信息，一般情况下，复方氨酚烷胺胶囊的服用方法为口服，成人一次 1 粒，一日 2 次。以下是一个示例服药计划表：\n" +
                    "|时间|药品名称|剂量|\n" +
                    "| ---- | ---- | ---- |\n" +
                    "|早上 8:00|复方氨酚烷胺胶囊|1 粒|\n" +
                    "|晚上 20:00|复方氨酚烷胺胶囊|1 粒|\n" +
                    "\n" +
                    "### 二、关键风险提示\n" +
                    "1. 对复方氨酚烷胺胶囊中任何成分过敏者禁用。\n" +
                    "2. 严重肝肾功能不全者禁用。\n" +
                    "3. 服用本品期间不得饮酒或含有酒精的饮料。\n" +
                    "4. 不能同时服用与本品成份相似的其他抗感冒药。\n" +
                    "5. 前列腺肥大、青光眼等患者以及老年人应在医师指导下使用。\n" +
                    "6. 孕妇及哺乳期妇女慎用。\n" +
                    "7. 肝、肾功能不全者慎用。\n" +
                    "8. 用药 3 - 7 天，症状未缓解，请咨询医师或药师。\n" +
                    "\n" +
                    "### 三、辅助建议\n" +
                    "- **搭配维生素 C 片**\n" +
                    "    - **搭配的目的**：增强身体免疫力，促进感冒的恢复。\n" +
                    "    - **搭配的功效**：维生素 C 可以增强白细胞的活性，提高身体的抵抗力，有助于减轻感冒症状，缩短感冒病程。\n" +
                    "- **搭配止咳糖浆（如果有咳嗽症状）**\n" +
                    "    - **搭配的目的**：缓解感冒引起的咳嗽症状。\n" +
                    "    - **搭配的功效**：复方氨酚烷胺胶囊主要缓解感冒的发热、头痛、鼻塞等症状，止咳糖浆可以针对性地减轻咳嗽，两者搭配能更全面地缓解感冒不适。\n" +
                    "\n" +
                    "### 四、药品解读\n" +
                    "- **药品名称**：复方氨酚烷胺胶囊\n" +
                    "- **药品类型**：西药\n" +
                    "- **类型**：复方制剂的抗感冒药。\n" +
                    "- **用途**：适用于缓解普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。\n" +
                    "- **用法**：口服，成人一次 1 粒，一日 2 次。\n" +
                    "- **副作用**：有时有轻度头晕、乏力、恶心、上腹不适、口干、食欲缺乏和皮疹等，可自行恢复。\n" +
                    "\n" +
                    "### 五、同类药品建议\n" +
                    "- **复方氨酚那敏颗粒**\n" +
                    "    - **功效对比**：与复方氨酚烷胺胶囊功效相似，都能缓解感冒引起的发热、头痛、鼻塞、流涕等症状。但复方氨酚烷胺胶囊中含有金刚烷胺，对甲型流感病毒有一定的抑制作用，在抗病毒方面可能稍强于复方氨酚那敏颗粒。\n" +
                    "    - **性价比分析**：复方氨酚那敏颗粒价格相对较为便宜，对于症状较轻的普通感冒，选择复方氨酚那敏颗粒可以在满足治疗需求的同时节省费用；而复方氨酚烷胺胶囊价格可能稍高一些，但对于流行性感冒或症状较重的感冒，其抗病毒和缓解症状的效果可能更好。\n" +
                    "- **氨咖黄敏胶囊**\n" +
                    "    - **功效对比**：同样是缓解感冒症状的常用药物，氨咖黄敏胶囊和复方氨酚烷胺胶囊都能减轻发热、头痛、鼻塞等不适。不过氨咖黄敏胶囊不含金刚烷胺，在抗病毒方面相对较弱。\n" +
                    "    - **性价比分析**：氨咖黄敏胶囊价格较为亲民，对于一般的感冒症状，其治疗效果也能满足需求，性价比相对较高；复方氨酚烷胺胶囊在应对流感等情况时优势更明显，但价格也会稍贵。\n" +
                    "\n" +
                    "### 六、简要总结说明\n" +
                    "本次药品清单中仅包含复方氨酚烷胺胶囊这一种药品，它是一种常用的抗感冒药，能有效缓解普通感冒和流行性感冒的多种症状。在服用时需要注意相关的禁忌和风险提示。为了更好地促进感冒恢复，可以搭配一些辅助药物，如维生素 C 片、止咳糖浆等。同时，市场上还有一些同类药品可供选择，如复方氨酚那敏颗粒、氨咖黄敏胶囊等，它们在功效和性价比上各有特点，患者可以根据自身的病情和经济情况进行合理选择。  \n" +
                    "\n" +
                    "**本报告为AI助医机输出，仅供参考。**\n" +
                    "**AI助医机可专业解读医院的病历、检查单和处方等报告，解惑治疗疑问，洞察疾病风险。它覆盖了上万种疾病，是您身边的健康管理专家。欢迎您带上医院的报告来店体验！具体请与健康顾问联系！**\n";
            String qcCodeData = appendQCCode(1, content);
            data = appendImage(false, pdfSetting) + qcCodeData + appendImage(true, pdfSetting) + data;
        }
        byte[] content = markdownToPdfConverter.convertMarkdownToPdf(data, "",pdfSetting.getCss(), pdfSetting.getHeaderHtml(), pdfSetting.getFooterHtml(), pdfSetting.getMarginTop(), pdfSetting.getMarginBottom(), pdfSetting.getMarginLeft(), pdfSetting.getMarginRight());
        writeBytesToFile(content, "健康报告deep.pdf");
        return true;
    }

    /**
     * 追加顾问二维码
     *
     * @param consultantId 顾问id
     * @param data 内容
     * @return
     */
    private String appendQCCode(Integer consultantId, String data) {
        Consultant consultant = consultantService.getConsultantInfoById(consultantId);
        Dic dic = dicService.findOneByDicCode("HWXC_PHARMACY");
        if (null != consultant && null != dic) {
            data = data + "<br></br>" + consultantQCCodeImage(consultant, dic);
        }
        return data;
    }

    /**
     * 追加图片
     *
     * @param consultant 顾问信息
     * @param dic
     * @return
     */
    private String consultantQCCodeImage(Consultant consultant, Dic dic) {
        return "<div class=\"qr-code\"><div>"+"<img src=\"" + consultant.getQrCode() +"\">" +
                "<div>"+ "您的健康顾问-" + consultant.getName() + "</div>" +
                "</div>" +
                "<div>" +
                "<img src=\"" + dic.getDicValue() +"\">" +
                "<div>"+ dic.getShowName() + "</div>" +
                "</div>" +
                "</div>";
    }

    public static void writeBytesToFile(byte[] content, String fileName) throws IOException {
        // 获取用户桌面路径
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");

        // 构造完整文件路径
        Path path = desktopPath.resolve(fileName);

        // 打印调试信息
        System.out.println("Attempting to write to: " + path.toAbsolutePath());

        // 确保父目录存在（桌面目录一般已存在）
        Files.createDirectories(desktopPath);

        // 写入文件（覆盖已有内容）
        try {
            Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (AccessDeniedException e) {
            throw new IOException("无法写入文件，权限不足: " + path, e);
        } catch (IOException e) {
            throw new IOException("写入文件时发生错误: " + path, e);
        }
    }

    /**
     * 追加图片
     * @param pdfSettingVO
     * @return
     */
    private String healthImage(PdfSettingVO pdfSettingVO) {
        return "<div style=\"text-align: left;page-break-before: always;\">\n" +
                "<img src=\"" + pdfSettingVO.getHealthImage() + "\" />\n" +
                "</div>\n\n";
    }

    /**
     * 追加图片
     * @param isDeep
     * @param pdfSettingVO
     * @return
     */
    private String appendImage(boolean isDeep, PdfSettingVO pdfSettingVO) {
        return isDeep ?
                "<div style=\"text-align: left;page-break-before: always;\">\n" +
                        "<img src=\"" + pdfSettingVO.getDeepExplainImage() + "\" />\n" +
                        "</div>\n\n"
                :
                "<div style=\"text-align: left;\">\n" +
                        "<img src=\"" + pdfSettingVO.getExplainImage() + "\" />\n" +
                        "</div>\n\n" ;
    }
}
