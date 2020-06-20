package crypt_test;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-14 16:12
 */
public class EncryptUtils {
    public static String publicKeyString =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDov9Qpa9rk6VUtPS4wgyi2154l6nzb0s05NPhXwel6G" +
                    "/QE8O5eIXmnUCZ22TxPJ+0OPesZauK6bPOR44BCpWuVTfF1rs4GCMK+QEp" +
                    "/VXQqH1YTzpJS6c5upp8J4A0rzNVR3fpTgzdmi5w1nzhkaiMLYffTSpb/09W/FPoFYSXhrQIDAQAB";

    /**
     * 加密方法
     *
     * @param srcBytes
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] srcBytes) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyString);

        if (publicKeyString != null) {
            /**
             * Cipher负责完成加密或解密工作，基于RSA
             */
            Cipher cipher = Cipher.getInstance("RSA");
            /**
             * 根据公钥，对Cipher对象进行初始化
             */
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 根据公钥生成PublicKey对象
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}
