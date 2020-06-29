package compression_test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;


/**
 * Description: 获取文件类型工具
 * 参考：<a href="https://www.cnblogs.com/ryelqy/p/10104171.html"></a>
 *
 * @author Baltan
 * @date 2020-06-29 22:02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class FileUtil {
    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    static {
        /**
         * 初始化文件类型信息
         */
        getAllFileType();
    }

    /**
     * getAllFileType ->常见文件头信息
     */
    private static void getAllFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF");
        FILE_TYPE_MAP.put("png", "89504E47");
        FILE_TYPE_MAP.put("gif", "47494638");
        FILE_TYPE_MAP.put("tif", "49492A00");
        FILE_TYPE_MAP.put("bmp", "424D");
        FILE_TYPE_MAP.put("dwg", "41433130");
        FILE_TYPE_MAP.put("html", "68746D6C3E");
        FILE_TYPE_MAP.put("rtf", "7B5C727466");
        FILE_TYPE_MAP.put("504B0304", "xlsx");
        FILE_TYPE_MAP.put("504B0304", "docx");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053");
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
        FILE_TYPE_MAP.put("pst", "2142444E");
        FILE_TYPE_MAP.put("xls", "D0CF11E0");
        FILE_TYPE_MAP.put("doc", "D0CF11E0");
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E");
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
        FILE_TYPE_MAP.put("pwl", "E3828596");
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD");
        FILE_TYPE_MAP.put("rm", "2E524D46");
        FILE_TYPE_MAP.put("mpg", "000001BA");
        FILE_TYPE_MAP.put("mov", "6D6F6F76");
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
        FILE_TYPE_MAP.put("mid", "4D546864");
    }

    public final static String getFileByFile(byte[] bytes) {
        return getFileTypeByStream(bytes);
    }

    private final static String getFileTypeByStream(byte[] b) {
        String fileTypeHex = String.valueOf(getFileHexString(b));
        Iterator<Entry<String, String>> iterator = FILE_TYPE_MAP.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            String fileTypeHexValue = entry.getValue();

            if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private final static String getFileHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        if (Objects.isNull(bytes) || bytes.length <= 0) {
            return null;
        }

        int length = bytes.length;

        for (int i = 0; i < length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);

            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}
