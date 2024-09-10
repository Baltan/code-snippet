package io_test;

import cn.hutool.core.io.IoUtil;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author baltan
 * @date 2024/9/10 10:39
 */
public class ReadLineTest {
    @SneakyThrows
    public static void main(String[] args) {
        List<String> ips = new ArrayList<>();
        BufferedReader reader = IoUtil.getUtf8Reader(Files.newInputStream(Paths.get("/Users/baltan/Desktop/ip.txt")));
        /**
         * 逐行读取文本到集合中
         */
        IoUtil.readLines(reader, ips);
        System.out.println(ips.size());
    }
}
