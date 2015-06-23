package com.github.saintdan.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Conversation utilities.
 *
 * Transformer factory.
 * "Autobot, transform and roll out!"
 *
 * Autobots:
 *     Long x = convert("123", Long.class);
 *     Date d = convert("1949/10/01", Date.class)
 *     int y = convert(11.0, int.class)
 *     String date = convert(date, String.class)
 *     String date = convert(calendar, String.class)
 *     Short t = convert(true, short.class);
 *     Long date = convert(calendar, long.class);
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/2/15
 * @since JDK1.8
 */
public final class ConvertUtil {

    private ConvertUtil() {
    }

    public static Object convert(Object value, Class targetType) {
        if (targetType == null) {
            throw new IllegalArgumentException("Target type cannot be null in ConvertUtil.convert(value, targetType)");
        }
        switch (targetType.getName()) {
            case "byte":
                return toByte(value);
            case "java.lang.Byte":
                return toJavaLangByte(value);
            case "java.lang.String":
                return toJavaLangString(value);
            case "boolean":
                return toBoolean(value);
            case "java.lang.Boolean":
                return toJavaLangBoolean(value);
            case "short":
                return toShort(value);
            case "java.lang.Short":
                return toJavaLangShort(value);
            case "int":
                return toInt(value);
            case "java.lang.Integer":
                return toJavaLangInteger(value);
            case "long":
                return toLong(value);
            case "java.lang.Long":
                return toJavaLangLong(value);
            case "java.math.BigDecimal":
                return toBigDecimal(value);
            case "java.math.BigInteger":
                return toBigInteger(value);
            case "float":
                return toFloat(value);
            case "java.lang.Float":
                return toJavaLangFloat(value);
            case "double":
                return toDouble(value);
            case "java.lang.Double":
                return toJavaLangDouble(value);
            case "java.time.LocalDateTime":
                return toJavaTime(value);

        }
        throw new IllegalArgumentException("Unsupported type : '" + targetType.getName() + "' for conversion");
    }

    private static Object toByte(Object value) {
        if (value == null) {
            return (byte) 0;
        }
        return value;
    }

    private static Object toJavaLangByte(Object value) {
        try {
            if (value == null) {
                return ((Number) value).byteValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return (byte) 0;
                }
                return Byte.valueOf(((String) value).trim());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? (byte) 1 : (byte) 0;
            }
        }
        catch(Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'Byte'", e);
        }
        return failure(value, "Byte");
    }

    private static Object toJavaLangString(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return value;
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).stripTrailingZeros().toPlainString();
        } else if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Date) {
            return DateFormat.getDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(value);
        } else if (value instanceof Calendar) {
            return DateFormat.getDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(((Calendar) value).getTime());
        } else if (value instanceof Character) {
            return "" + value;
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        return failure(value, "String");
    }

    private static Object toBoolean(Object value) {
        if (value == null) {
            return Boolean.FALSE;
        }
        return value;
    }

    private static Object toJavaLangBoolean(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue() != 0;
        } else if (value instanceof String) {
            if (StringUtil.isBlank((String) value)) {
                return Boolean.FALSE;
            }
            String string = (String) value;
            return "true".equalsIgnoreCase(string) ? Boolean.TRUE : Boolean.FALSE;
        }
        return failure(value, "Boolean");
    }

    private static Object toShort(Object value) {
        if (value == null) {
            return (short) 0;
        }
        return value;
    }

    private static Object toJavaLangShort(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Short) {
                return value;
            } else if (value instanceof Number) {
                return ((Number)value).shortValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String)value)) {
                    return (short)0;
                }
                return Short.valueOf(((String) value).trim());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? (short) 1 : (short) 0;
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'Short'", e);
        }
        return failure(value, "Short");
    }

    private static Object toInt(Object value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    private static Object toJavaLangInteger(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Integer) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return 0;
                }
                return Integer.valueOf(((String) value).trim());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? 1 : 0;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to an 'Integer'", e);
        }
        return failure(value, "Integer");
    }

    private static Object toLong(Object value) {
        if (value == null) {
            return 0L;
        }
        return value;
    }

    private static Object toJavaLangLong(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Long) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).longValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return 0L;
                }
                return Long.valueOf(((String) value).trim());
            } else if (value instanceof Date) {
                return ((Date) value).getTime();
            } else if (value instanceof Boolean) {
                return (Boolean) value ? 1L : 0L;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'Long'", e);
        }
        return failure(value, "Long");
    }

    private static Object toBigDecimal(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof BigDecimal) {
                return value;
            } else if (value instanceof BigInteger) {
                return new BigDecimal((BigInteger) value);
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return BigDecimal.ZERO;
                }
                return new BigDecimal(((String) value).trim());
            } else if (value instanceof Number) {
                return new BigDecimal(((Number) value).doubleValue());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? BigDecimal.ONE : BigDecimal.ZERO;
            } else if (value instanceof Date) {
                return new BigDecimal(((Date) value).getTime());
            } else if (value instanceof Calendar) {
                return new BigDecimal(((Calendar) value).getTime().getTime());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'BigDecimal'", e);
        }
        return failure(value, "BigDecimal");
    }

    private static Object toBigInteger(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof BigInteger) {
                return value;
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).toBigInteger();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return BigInteger.ZERO;
                }
                return new BigInteger(((String) value).trim());
            } else if (value instanceof Number) {
                return new BigInteger(Long.toString(((Number) value).longValue()));
            } else if (value instanceof Boolean) {
                return (Boolean) value ? BigInteger.ONE : BigInteger.ZERO;
            } else if (value instanceof Date) {
                return new BigInteger(Long.toString(((Date) value).getTime()));
            } else if (value instanceof Calendar) {
                return new BigInteger(Long.toString(((Calendar) value).getTime().getTime()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'BigInteger'", e);
        }
        return failure(value, "BigInteger");
    }

    private static Object toFloat(Object value) {
        if (value == null) {
            return 0.0f;
        }
        return value;
    }

    private static Object toJavaLangFloat(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Float) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).floatValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return 0.0f;
                }
                return Float.valueOf(((String) value).trim());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? 1.0f : 0.0f;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'Float'", e);
        }
        return failure(value, "Float");
    }

    private static Object toDouble(Object value) {
        if (value == null) {
            return 0.0d;
        }
       return value;
    }

    private static Object toJavaLangDouble(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Double) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                if (StringUtil.isBlank((String) value)) {
                    return 0.0d;
                }
                return Double.valueOf(((String) value).trim());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? 1.0d : 0.0d;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("value [" + getTypeName(value) + "] could not be converted to a 'Double'", e);
        }
        return failure(value, "Double");
    }

    private static Object toJavaTime(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof java.sql.Date) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(((java.sql.Date) value).getTime()), ZoneId.systemDefault());
        } else if (value instanceof java.util.Date) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(((Date) value).getTime()), ZoneId.systemDefault());
        } else if (value instanceof Calendar) {
            return LocalDateTime.ofInstant(((Calendar) value).toInstant(), ZoneId.systemDefault());
        } else if (value instanceof Timestamp) {
            return LocalDateTime.ofInstant(((Timestamp) value).toInstant(), ZoneId.systemDefault());
        }
        return failure(value, "java.time.LocalDateTime");
    }

    /**
     * Fail to convert.
     * @param value      value
     * @param targetType targetType
     * @return           new IllegalArgumentException
     */
    private static String failure(Object value, String targetType) {
        throw new IllegalArgumentException("Unsupported value type [" + getTypeName(value) + "] attempting to convert to '" + targetType + "'");
    }

    /**
     * Get the value's type name
     * @param value value
     * @return      type name
     */
    private static String getTypeName(Object value) {
        return value.getClass().getName() + " (" + value.toString() + ")";
    }

}
