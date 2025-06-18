package crypt;

import java.security.KeyPairGenerator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-14 16:14
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        String msg = "唐牛才是食神！";
        /**
         * KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
         */
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        /**
         * 初始化密钥对生成器，密钥大小为1024位
         */
        keyPairGen.initialize(1024);

        byte[] srcBytes = msg.getBytes();
        /**
         * 公钥加密
         */
        byte[] resultBytes = EncryptUtils.encrypt(srcBytes);
        /**
         * 私钥解密
         */
        byte[] decBytes = DecryptUtils.decrypt(resultBytes);

        System.out.println("明文:" + msg);
        System.out.println("加密后密文:" + new String(resultBytes));
        System.out.println("解密后明文:" + new String(decBytes));
    }
}
