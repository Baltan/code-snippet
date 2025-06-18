package word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-10-30 14:13
 */
public class Test1 {
    public static void main(String[] args) throws IOException, TemplateException {
        // 要填充的数据, 注意map的key要和word中${xxx}的xxx一致
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("uniqueCode", UUID.randomUUID().toString());
        dataMap.put("name", "name");
        dataMap.put("gender", "gender");
        dataMap.put("nation", "nation");
        dataMap.put("idCard", "idCard");
        dataMap.put("politicsStatus", "politicsStatus");
        dataMap.put("employer", "employer");
        dataMap.put("duty", "duty");
        dataMap.put("partyTime", "partyTime");
        dataMap.put("identity", "identity");
        dataMap.put("householdRegistration", "householdRegistration");
        dataMap.put("currentResidence", "currentResidence");
        dataMap.put("personalResume", "personalResume");
        dataMap.put("imageUrl", getImageStr("/Users/Baltan/Desktop/图片.png"));
        dataMap.put("remark", "remark");

        List<RelativeInfo> relativeInfos = new ArrayList<>();
        relativeInfos.add(new RelativeInfo("父", "张三", "123", "少先队员", "搬砖"));
        relativeInfos.add(new RelativeInfo("母", "李四", "456", "共青团员", "挖煤"));
        relativeInfos.add(new RelativeInfo("弟", "王五", "789", "无党派人士", "写bug"));
        dataMap.put("relativeInfos", relativeInfos);

        /**
         * Configuration读取ftl文件
         */
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        /**
         * 指定ftl文件所在目录的路径
         */
        configuration.setDirectoryForTemplateLoading(new File("/Users/Baltan/Workspaces/IntelliJ IDEA/code-snippet/src/main/java/word_test/template"));
        File outFile = new File("/Users/Baltan/Desktop/××××.doc");
        /**
         * 以utf-8的编码读取ftl文件
         */
        Template t = configuration.getTemplate("template.ftl", "utf-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        t.process(dataMap, out);
        out.close();
    }

    private static String getImageStr(String filePath) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(filePath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
