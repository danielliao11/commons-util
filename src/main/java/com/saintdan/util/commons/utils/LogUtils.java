package com.saintdan.util.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 8/25/15
 * @since JDK1.8
 */
public class LogUtils {

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void trackInfo(Log log, String msg) {
        log.info(generateTraceString(msg, null));
    }

    public static void trackWarn(Log log, String msg) {
        log.warn(generateStackTrace(msg));
    }

    public static void traceDebug(Log log, Throwable e) {
        log.debug(generateTraceString(null, e));
    }

    public static void traceDebug(Log log, Throwable e, String errorMsg) {
        log.debug(generateTraceString(errorMsg, e));
    }

    public static void traceError(Log log, Throwable e) {
        log.error(generateTraceString(null, e));
    }

    public static void traceError(Log log, Throwable e, String errorMsg) {
        log.error(generateTraceString(errorMsg, e));
    }

    /**
     * Generate stack trace.
     *
     * @param msg
     *                      message
     * @return
     *                      stack trace
     */
    public static String generateStackTrace(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(msg);
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < trace.length; i++) {
            sb.append("\tat ");
            sb.append(trace[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generate trace string.
     *
     * @param errorMsg
     *                      error message
     * @param e
     *                      exception
     * @return
     *                      trace string
     */
    public static String generateTraceString(String errorMsg, Throwable e) {
        StringWriter w = new StringWriter();
        w.append("Message is: ").append(errorMsg);
        PrintWriter out = new PrintWriter(w);
        if (!StringUtils.isEmpty(errorMsg)) {
            out.println(errorMsg);
        }
        if (e != null) {
            e.printStackTrace(out);
        }
        return w.toString();
    }
}
