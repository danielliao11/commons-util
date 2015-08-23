package com.saintdan.util.commons.utils;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-Safe date formatter.
 * Use ThreadLocal to ensure thread-safe.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/2/15
 * @since JDK1.8
 */
public class DateFormat extends Format {

    private final String FORMAT;
    private static final ThreadLocal<Map<String, SimpleDateFormat>> dateFormats = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        public Map<String, SimpleDateFormat> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };

    public static SimpleDateFormat getDateFormat(String format)
    {
        Map<String, SimpleDateFormat> formatters = dateFormats.get();
        SimpleDateFormat formatter = formatters.get(format);
        if (formatter == null)
        {
            formatter = new SimpleDateFormat(format);
            formatters.put(format, formatter);
        }
        return formatter;
    }

    public DateFormat(String format) {
        FORMAT = format;
    }

    public Object parseObject(String source, ParsePosition pos)
    {
        return getDateFormat(FORMAT).parse(source, pos);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
    {
        return getDateFormat(FORMAT).format(obj, toAppendTo, pos);
    }
}
