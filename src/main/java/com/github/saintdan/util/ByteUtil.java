package com.github.saintdan.util;

/**
 * Simple encode and decode.
 * @author Liao Yifan
 * @date 4/13/15
 * @since JDK1.8
 */
public final class ByteUtil {

    private static final char[] HEX =
        {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };

    private ByteUtil() {
        super();
    }

    /**
     * HEX string -> byte[].
     * @param str src
     * @return    bytes
     */
    public static byte[] decode(final String str) {
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }

        byte[] bytes = new byte[length / 2];
        int position = 0;

        for (int i = 0; i < length; i += 2) {
            byte high = (byte)Character.digit(str.charAt(i), 16);
            byte low = (byte)Character.digit(str.charAt(i + 1), 16);
            bytes[position++] = (byte)(high * 16 + low);
        }
        return bytes;
    }

    /**
     * byte[] -> HEX string
     * @param bytes
     * @return
     */
    public static String encode(final byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length << 1);
        for (byte aByte : bytes)
        {
            sb.append(convertDigit(aByte >> 4));
            sb.append(convertDigit(aByte & 0x0f));
        }
        return sb.toString();
    }

    private static char convertDigit(final int value)
    {
        return HEX[(value & 0x0f)];
    }
}
