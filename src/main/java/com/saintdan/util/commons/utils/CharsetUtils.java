package com.saintdan.util.commons.utils;

import org.apache.commons.lang3.CharSetUtils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Some charset utilities.
 * "Love your life, and keep away from charset trap."
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/16/15
 * @since JDK1.8
 */
public class CharsetUtils extends CharSetUtils{
    /**
     * 16-bit UTF (UCS Transformation Format) whose byte order is identified by
     * an optional byte-order mark
     */
    public static final Charset UTF_16 = Charset.forName("UTF-16");

    /**
     * 16-bit UTF (UCS Transformation Format) whose byte order is big-endian
     */
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");

    /**
     * 16-bit UTF (UCS Transformation Format) whose byte order is little-endian
     */
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

    /**
     * 8-bit UTF (UCS Transformation Format)
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * ISO Latin Alphabet No. 1, as known as <tt>ISO-LATIN-1</tt>
     */
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    /**
     * 7-bit ASCII, as known as ISO646-US or the Basic Latin block of the
     * Unicode character set
     */
    public static final Charset US_ASCII = Charset.forName("US-ASCII");

    private CharsetUtils() {

    }

    private static final ThreadLocal<Map<Charset, CharsetEncoder>> encoders =
            new ThreadLocal<Map<Charset, CharsetEncoder>>() {
                @Override
                protected Map<Charset, CharsetEncoder> initialValue() {
                    return new IdentityHashMap<Charset, CharsetEncoder>();
                }
            };

    private static final ThreadLocal<Map<Charset, CharsetDecoder>> decoders =
            new ThreadLocal<Map<Charset, CharsetDecoder>>() {
                @Override
                protected Map<Charset, CharsetDecoder> initialValue() {
                    return new IdentityHashMap<Charset, CharsetDecoder>();
                }
            };

    /**
     * Returns a cached thread-local {@link CharsetEncoder} for the specified <tt>charset</tt>.
     * @param charset {@link java.nio.charset.Charset}
     * @return {@link java.nio.charset.CharsetEncoder}
     */
    public static CharsetEncoder getEncoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }

        Map<Charset, CharsetEncoder> map = encoders.get();
        CharsetEncoder e = map.get(charset);
        if (e != null) {
            e.reset();
            e.onMalformedInput(CodingErrorAction.REPLACE);
            e.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return e;
        }

        e = charset.newEncoder();
        e.onMalformedInput(CodingErrorAction.REPLACE);
        e.onUnmappableCharacter(CodingErrorAction.REPLACE);
        map.put(charset, e);
        return e;
    }

    /**
     * Returns a cached thread-local {@link CharsetDecoder} for the specified <tt>charset</tt>.
     * @param charset {@link java.nio.charset.Charset}
     * @return {@link CharsetDecoder}
     */
    public static CharsetDecoder getDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }

        Map<Charset, CharsetDecoder> map = decoders.get();
        CharsetDecoder d = map.get(charset);
        if (d != null) {
            d.reset();
            d.onMalformedInput(CodingErrorAction.REPLACE);
            d.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return d;
        }

        d = charset.newDecoder();
        d.onMalformedInput(CodingErrorAction.REPLACE);
        d.onUnmappableCharacter(CodingErrorAction.REPLACE);
        map.put(charset, d);
        return d;
    }
}
