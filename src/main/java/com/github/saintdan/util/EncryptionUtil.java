package com.github.saintdan.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Encryption and decryption utilities.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/13/15
 * @since JDK1.8
 */
public class EncryptionUtil {

    private EncryptionUtil() {

    }

    /**
     * Encrypt string content.
     * @param key     encrypt key
     * @param content src
     * @return        encrypted content
     */
    public static String encrypt(String key, String content) {
        try {
            return ByteUtil.encode(createAesEncryptionCipher(key).doFinal(content.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException("Error occurred encrypting data", e);
        }
    }

    /**
     * Encrypt byte[] content.
     * @param key       encrypt key
     * @param content   src
     * @return          encrypted content
     */
    public static String encryptBytes(String key, byte[] content) {
        try {
            return ByteUtil.encode(createAesEncryptionCipher(key).doFinal(content));
        } catch (Exception e) {
            throw new IllegalStateException("Error occurred encrypting data", e);
        }
    }

    /**
     * Decrypt HEX string.
     * @param key       decrypt key
     * @param hexStr    src
     * @return          decrypted string
     */
    public static String decrypt(String key, String hexStr) {
        try {
            return new String(createAesDecryptionCipher(key).doFinal(ByteUtil.decode(hexStr)));
        } catch (Exception e) {
            throw new IllegalStateException("Error occurred decrypting data", e);
        }
    }

    /**
     * Decrypt bytes.
     * @param key       decrypt key
     * @param hexStr    src
     * @return          decrypted byte[]
     */
    public static byte[] decryptBytes(String key, String hexStr) {
        try {
            return createAesDecryptionCipher(key).doFinal(ByteUtil.decode(hexStr));
        } catch (Exception e) {
            throw new IllegalStateException("Error occurred decrypting data", e);
        }
    }

    /**
     * Calculate a SHA-256 hash string.
     * @param digest MessageDigest
     * @param bytes  SHA string
     * @return
     */
    public static String calculateHash(MessageDigest digest, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        digest.update(bytes);
        return ByteUtil.encode(digest.digest());
    }

    /**
     * Fast MD5 calculation.
     * @param  file src
     * @return MD5  string
     */
    public static String fastMD5(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return calculateMD5Hash(in.getChannel());
        } catch (IOException e) {
            return null;
        } finally {
            IOUtil.closeQuietly(in);
        }
    }

    /**
     * Calculate a MD5 hash string.
     * @param  channel src
     * @return MD5     string
     * @throws IOException
     */
    public static String calculateMD5Hash(FileChannel channel) throws IOException {
        ByteBuffer bb = ByteBuffer.allocateDirect(65536);
        MessageDigest d = getMD5Digest();

        int nRead;

        while ((nRead = channel.read(bb)) != -1) {
            if (nRead == 0) {
                continue;
            }
            bb.position(0);
            bb.limit(nRead);
            d.update(bb);
            bb.clear();
        }
        return ByteUtil.encode(d.digest());
    }

    public static String calculateMD5Hash(byte[] bytes) {
        return calculateHash(getMD5Digest(), bytes);
    }

    /**
     * Get digest.
     * @param digest digest of string
     * @return       MessageDigest
     */
    public static MessageDigest getDigest(String digest) {
        try {
            return MessageDigest.getInstance(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("The requested MessageDigest " + digest + " does not exist", e);
        }
    }

    public static MessageDigest getMD5Digest() {
        return getDigest("MD5");
    }

    public static String calculateSHA1Hash(byte[] bytes) {
        return calculateHash(getSHA1Digest(), bytes);
    }

    public static MessageDigest getSHA1Digest() {
        return getDigest("SHA-1");
    }

    public static String calculateSHA256Hash(byte[] bytes) {
        return calculateHash(getSHA256Digest(), bytes);
    }

    public static MessageDigest getSHA256Digest() {
        return getDigest("SHA-256");
    }

    public static String calculateSHA512Hash(byte[] bytes) {
        return calculateHash(getSHA512Digest(), bytes);
    }

    public static MessageDigest getSHA512Digest() {
        return getDigest("SHA-512");
    }

    public static byte[] createCipherBytes(String key, int bitsNeeded) {
        String word = calculateMD5Hash(key.getBytes());
        return word.substring(0, bitsNeeded / 8).getBytes();
    }

    public static Cipher createAesEncryptionCipher(String key) throws Exception {
        return createAesCipher(key, Cipher.ENCRYPT_MODE);
    }

    public static Cipher createAesDecryptionCipher(String key) throws Exception {
        return createAesCipher(key, Cipher.DECRYPT_MODE);
    }

    public static Cipher createAesCipher(String key, int mode) throws Exception {
        Key sKey = new SecretKeySpec(createCipherBytes(key, 128), "AES");
        return createAesCipher(sKey, mode);
    }

    public static Cipher createAesCipher(Key key, int mode) throws Exception {
        MessageDigest d = getMD5Digest();
        d.update(key.getEncoded());
        byte[] iv = d.digest();

        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, key, paramSpec);
        return cipher;
    }
}
