package com.hx.base.controller;

import com.hx.base.constant.ConsultantEnum;
import com.hx.base.dao.entity.Consultant;
import com.hx.base.dao.entity.Dic;
import com.hx.base.dao.entity.DicVo;
import com.hx.base.dao.entity.OrgConfigVO;
import com.hx.base.dao.entity.PdfSettingVO;
import com.hx.base.dao.entity.PromptTemplate;
import com.hx.base.service.ConfigService;
import com.hx.base.service.ConsultantService;
import com.hx.base.service.DicService;
import com.hx.base.service.PdfSettingService;
import com.hx.base.service.PromptService;
import com.hx.base.utils.MarkdownToPdfConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ai")
public class TestContentConversion {

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
        String data = "### 一、健康风险评估表\n" +
                "|评估维度|关键指标|实测值|参考范围|风险等级|\n" +
                "| -------- | -------- | ------ | -------- | -------- |\n" +
                "|体重与代谢|体重|80.00kg|49.61 - 66.49kg|偏胖⚠\uFE0F|\n" +
                "||BMI|23.20|18.5 - 25.0|标准✅|\n" +
                "||肥胖等级|偏重|标准：-0.1 ≤ level ≤ 0.1|偏重⚠\uFE0F|\n" +
                "||身体类型|隐形肥胖|/|异常⚠\uFE0F|\n" +
                "|体成分核心|体脂率|29.80%|11% - 22%|高⚠\uFE0F|\n" +
                "||内脏脂肪指数|0.00%|< 11|标准✅|\n" +
                "||皮下脂肪率|12.20%|8% - 20%|标准✅|\n" +
                "|蛋白质营养|蛋白率|11.00%|≥16%|不足⚠\uFE0F|\n" +
                "|水分平衡|水分率|90.75%（72.6/80）|≥65%|优✅|\n" +
                "|骨重与肌量|骨重|29.10kg|≥3.1（体重≥75Kg）|此处数据可能有误，若按比例看骨重占比高✅|\n" +
                "||肌肉率|16.70%|≥40%|不足⚠\uFE0F|\n" +
                "\n" +
                "### 二、推测可能症状\n" +
                "- **疲劳感**：蛋白率和肌肉率不足，身体能量供应和肌肉力量不够，容易感到疲劳。\n" +
                "- **运动耐力差**：肌肉率低，肌肉力量和耐力不足，在进行运动或日常活动时容易感到力不从心。\n" +
                "- **免疫力下降**：蛋白率不足，可能影响免疫系统的正常功能，导致身体抵抗力下降，容易生病。\n" +
                "\n" +
                "### 三、核心健康风险提示\n" +
                "- **肥胖风险**：体脂率过高，处于高风险水平，长期肥胖可能会增加患心血管疾病、糖尿病等慢性疾病的风险。\n" +
                "- **蛋白质缺乏风险**：蛋白率不足，会影响身体的正常代谢、免疫功能和肌肉修复等，导致身体机能下降。\n" +
                "- **肌肉量不足风险**：肌肉率远低于标准，会使身体基础代谢率降低，能量消耗减少，进一步加重肥胖问题，同时也会影响身体的运动能力和稳定性。\n" +
                "\n" +
                "### 四、整体健康状态\n" +
                "整体健康状态存在一定问题。虽然BMI处于标准范围，但体脂率过高，属于隐形肥胖，且蛋白质和肌肉量不足，这可能会对身体的代谢、免疫和运动功能产生负面影响。不过，水分率处于优秀水平，内脏脂肪指数和皮下脂肪率正常，说明身体在某些方面仍有较好的表现。需要通过合理的饮食和运动来改善体脂率、增加蛋白质摄入和提高肌肉量，以提升整体健康水平。\n" +
                "\n" +
                "### 五、饮食调整计划\n" +
                "#### 核心饮食原则\n" +
                "- 控制总热量摄入，保证热量摄入低于消耗，以促进体脂的减少。\n" +
                "- 增加蛋白质摄入，提高蛋白率和肌肉量。\n" +
                "- 均衡饮食，保证各种营养素的摄入，多吃蔬菜、水果和全谷物。\n" +
                "\n" +
                "#### 具体分配与食物选择\n" +
                "|类别|推荐食物|限制食物|\n" +
                "|----|----|----|\n" +
                "|蛋白质|鸡胸肉、鱼虾、豆类、蛋类、牛奶、大豆蛋白、乳清蛋白|加工肉类、高脂肪肉类|\n" +
                "|碳水化合物|全麦面包、糙米、燕麦、红薯|白面包、蛋糕、糖果|\n" +
                "|脂肪|橄榄油、鱼油、坚果|动物油、油炸食品|\n" +
                "|蔬菜|西兰花、菠菜、芹菜、黄瓜等各种绿叶蔬菜|腌制蔬菜|\n" +
                "|水果|苹果、香蕉、橙子、蓝莓等低糖水果|果汁、果脯|\n" +
                "\n" +
                "#### 三餐模板示范\n" +
                "- **早餐**：水煮蛋一个、全麦面包两片、牛奶一杯、蓝莓若干。\n" +
                "- **午餐**：香煎鸡胸肉100g、糙米饭150g、清炒西兰花200g。\n" +
                "- **晚餐**：清蒸鱼100g、红薯150g、凉拌菠菜200g。\n" +
                "\n" +
                "### 六、运动锻炼计划\n" +
                "#### 每周运动方案\n" +
                "|运动类型|频率|时长|推荐项目|关键细节|\n" +
                "|-------|-------|-------|----|----|\n" +
                "|有氧运动|3 - 5次/周|30 - 60分钟|慢跑、游泳、骑自行车|运动强度保持在心率为最大心率的60% - 80%（最大心率=220 - 年龄）。\n" +
                "|力量训练|2 - 3次/周|30 - 60分钟|哑铃训练、俯卧撑、仰卧起坐、深蹲|每个动作进行2 - 3组，每组8 - 12次，逐渐增加重量。\n" +
                "\n" +
                "#### 运动效果追踪指标\n" +
                "|指标|当前值|3个月目标|调整阈值|\n" +
                "|----|----|----|----|\n" +
                "|体脂率|29.80%|25%|±1%|\n" +
                "|肌肉率|16.70%|25%|±1%|\n" +
                "|体重|80.00kg|75kg|±0.5kg|\n" +
                "\n" +
                "### 七、生活习惯调整\n" +
                "- 保证充足睡眠，每天睡眠时间不少于7 - 8小时，有利于身体恢复和激素平衡。\n" +
                "- 减少久坐时间，每隔一段时间起身活动一下，促进血液循环。\n" +
                "- 保持良好的心态，避免长期处于压力和焦虑状态，可通过冥想、瑜伽等方式缓解压力。\n" +
                "\n" +
                "### 八、辅助用药建议\n" +
                "|改善指标|推荐用药|功效解释|\n" +
                "| -------- | -------------- | -------- |\n" +
                "|体脂率、肥胖|B族维生素|参与糖和脂肪代谢，为身体供能，减少体脂。维生素B族能够调节食欲，B6可以影响神经递质如血清素的合成，血清素水平的变化可以调节食欲。适当补充B族可能有助于控制肥胖者过度的食欲，减少食物摄入量，从而对减肥起到辅助作用。|\n" +
                "||铬|铬有助于人们减少对甜食的渴求，帮助降低体脂含量、增加瘦肌肉组织，从而有助于促进新陈代谢，维持理想体重（瘦肌肉组织越多，代谢率越高）。|\n" +
                "|蛋白率|大豆蛋白|用大豆蛋白代替膳食中部分蛋白，不仅降低了胆固醇与饱和脂肪的摄入量，同时也达到了营养摄取的均衡。|\n" +
                "||乳清蛋白|β - 乳球蛋白是乳清蛋白的主要成分，它具备最佳的氨基酸比例，支链氨基酸含量极高，对促进蛋白质合成和减少蛋白质分解起着重要的作用，使瘦体重增加(瘦体重 = 体重 - 脂肪重量），有助于提高机体代谢率，利于减脂和塑型。|\n" +
                "\n" +
                "**AI助医是根据您健康测量的数据给出的参考建议，具体治疗方案需要结合您的个体情况，由临床专业医生或医师做最终判断。**";



        String replace = "<br></br>\n" +
                "<table>\n" +
                "   <thead> \n" +
                "        <tr>\n" +
                "            <th colspan=\"4\" class=\"title-row\" style=\"text-align: center;background-color:#DFE2F0;\">基础信息</th>\n" +
                "        </tr>\n" +
                "   </thead> \n" +
                "        <tr>\n" +
                "            <th style=\"text-align: center;\"><strong>项目</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>结果</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>项目</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>结果</strong></th>\n" +
                "        </tr>\n" +
                "   <tbody> \n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">性别</td>\n" +
                "            <td style=\"text-align: center;\">女</td>\n" +
                "            <td style=\"text-align: center;\">身高</td>\n" +
                "            <td style=\"text-align: center;\">159cm</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">年龄</td>\n" +
                "            <td style=\"text-align: center;\">29岁</td>\n" +
                "            <td style=\"text-align: center;\">基础疾病</td>\n" +
                "            <td style=\"text-align: center;\">糖尿病</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "\n" +
                "   </tbody> \n" +
                "    </table>\n" +
                "<br></br>\n" +
                "<table>\n" +
                "   <thead> \n" +
                "        <tr>\n" +
                "            <th colspan=\"4\" class=\"title-row\" style=\"text-align: center;background-color:#DFE2F0;\">测量数据</th>\n" +
                "        </tr>\n" +
                "   </thead> \n" +
                "        <tr>\n" +
                "            <th style=\"text-align: center;\"><strong>项目</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>结果</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>项目</strong></th>\n" +
                "            <th style=\"text-align: center;\"><strong>结果</strong></th>\n" +
                "        </tr>\n" +
                "   <tbody> \n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">血压</td>\n" +
                "            <td style=\"text-align: center;\">120/82mmHg</td>\n" +
                "            <td style=\"text-align: center;\">脉搏</td>\n" +
                "            <td style=\"text-align: center;\">83次/分</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">内脏脂肪率</td>\n" +
                "            <td style=\"text-align: center;\">7.00%</td>\n" +
                "            <td style=\"text-align: center;\">体重</td>\n" +
                "            <td style=\"text-align: center;\">70.00kg</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">BMI</td>\n" +
                "            <td style=\"text-align: center;\">27.70</td>\n" +
                "            <td style=\"text-align: center;\">体脂率</td>\n" +
                "            <td style=\"text-align: center;\">41.00%</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">蛋白率</td>\n" +
                "            <td style=\"text-align: center;\">9.90%</td>\n" +
                "            <td style=\"text-align: center;\">肌肉率</td>\n" +
                "            <td style=\"text-align: center;\">55.30%</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">皮下脂肪率</td>\n" +
                "            <td style=\"text-align: center;\">37.20%</td>\n" +
                "            <td style=\"text-align: center;\">骨量</td>\n" +
                "            <td style=\"text-align: center;\">2.54kg</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">水分</td>\n" +
                "            <td style=\"text-align: center;\">42.10kg</td>\n" +
                "            <td style=\"text-align: center;\">基础代谢率</td>\n" +
                "            <td style=\"text-align: center;\">1336.00kcal</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">身体年龄</td>\n" +
                "            <td style=\"text-align: center;\">34.00岁</td>\n" +
                "            <td style=\"text-align: center;\">身体阻抗</td>\n" +
                "            <td style=\"text-align: center;\">544.0Ω</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "        <tr>\n" +
                "            <td style=\"text-align: center;\">空腹血糖</td>\n" +
                "            <td style=\"text-align: center;\">4.0mmol/L</td>\n" +
                "            <td style=\"text-align: center;\">非空腹血糖</td>\n" +
                "            <td style=\"text-align: center;\">5.0mmol/L</td>\n" +
                "        </tr>\n" +
                "\n" +
                "\t\n" +
                "\n" +
                "   </tbody> \n" +
                "    </table>";

//        PromptTemplate promptTemplate = promptService.getPromptTemplate(8, "1");
//        if (StringUtils.isNotBlank(promptTemplate.getAdditionalComments())) {
//            String additionalComments = System.lineSeparator() + System.lineSeparator() + promptTemplate.getAdditionalComments();
//            data = data + additionalComments;
//        }

        OrgConfigVO orgConfigVO = configService.getOrgConfigByOrgCode(orgCode);
        PdfSettingVO pdfSetting = pdfSettingService.getByOrgCodeAndModule(orgCode, 8, orgConfigVO.getShowName(), orgConfigVO.getOrgImage());

        data = healthImage(pdfSetting) + "{HEALTHINFO}<br></br>\n\n" + appendImage(false, pdfSetting) + data;

        Consultant consultant = consultantService.getConsultantInfoById(1);
        Dic dic = dicService.findOneByDicCode("HWXC_PHARMACY");
        if (null != consultant && null != dic) {
            data = data + "<br></br>" + consultantQCCodeImage(consultant, dic);
        }
        byte[] content = markdownToPdfConverter.convertMarkdownToPdf(data, replace, pdfSetting.getCss(),
                pdfSetting.getHeaderHtml(), pdfSetting.getFooterHtml(), pdfSetting.getMarginTop(),
                pdfSetting.getMarginBottom(), pdfSetting.getMarginLeft(), pdfSetting.getMarginRight());

        writeBytesToFile(content, "健康报告.pdf");
        return true;
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
