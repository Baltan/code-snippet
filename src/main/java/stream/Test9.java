package stream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description:
 *
 * @author baltan
 * @date 2023/3/28 16:29
 */
public class Test9 {
    public static void main(String[] args) {
        String srcPath = "/Users/baltan/Desktop/test.png";
        String filename = "test.png";
        compress(srcPath, filename);
    }

    private static void compress(String srcPath, String filename) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos)) {
            compress(new File(srcPath), zos, filename);
            MultipartFile multipartFile =
                    new MockMultipartFile(filename, filename + ".zip", "application/x-zip-compressed", baos.toByteArray());
            System.out.println(multipartFile.getName());
            System.out.println(multipartFile.getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compress(File srcFile, ZipOutputStream zos, String filename) throws Exception {
        if (srcFile.isDirectory()) {
            zos.putNextEntry(new ZipEntry(filename + File.separator));

            for (File file : srcFile.listFiles()) {
                compress(file, zos, filename + File.separator + file.getName());
            }
        } else {
            zos.putNextEntry(new ZipEntry(filename));
            FileInputStream in = new FileInputStream(srcFile);
            byte[] data = new byte[4096];
            int buf;

            while ((buf = in.read(data)) != -1) {
                zos.write(data, 0, buf);
            }
            in.close();
        }
    }
}
