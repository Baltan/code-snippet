package documentation;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/6/10 14:23
 */
public class RenameUtil {
    public static void main(String[] args) {
        final String directoryPath = "/Users/Baltan/Desktop/未命名文件夹";
//        final String directoryPath = "/Users/Baltan/Downloads";
        String filename = "";
        int order = 1;
        rename(directoryPath, filename, order);
    }

    /**
     * 批量重命名文件
     *
     * @param directoryPath
     * @param filename
     * @param order
     */
    private static void rename(String directoryPath, String filename, int order) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("路径有误！");
            return;
        }
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }
        /**
         * 将文件按照原始文件名排序
         */
        Arrays.sort(files, Comparator.comparing(File::getName));

        for (File file : files) {
            String suffix = file.getName().substring(file.getName().lastIndexOf("."));
            String newName = directoryPath + "/" + filename + " (" + order++ + ")" + suffix;
            File newFile = new File(newName);
            file.renameTo(newFile);
        }
    }
}
