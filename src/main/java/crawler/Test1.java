package crawler;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test1 {

    public static void main(String[] args) throws Exception {
        /**
         * 需要获取图片的网页url
         */
        String url = "http://www.hzim.org/p/hzim/match/textpath.jsf?match=35588577";
        /**
         * 网页的源代码
         */
        String sourceCode = getSourceCode(url);
        /**
         * 需要匹配的源代码的正则表达式
         */
        String regExp = "<img(.+?)src=\"(.+?)\\.(jpg|jpeg|bmp|gif|png)\"";
        /**
         * 保存网页源代码的文件路径
         */
        String fileUrl = "/Users/Baltan/Desktop/old-driver.txt";
        /**
         * 保存网页文件的文件夹路径
         */
        String dirUrl = "/Users/Baltan/Desktop/pic";
        ArrayList<String> picUrlList = getPicUrlList(url, sourceCode, regExp, fileUrl);
        savePic(picUrlList, dirUrl);
    }

    /**
     * 获取网页的源代码
     *
     * @param url 需要获取图片的网页url
     * @return 网页的源代码
     * @throws Exception
     */
    public static String getSourceCode(String url) throws Exception {
        URL realUrl = new URL(url);
//        URLConnection connection = realUrl.openConnection();
//        connection.connect();
//        InputStream is = connection.getInputStream();
        InputStream is = realUrl.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String sourceCode;
        while ((sourceCode = br.readLine()) != null) {
            sb.append(sourceCode);
        }
        if (br != null) {
            br.close();
        }
        return sb.toString();
    }

    /**
     * 获得网页源代码中所有图片路径集合，并写入到本地文件
     *
     * @param url        需要获取图片的网页url
     * @param sourceCode 网页的源代码
     * @param regExp     需要匹配的源代码的正则表达式
     * @param fileUrl    保存网页源代码的文件路径
     * @return 匹配到的网页源代码内容集合
     * @throws Exception
     */
    public static ArrayList<String> getPicUrlList(String url, String sourceCode, String regExp,
                                                  String fileUrl)
            throws Exception {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceCode);
        File file = new File(fileUrl);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        ArrayList<String> picUrlList = new ArrayList<>();
        URL realUrl = new URL(url);
        while (matcher.find()) {
            String str = matcher.group();
            int start = str.indexOf("src=\"") + 5;
            int end = str.length() - 1;
            /**
             * 获得图片路径
             */
            str = str.substring(start, end);
            /**
             * 如果图片路径为相对路径，将其改为绝对路径
             */
            if (str.startsWith("/")) {
                str = realUrl.getProtocol() + "://" + realUrl.getHost() + str;
            }
            String picUrl = str;
            picUrlList.add(picUrl);
            bw.write(picUrl);
            bw.write("\n");
        }
        bw.flush();
        if (bw != null) {
            bw.close();
        }
        return picUrlList;
    }

    /**
     * 将网页上的本地文件保存到本地文件夹中
     *
     * @param picUrlList 匹配到的网页源代码内容集合
     * @param dirUrl     保存网页文件的文件夹路径
     * @throws Exception
     */
    public static void savePic(ArrayList<String> picUrlList, String dirUrl) throws Exception {
        int i;
        for (i = 0; i < picUrlList.size(); i++) {
            String url = picUrlList.get(i);
            /**
             * 图片本地保存路径，图片命名为原始文件名
             */
            String picName =
                    dirUrl + "/" + url.substring(url.lastIndexOf("/") + 1);
            URL picUrl = new URL(url);
            File picFile = new File(picName);
            FileUtils.copyURLToFile(picUrl, picFile);
        }
        System.out.println("下载完成！");
    }
}