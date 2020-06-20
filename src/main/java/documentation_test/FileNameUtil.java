package documentation_test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-22 15:00
 */
public class FileNameUtil {
    public static void main(String[] args) {
        final String directoryPath = "/Users/Baltan/Desktop/未命名文件夹";
        batchRenameFiles(directoryPath);
    }

    /**
     * 批量重命名文件
     *
     * @param directoryPath
     */
    public static void batchRenameFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("路径有误！");
            return;
        }

        File[] files = directory.listFiles();
        Set<String> fileNames = new HashSet<>();
        int order = 1;

        /**
         * 对文件进行排序，先依据文件名，再依据文件序号，例如：A (1).java、A (2).java、A (3).java、B (1).java……
         */
        Arrays.sort(files, (f1, f2) -> {
            String fileName1 = f1.getName();
            String fileName2 = f2.getName();
            int seperator1Index1 = fileName1.lastIndexOf("(");
            int seperator2Index1 = fileName1.lastIndexOf(")");
            int seperator1Index2 = fileName2.lastIndexOf("(");
            int seperator2Index2 = fileName2.lastIndexOf(")");
            String name1 = fileName1.substring(0, seperator1Index1).trim();
            String name2 = fileName2.substring(0, seperator1Index2).trim();
            int order1 = Integer.valueOf(fileName1.substring(seperator1Index1 + 1, seperator2Index1));
            int order2 = Integer.valueOf(fileName2.substring(seperator1Index2 + 1, seperator2Index2));

            if (Objects.equals(name1, name2)) {
                return order1 - order2;
            } else {
                return name1.compareTo(name2);
            }
        });

        for (File file : files) {
            String fileName = file.getName();
            String prefix = fileName.substring(0, fileName.lastIndexOf("(")).trim();
            String suffix = fileName.substring(fileName.lastIndexOf(".")).trim();
            if (!fileNames.contains(prefix)) {
                order = 1;
                fileNames.add(prefix);
                String newName = directoryPath + "/" + prefix + " (" + order + ")" + suffix;
                File newFile = new File(newName);
                order++;
                if (newFile.exists()) {
                    continue;
                } else {
                    file.renameTo(newFile);
                }
            } else {
                String newName = directoryPath + "/" + prefix + " (" + order + ")" + suffix;
                File newFile = new File(newName);
                order++;
                if (newFile.exists()) {
                    continue;
                } else {
                    file.renameTo(newFile);
                }
            }
        }
    }

    /**
     * 为文件名添加序号，例如：XXX.java修改为XXX (1).java
     *
     * @param directoryPath
     */
    public static void fileNameAddOrder(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("路径有误！");
            return;
        }

        File[] files = directory.listFiles();

        for (File file : files) {
            String fileName = file.getName();
            int suffixIndex = fileName.lastIndexOf(".");

            if (!fileName.contains("(") && !fileName.contains(")")) {
                String newFileName =
                        fileName.substring(0, suffixIndex) + " (1)" + fileName.substring(suffixIndex);
                file.renameTo(new File(directoryPath + "/" + newFileName));
            }
        }
    }
}
