package netease_music;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Description: 参考：<a href="https://zhuanlan.zhihu.com/p/36561112"></a>
 *
 * @author Baltan
 * @date 2019-12-04 21:58
 */
public class EncryptUtil {
    private static final String MODULES =
            "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
    private static final String NONCE = "0CoJUm6Qyw8W8jud";
    private static final String PUB_KEY = "010001";
    private static final String CHARSET = StandardCharsets.UTF_8.name();
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_KEY_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String IV_PARAMETER = "0102030405060708";

    /**
     * 无法实例化工具类
     */
    private EncryptUtil() {
    }

    /**
     * 产生一个长度为keyLength的随机字符串
     *
     * @param keyLength
     * @return
     */
    private static String getSecretKey(int keyLength) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder(keyLength);
        Random rand = new Random();
        int charsLength = str.length();

        for (int i = 0; i < keyLength; i++) {
            builder.append(str.charAt(rand.nextInt(charsLength)));
        }
        return builder.toString();
    }

    /**
     * AES加密
     *
     * @param clearText
     * @param secretKey
     * @return
     * @throws Exception
     */
    private static String aesEncrypt(String clearText, String secretKey) throws Exception {
        /**
         * 参考：<a href="https://www.cnblogs.com/lingyejun/p/10971308.html"></a>
         */
        Cipher cipher = Cipher.getInstance(DEFAULT_KEY_ALGORITHM);
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(clearText.getBytes(CHARSET));
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * RSA加密
     *
     * @param secretKey
     * @return
     */
    private static String rsaEncrypt(String secretKey) {
        secretKey = new StringBuilder(secretKey).reverse().toString();
        String hexString = string2HexString(secretKey);
        BigInteger hexStringBigInteger = string2DecBigInteger(hexString);
        int pubKeyBigInteger = string2DecInteger(PUB_KEY);
        BigInteger modulusBigInteger = string2DecBigInteger(MODULES);
        BigInteger decBigInteger = hexStringBigInteger.pow(pubKeyBigInteger).mod(modulusBigInteger);
        String anotherHexString = decBigInteger2HexString(decBigInteger);
        String result = appendAtTheEndAtTheHead(anotherHexString, 256 - anotherHexString.length(), '0');
        return result;
    }

    /**
     * 字符串转为十六进制字符串
     * 参考：<a href="https://www.cnblogs.com/carryLess/p/6889378.html"></a>
     *
     * @param s
     * @return
     */
    private static String string2HexString(String s) {
        StringBuilder builder = new StringBuilder();

        for (char c : s.toCharArray()) {
            builder.append(Integer.toHexString((int) c));
        }
        return builder.toString();
    }

    /**
     * 十进制大数转为十六进制字符串
     *
     * @param decBigInteger
     * @return
     */
    private static String decBigInteger2HexString(BigInteger decBigInteger) {
        return decBigInteger.toString(16);
    }

    /**
     * 十六进制字符串转为十进制整型
     *
     * @param hexString
     * @return
     */
    private static int string2DecInteger(String hexString) {
        return Integer.valueOf(hexString, 16);
    }

    /**
     * 十六进制字符串转为十进制大数
     *
     * @param hexString
     * @return
     */
    private static BigInteger string2DecBigInteger(String hexString) {
        return new BigInteger(hexString, 16);
    }

    /**
     * 在字符串头部补足一定长度的字符
     *
     * @param oldString
     * @param filledLength
     * @param filledCharacter
     * @return
     */
    private static String appendAtTheEndAtTheHead(String oldString, int filledLength, char filledCharacter) {
        StringBuilder builder = new StringBuilder(oldString);

        for (int i = 0; i < filledLength; i++) {
            builder.insert(0, filledCharacter);
        }
        return builder.toString();
    }

    public static String[] getParam(String cipherText) throws Exception {
//        /**
//         * 产生一个长度为16的随机字符串
//         */
//        String secretKey = getSecretKey(16);
        String secretKey = NONCE;
        String params = aesEncrypt(aesEncrypt(cipherText, secretKey), NONCE);
        String encSecKey = rsaEncrypt(secretKey);
        return new String[]{params, encSecKey};
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("hlpretag", "<span class=\\\"s-fc7\\\">");
        requestParams.put("hlposttag", "</span>");
        requestParams.put("#/discover", "");
        requestParams.put("s", "李荣浩");
        requestParams.put("type", "1");
        requestParams.put("offset", "0");
        requestParams.put("total", "true");
        requestParams.put("limit", "30");
        requestParams.put("csrf_token", "");
        String cipherText = JSON.toJSONString(requestParams);
        String[] paramArray = getParam(cipherText);
        System.out.println("params: " + paramArray[0]);
        System.out.println("encSecKey: " + paramArray[1]);
    }
}
