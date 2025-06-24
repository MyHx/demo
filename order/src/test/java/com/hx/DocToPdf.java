//// 使用 POI + PDFBox（轻量级依赖）
//
//import org.apache.commons.io.IOUtils;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.net.URL;
//import java.util.Map;
//import java.util.Objects;
//import java.util.UUID;
//
//public class DocToPdfWithLibs {
//
//    public static void main(String[] args) {
//        File tmpPdfFile = null;
//        try {
//            String templateUrl = "";
//
//            ByteArrayInputStream inStream = new ByteArrayInputStream(IOUtils.toByteArray(new URL(templateUrl)));
//            Document document = new Document(inStream);
//            //创建临时文件
//            tmpPdfFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), ".pdf");
//            // 保存pdf
//            document.save(tmpPdfFile.getAbsolutePath(), SaveFormat.PDF);
//            //上传pdf
//            Map<String, Object> resultPdfMap = userUtil.uploadDocument(tmpPdfFile, false);
//            if (CollectionUtil.isEmpty(resultMap)) {
//                throw new ServiceException("责任公示书生成失败！");
//            }
//            String previewUrl = resultPdfMap.get("openUrl").toString();
//
//            DutyAgreementTemplate template = new DutyAgreementTemplate();
//            template.setOrgId(orgId);
//            template.setTemplateName(templateName);
//            template.setTemplateUrl(templateUrl);
//            template.setPreviewUrl(previewUrl);// TODO 乱码了
//            template.setClassifyId(classifyId);
//            template.setTargetTaskId(targetTaskId);
//            template.setStatus(1);
//
//            dutyAgreementService.addTemplate(template);
//            return R.success("责任公示书上传成功！");
//
//        } finally {
//            if (Objects.nonNull(tmpPdfFile)) {
//                tmpPdfFile.delete();
//            }
//        }
//    }
//}