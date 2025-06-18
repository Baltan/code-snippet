package lambda_test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/22 16:01
 */
public class FilterFileDemo {
    public static void main(String[] args) {
        ArrayList<File> list = new ArrayList<>();

        // 也可用FileFilter接口并重写其accept方法
        Predicate<File> filter = pathname -> {
            if (pathname.getName().endsWith(".mp3")) {
                return true;
            }
            return false;
        };

        File dir = new File("/Users/Baltan/Music/网易云音乐");

        getAllFiles(dir, list, filter);

        for (File file : list) {
            System.out.println(file.getName());
        }
    }

    //获取指定文件目录下所有符合条件的文件集合
    public static void getAllFiles(File dir, List<File> list, Predicate<File> filter) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllFiles(file, list, filter);
                } else {
                    if (filter.test(file)) {
                        list.add(file);
                    }
                }
            }
        } else {
            if (filter.test(dir)) {
                list.add(dir);
            }
        }
    }
}
