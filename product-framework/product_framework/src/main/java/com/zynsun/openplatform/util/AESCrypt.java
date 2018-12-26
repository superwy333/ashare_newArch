package com.zynsun.openplatform.util;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCrypt {
    private final Cipher cipher;
    private final SecretKeySpec key;
    private AlgorithmParameterSpec spec;

    private static final byte[] DESkey = {
            (byte)0xaf,(byte)0x52,(byte)0xde,(byte)0x45,(byte)15,(byte)0x58,(byte)0xcd,(byte)0x10,(byte)0x23,(byte)0x8b,
            (byte)0x45,(byte)0x6a,(byte)0x10,(byte)0x90,(byte)0xaf,(byte)0xbd,(byte)0xad,(byte)0xdb,(byte)0xae,(byte)0x8d,
            (byte)0xac,(byte)0x80,(byte)0x52,(byte)0xff,(byte)0x45,(byte)0x90,(byte)0x85,(byte)0xca,(byte)0xcb,(byte)0x9f,
            (byte)0xaf,(byte)0xbd
    };

    private static final byte[] DESIV = { (byte)2, (byte)0xaf, (byte)0xbc, (byte)0xab, (byte)0xcc, (byte)0x90, (byte)0x39, (byte)0x90,
            (byte)0xbc, (byte)0xaf, (byte)0x86, (byte)0x99, (byte)0xad, (byte)170, (byte)0xfb, (byte)0x60 };


    public AESCrypt() throws Exception {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        key = new SecretKeySpec(DESkey, "AES");
        spec = new IvParameterSpec(DESIV);
    }

    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedText = new String(new BASE64Encoder().encode(encrypted).getBytes(), "UTF-8");
        return encryptedText;
    }

    public String decrypt(String cryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = new BASE64Decoder().decodeBuffer(cryptedText);
        byte[] decrypted = cipher.doFinal(bytes);
        String decryptedText = new String(decrypted, "UTF-8");
        return decryptedText;
    }

    public static void main(String[] args) throws Exception {
    	String mm = "hxbp0CIxkbTxP47edjFs15blOrtPELRpAPR3KB00H8TRiLx5u87nGjHcj9Ie+Apz6RkVSJeiDqCC44wtYBy1t4vqMKYqGvXecefnq32An3hf5OOzgZpkQ4P9we9r/9GcH1COokkBzkIqk8ZcVgpSFQ==";
        AESCrypt aesCrypt = new AESCrypt();
        String miwen =  aesCrypt.encrypt("5<014-0018*172*88*1038>>>36<71/4-<233016<21*0<63630<8><1+2*+560<794/<2<6+9880130>>>36<<89322277016<41*0<02+3");
        System.out.println(miwen);
       // miwen = "y3gNCiYNqBxY2FV4WcTwYj9UMY969yzwnDWK3mgoYEBaoVKdtPr2Sw4KTYhYWie9rL1pKmaWre65wjqii+v/bP6uGcUSAxqt2nXxRuJQR7WkYbtjBKzYtG4kJDNpCd3o91gIqYW5Nk9gygzAGgk94Q==";
        String mingwen = aesCrypt.decrypt(mm);
        System.out.println(mingwen);
    }
}