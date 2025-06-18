package io;

import lombok.SneakyThrows;

import java.io.FileOutputStream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/5/10 22:50
 */
public class CreateFileTest {
    @SneakyThrows
    public static void main(String[] args) {
        String path = "/Users/Baltan/Workspaces/IntelliJ IDEA/code-snippet/src/main/java/io_test/A.java";
        String text = "package io;\n\n" +
                "public class A {\n" +
                "    public static void main(String[] args) {\n" +
                "       System.out.println(\"666\");\n" +
                "    }\n" +
                "}";

        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text.getBytes());
        }
    }
}
