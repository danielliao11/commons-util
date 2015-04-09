package com.github.saintdan.util;

import java.nio.charset.Charset;

/**
 * Some String utilities.
 * @author LiaoYifan
 * @date 4/2/15
 * @since JDK1.8
 */
public class StringUtil {

    /**
     * 0~9 A~Z char array
     */
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    /**
     * Hex array
     */
    private final static char[] HEXARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Single byte char
     */
    private static final char[] SBC = {'1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-',
            '_', '=', '+', '\\', '|', '[', ']', ';', ':', '\'', '"', ',', '<',
            '.', '>', '/', '?'};

    /**
     * Double byte char
     */
    private static final char[] DBC = {'１', '２', '３', '４', '５', '６', '７', '８',
            '９', '０', '！', '＠', '＃', '＄', '％', '︿', '＆', '＊', '（', '）', 'ａ',
            'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ', 'ｇ', 'ｈ', 'ｉ', 'ｊ', 'ｋ', 'ｌ', 'ｍ', 'ｎ',
            'ｏ', 'ｐ', 'ｑ', 'ｒ', 'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ', 'ｘ', 'ｙ', 'ｚ', 'Ａ',
            'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ', 'Ｇ', 'Ｈ', 'Ｉ', 'Ｊ', 'Ｋ', 'Ｌ', 'Ｍ', 'Ｎ',
            'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ', 'Ｓ', 'Ｔ', 'Ｕ', 'Ｖ', 'Ｗ', 'Ｘ', 'Ｙ', 'Ｚ', '－',
            '＿', '＝', '＋', '＼', '｜', '【', '】', '；', '：', '‘', '“', '，', '《',
            '。', '》', '／', '？'};
    /**
     * WEEK DAYS
     */
    private static final String[] WEEKDAYS = {"SUNDAY", "MONDAY", "TUESDAY",
            "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

    /**
     * Line separator
     */
    private static final String NEWLINE = String.format("%n");

    /**
     * The default charset of this Java virtual machine.
     */
    private static final Charset CHARSET = Charset.defaultCharset();

    /**
     * Blank validation
     * @param  cs
     * @return boolean
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Email validation
     * @param  str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        String regex="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if(!isBlank(str)) {
            return str.matches(regex);
        }
        return false;
    }

    /**
     * Special characters validation
     *
     * @param  str
     * @return boolean
     */
    public static boolean hasSpecialChar(String str) {
        String regex = "[a-zA-Z0-9_\\-]*";
        if(!isBlank(str)) {
            return !str.matches(regex);
        }
        return false;
    }

    /**
     *
     * @param  str
     * @return boolean
     */
    public static boolean isPhoneNum(String number) {
        int result = getPhoneNumberType(number);
        return (result > 0 && result < 4);
    }

    /**
     * Get Mobile operator
     * @param number
     * @return 0, 1, 2, 3, 4
     */
    public static int getPhoneNumberType(String number) {
        int flag = 0;
        if(isBlank(number)){
            return flag;
        }
        String cm = "^(((13[5-9])|(147)|(15[012789])|(18[23478]))\\d{8})|(134[0-8])\\d{7}$";
        String cu = "^((13[0-2])|(145)|(15[5-6])|(18[5-6]))\\d{8}$";
        String ct = "^(((133)|(153)|(18[019]))\\d{8})|(1349)\\d{7}$";

        String tel = "^((0[12]\\d\\d{8})|(0[3-9]\\d{2}\\d{7,8}))$";

        if (number.matches(cm)) {
            flag = 1;
        } else if (number.matches(cu)) {
            flag = 2;
        } else if (number.matches(ct)) {
            flag = 3;
        } else if (number.matches(tel)) {
            flag = 4;
        }
        return flag;
    }

}
