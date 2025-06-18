package crypt;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-14 16:09
 */
public class DecryptUtils {
    public static String privateKeyString =
            "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOi/1Clr2uTpVS09LjCDKLbXniXqfNvSzTk0" +
                    "+FfB6Xob9ATw7l4headQJnbZPE8n7Q496xlq4rps85HjgEKla5VN8XWuzgYIwr5ASn9VdCofVhPOklLpzm6mnwngDSvM1VHd+lODN2aLnDWfOGRqIwth99NKlv/T1b8U+gVhJeGtAgMBAAECgYEA3+9JIv1Jozs4n4kgur5G2vzDhXmB3kKI/8SOOGkyyTDB7EsfHOQaVzdPesPyObqRY0WBwHfNY07WXoRD8FuWEEAwPk20E5WB8uQyfFnGlEBgQAlCos3vjmDGFMiFoJT1BPQVSqUUWCAeLPNIYIr5BmXakGlCT6t/EI79wRIhAyECQQD1aE9h3TDy5MWteyNzaALzpyE34IpDKKkEkWSigLd8FVYoBz8YMdUztXa3lOCRg1NHo8zh+mkVqkTW4DNKQFM1AkEA8sumSVu70nIHDPfd6ff9v0bVJ/C/z2VRrfiwl/lk/rAVuO/IwFVehvV/0ct9byHJQ8W29MhRnnRgsW9A/tlrmQJBAMlM3QDLraEwIy6geX3q7bDrgQYSu5dHPQ0wLjpWFyxDXD7NYuX3yoj16wG/r03tSVzUcfVTsOtCleEkWy4nAZkCQQCClHKfe529ZiRSnjCUuBKZwA++BmYytcxDI8BeS/w8WhmkuBmhD4jiTsDq5WVV0rb7Iyft6Jq0/ORQKYSsS+TpAkBF8dLaN4wuOoHzO9q0A+4W4NNw8hTsUt/lDFgd68sTYNw4hsEP2oZ9lbLmxjdsRiMflixMPYGZuB9RxcpMha1N";

    /**
     * 解密方法
     *
     * @param srcBytes
     * @return
     * @throws Exception
     * @see <a href="https://blog.csdn.net/qy20115549/article/details/83105736"></a>
     */
    public static byte[] decrypt(byte[] srcBytes) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyString);

        if (Objects.nonNull(privateKey)) {
            /**
             * Cipher负责完成加密或解密工作，基于RSA
             */
            Cipher cipher = Cipher.getInstance("RSA");
            /**
             * 根据公钥，对Cipher对象进行初始化
             */
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 根据私钥生成PrivateKey对象
     *
     * @param key
     * @return
     * @throws Exception
     * @see <a href="https://www.cnblogs.com/KKatherine/p/4128444.html"></a>
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
}
