package com.github.saintdan.util;

import java.math.BigDecimal;

/**
 * Some math utilities of high-precision calculation and max, min calculation.
 *
 * NOTICE:
 * BigInteger and BigDecimal are immutable,
 * they will create a new object every step in calculations.
 * The memory may cry.
 * So, we should use long, double, float to do scientific computation.
 *
 * Now, we change long, double, float to String.
 * And use the construction of BigDecimal like: new BigDecimal(String).
 * Use the BigDecimal to do calculation.
 * And then, change the result to origin type.
 *
 * @author LiaoYifan
 * @date 4/1/15
 * @since JDK1.8
 */
public final class MathUtil {

    /**
     * Default precision.
     */
    private static final int DIV_SCALE = 10;
    /**
     * Default rounding Mode.
     */
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    private MathUtil() {
        super();
    }

    // High-precision calculation

    // Double high-precision calculation
    /**
     * Double addition.
     * @param double1 double1
     * @param double2 double2
     * @return        sum = double1 + double2
     */
    public static double doubleAdd(final double double1, final double double2) {
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(double1));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(double2));
        return bigDecimal1.add(bigDecimal2).doubleValue();
    }

    /**
     * Double subtraction.
     * @param double1 double1
     * @param double2 double2
     * @return        difference = double1 - double2
     */
    public static double doubleSub(final double double1,final double double2){
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(double1));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(double2));
        return bigDecimal1.subtract(bigDecimal2).doubleValue();
    }

    /**
     * Double multiplication
     * @param double1 double1
     * @param double2 double2
     * @return        product ＝ double1 * double2
     */
    public static double doubleMultiply(final double double1,final double double2){
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(double1));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(double2));
        return bigDecimal1.multiply(bigDecimal2).doubleValue();
    }

    /**
     * Double division.
     * @param dividend      dividend
     * @param divisor       divisor
     * @param scale         scale (default:{@link #DIV_SCALE})
     * @param roundingMode  roundingMode (default:{@link #ROUNDING_MODE})
     * @return              quotient ＝ dividend / divisor
     */
    public static double doubleDivide(final double dividend, final double divisor, int scale, int roundingMode) {

        if(scale<0) {
            scale=DIV_SCALE;
        }
        if(roundingMode<0) {
            roundingMode=ROUNDING_MODE;
        }
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(dividend));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(divisor));
        return bigDecimal1.divide(bigDecimal2, scale, roundingMode).doubleValue();
    }

    /**
     * Double division default without scale param.
     * @param dividend dividend
     * @param divisor  right value
     * @return         quotient ＝ dividend / divisor
     */
    public static double doubleDivide(final double dividend, final double divisor) {
        return doubleDivide(dividend, divisor, DIV_SCALE);
    }

    /**
     * Double division default with scale param.
     * @param dividend dividend
     * @param divisor  divisor
     * @param scale    scale
     * @return         quotient ＝ dividend / divisor
     */
    public static double doubleDivide(final double dividend, final double divisor, int scale) {
        return doubleDivide(dividend, divisor, scale, ROUNDING_MODE);
    }

    /**
     * Double round.
     * @param value         the value who what's to be rounded.
     * @param scale         scale (default:{@link #DIV_SCALE})
     * @param roundingMode  roundingMode (default:{@link #ROUNDING_MODE})
     * @return              round double
     */
    public static double doubleRound(final double value,int scale,int roundingMode) {
        if(scale<0) {
            scale=DIV_SCALE;
        }
        if(roundingMode<0) {
            roundingMode=ROUNDING_MODE;
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,roundingMode).doubleValue();
    }

    /**
     * Double round default without scale param.
     * @param value value
     * @return      round double
     */
    public static double doubleRound(final double value) {
        return doubleRound(value, DIV_SCALE, ROUNDING_MODE);
    }

    /**
     * Double round default with scale param.
     * @param value value
     * @param scale scale
     * @return      round double
     */
    public static double doubleRound(final double value,int scale) {
        return doubleRound(value, scale, ROUNDING_MODE);
    }

    // Float high-precision calculation
    /**
     * Float addition
     * @param float1 float1
     * @param float2 float2
     * @return       sum = float1 + float2
     */
    public static float floatAdd(final float float1, final float float2) {
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(float1));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(float2));
        return bigDecimal1.add(bigDecimal2).floatValue();
    }

    /**
     * Double subtraction.
     * @param float1 float1
     * @param float2 float2
     * @return        difference = float1 - float2
     */
    public static float floatSub(final float float1,final float float2){
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(float1));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(float2));
        return bigDecimal1.subtract(bigDecimal2).floatValue();
    }

    /**
     * float multiplication
     * @param float1 float1
     * @param float2 float2
     * @return        product ＝ float1 * float2
     */
    public static float floatMultiply(final float float1,final float float2){
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(float1));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(float2));
        return bigDecimal1.multiply(bigDecimal2).floatValue();
    }

    /**
     * float division.
     * @param dividend      dividend
     * @param divisor       divisor
     * @param scale         scale (default:{@link #DIV_SCALE})
     * @param roundingMode  roundingMode (default:{@link #ROUNDING_MODE})
     * @return              quotient ＝ dividend / divisor
     */
    public static float floatDivide(final float dividend, final float divisor, int scale, int roundingMode) {

        if(scale<0) {
            scale=DIV_SCALE;
        }
        if(roundingMode<0) {
            roundingMode=ROUNDING_MODE;
        }
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(dividend));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(divisor));
        return bigDecimal1.divide(bigDecimal2, scale, roundingMode).floatValue();
    }

    /**
     * float division default without scale param.
     * @param dividend dividend
     * @param divisor  right value
     * @return         quotient ＝ dividend / divisor
     */
    public static float floatDivide(final float dividend, final float divisor) {
        return floatDivide(dividend, divisor, DIV_SCALE);
    }

    /**
     * float division default with scale param.
     * @param dividend dividend
     * @param divisor  divisor
     * @param scale    scale
     * @return         quotient ＝ dividend / divisor
     */
    public static float floatDivide(final float dividend, final float divisor, int scale) {
        return floatDivide(dividend, divisor, scale, ROUNDING_MODE);
    }

    /**
     * float round.
     * @param value         the value who what's to be rounded.
     * @param scale         scale (default:{@link #DIV_SCALE})
     * @param roundingMode  roundingMode (default:{@link #ROUNDING_MODE})
     * @return              round float
     */
    public static float floatRound(final float value,int scale,int roundingMode) {
        if(scale<0) {
            scale=DIV_SCALE;
        }
        if(roundingMode<0) {
            roundingMode=ROUNDING_MODE;
        }
        BigDecimal b = new BigDecimal(Float.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,roundingMode).floatValue();
    }

    /**
     * float round default without scale param.
     * @param value value
     * @return      round float
     */
    public static float floatRound(final float value) {
        return floatRound(value, DIV_SCALE, ROUNDING_MODE);
    }

    /**
     * float round default with scale param.
     * @param value value
     * @param scale scale
     * @return      round float
     */
    public static float floatRound(final float value,int scale) {
        return floatRound(value, scale, ROUNDING_MODE);
    }

    // Max and Min.

    // Long Max and Min.
    public static long longMin(long... values) {
        long current = values[0];

        for (int i=1; i < values.length; i++) {
            current = Math.min(values[i], current);
        }

        return current;
    }

    public static long longMax(long... values) {
        long current = values[0];

        for (int i=1; i < values.length; i++) {
            current = Math.max(values[i], current);
        }

        return current;
    }

    // Double Max and Min.
    public static double doubleMin(double... values) {
        double current = values[0];

        for (int i=1; i < values.length; i++) {
            current = Math.min(values[i], current);
        }

        return current;
    }

    public static double doubleMax(double... values) {
        double current = values[0];

        for (int i=1; i < values.length; i++) {
            current = Math.max(values[i], current);
        }

        return current;
    }
}
