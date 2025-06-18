package nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/11 10:40
 */
public class ProjectPackageTest {
    public static void main(String[] args) throws IOException {
        /**
         * 目标文本文件路径名+文件名
         */
        final String fileName = "E:/hccb(不要删)/Desktop/新建文本文档 (2).txt";
        /**
         * 桌面路径
         */
        final String desktopPath = "E:/hccb(不要删)/Desktop";
        /**
         * 工作空间路径
         */
        final String workSpacePath = "D:/gongjijinEclipseWorkspace";
        /**
         * java源代码文件公共的目录结构
         */
        final String javaPublicPath = "/src/main/java/com/ycnet/mirage/fund";
        /**
         * class文件公共的目录结构
         */
        final String classPublicPath = "/target/classes/com/ycnet/mirage/fund";

        /**
         * 读取文本文件获取要复制的java文件
         */
        List<String> fileList = readFile(fileName);

        String projectName = getProjectName(fileList);

        generateFullStructure(desktopPath, projectName, workSpacePath, javaPublicPath, classPublicPath, fileList);
    }

    /**
     * 将文本文件中的java类文件路径或全限定类名存入列表中
     *
     * @param fileName
     *            文件路径名+文件名
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String fileName) throws IOException {
        List<String> fileList = new ArrayList<>();

        final String MODE = "rw";
        RandomAccessFile file = new RandomAccessFile(fileName, MODE);
        FileChannel channel = file.getChannel();
        /**
         * 创建容量为1024字节的缓冲区
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        /**
         * 从通道读取内容到缓冲区，返回值表示了有多少字节被读到了缓冲区中。如果返回-1，表示到了文件末尾
         */
        int bytesRead = channel.read(buffer);
        StringBuilder builder = new StringBuilder();
        while (bytesRead != -1) {
            /**
             * 缓冲区从写模式切换到读模式
             */
            buffer.flip();

            while (buffer.hasRemaining()) {
                /**
                 * 每次从缓冲区中读取一个字节
                 */
                int asciiCode = buffer.get();
                /**
                 * 从缓存区中读取到的文件中字节可能包含"."、"/"、"_"、0-9、a-z、A-Z
                 */
                if ((asciiCode >= 46 && asciiCode <= 57) || (asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122) || asciiCode == 95) {
                    builder.append((char) asciiCode);
                }
                else {
                    String className = builder.toString().trim();
                    if (!"".equals(className)) {
                        fileList.add(className);
                    }
                    builder.delete(0, builder.length());
                }
                if (!buffer.hasRemaining()) {
                    fileList.add(builder.toString().trim());
                }
            }
            /**
             * 清除已经读过的数据，任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
             */
            buffer.compact();
            bytesRead = channel.read(buffer);
        }
        file.close();
        return fileList;
    }

    /**
     * 获取项目名称
     *
     * @param fileList
     * @return
     */
    public static String getProjectName(List<String> fileList) {
        String projectName = null;
        if (!fileList.isEmpty()) {
            String fileName = fileList.get(0);
            /**
             * 例如：companyManage/src/main/java/com/ycnet/mirage/fund/controller/LoginController.java
             */
            fileName = fileName.substring(1);
            /**
             * 例如：companyManage
             */
            projectName = fileName.substring(0, fileName.indexOf("/"));
        }
        return projectName;
    }

    /**
     * 生成完整的目录结构
     *
     * @param desktopPath
     * @param projectName
     * @param workSpacePath
     * @param javaPublicPath
     * @param classPublicPath
     * @param fileList
     * @throws IOException
     */
    public static void generateFullStructure(String desktopPath, String projectName, String workSpacePath, String javaPublicPath, String classPublicPath,
                                             List<String> fileList) throws IOException {
        if (projectName != null) {
            /**
             * 例如：/companyManage/src/main/java/com/ycnet/mirage/fund/controller/LoginController.java
             */
            for (String fileName : fileList) {
                /**
                 * class文件公共的目录结构后面的部分，例如：controller/LoginController.class
                 */
                String classFilePostPath = fileName.substring(fileName.indexOf(javaPublicPath) + javaPublicPath.length() + 1, fileName.lastIndexOf("."))
                        + ".class";
                /**
                 * java源文件路径，例如：D:/workSpace/companyManage/src/main/java/com/ycnet/mirage/fund/controller/LoginController.java
                 */
                String javaSourceFile = workSpacePath + fileName;
                /**
                 * java源文件拷贝到桌面上对应的文件路径，例如：E:/hccb/Desktop/companyManage/src/main/java/com/ycnet/miragefund/controller/LoginController.java
                 */
                String javaDestinationFile = desktopPath + fileName;
                /**
                 * class原始文件路径，例如：D:/workSpace/companyManage/target/classes/com/ycnet/mirage/fund/controller/LoginController.class
                 */
                String classSourceFile = workSpacePath + "/" + projectName + classPublicPath + "/" + classFilePostPath;
                /**
                 * class文件拷贝到桌面上对应的文件路径，例如：E:/hccb/Desktop/companyManage/target/classes/com/ycnet/mirage/fund/controller/LoginController.class
                 */
                String classDestinationFile = desktopPath + "/" + projectName + classPublicPath + "/" + classFilePostPath;

                /**
                 * java源文件拷贝到桌面上的文件夹，例如：E:/hccb/Desktop/companyManage/src/main/java/com/ycnet/mirage/fund/controller
                 */
                File javaDirectory = new File(javaDestinationFile.substring(0, javaDestinationFile.lastIndexOf("/")));
                /**
                 * class文件拷贝到桌面上的文件夹，例如：E:/hccb/Desktop/companyManage/target/classes/com/ycnet/mirage/fund/controller
                 */
                File classDirectory = new File(classDestinationFile.substring(0, classDestinationFile.lastIndexOf("/")));
                if (!javaDirectory.exists()) {
                    javaDirectory.mkdirs();
                }
                if (!classDirectory.exists()) {
                    classDirectory.mkdirs();
                }

                /**
                 * 复制文件
                 */
                copyFile(javaSourceFile, javaDestinationFile);
                copyFile(classSourceFile, classDestinationFile);

                /**
                 * 处理内部类class文件
                 *
                 * class原始文件所在文件夹，例如：D:/workSpace/companyManage/target/classes/com/ycnet/mirage/fund/controller
                 */
                String classSourceDirectoryPath = classSourceFile.substring(0, classSourceFile.lastIndexOf("/"));

                File classSourceDirectory = new File(classSourceDirectoryPath);
                if (classSourceDirectory.isDirectory()) {
                    /**
                     * 获得所有内部类class文件，内部类class文件包含“$”
                     */
                    File[] innerClassList = classSourceDirectory.listFiles(pathname -> {
                        String fileName1 = pathname.getName();
                        /**
                         * 类名，例如：LoginController$1.class或LoginController$LoginControllerInnerClass.class
                         */
                        return fileName1.contains("$");
                    });

                    for (File innerClass : innerClassList) {
                        /**
                         * class原始文件路径，例如：D:/workSpace/companyManage/target/classes/com/ycnet/mirage/fund/controller/LoginController$1.class
                         *
                         * 将返回的绝对路径中的“\”都替换成“/”
                         */
                        String innerClassSourceFile = innerClass.getAbsolutePath().replaceAll("\\\\", "/");
                        /**
                         * class文件公共的目录结构后面的部分，例如：controller/LoginController$1.class
                         */
                        String innerClassFilePostPath = innerClassSourceFile.substring(innerClassSourceFile.indexOf(classPublicPath) + classPublicPath.length() + 1);
                        /**
                         * class文件拷贝到桌面上对应的文件路径，例如：E:/hccb/Desktop/companyManage/target/classes/com/ycnet/mirage/fund/controller/LoginController.class
                         */
                        String innerClassDestinationFile = desktopPath + "/" + projectName + classPublicPath + "/" + innerClassFilePostPath;
                        /**
                         * 复制文件
                         */
                        copyFile(innerClassSourceFile, innerClassDestinationFile);
                    }
                }
            }
        }
    }

    /**
     * 复制文件
     *
     * @param sourcePath
     * @param destinationPath
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        final String MODE = "rw";

        RandomAccessFile sourceFile = new RandomAccessFile(sourcePath, MODE);
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile destinationSource = new RandomAccessFile(destinationPath, MODE);
        FileChannel destinationChannel = destinationSource.getChannel();

        long position = 0;
        long amount = sourceChannel.size();
        destinationChannel.transferFrom(sourceChannel, position, amount);
    }
}