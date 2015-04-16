package com.github.saintdan.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Some String utilities.
 * @author Liao Yifan
 * @date 4/2/15
 * @since JDK1.8
 */
public class StringUtil extends StringUtils{

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
     * Phone number validation.
     * @param  number
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

    public static boolean equals(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        }
        return str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        }
        return str1.equalsIgnoreCase(str2);
    }

    public static boolean equalsWithTrim(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        }
        return str1.trim().equals(str2.trim());
    }

    public static boolean equalsIgnoreCaseWithTrim(final String str1, final String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        }
        return str1.trim().equalsIgnoreCase(str2.trim());
    }

    public static boolean hasContent(final String s) {
        return !(trimLength(s) == 0);    // faster than returning !isEmpty()
    }

    public static int length(final String s) {
        return s == null ? 0 : s.length();
    }

    public static int trimLength(final String s) {
        return (s == null) ? 0 : s.trim().length();
    }
}
