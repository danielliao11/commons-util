package com.saintdan.util.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Get the host's IP or domain name.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/13/15
 * @since JDK1.8
 */
public class InetAddressUtils {

    private InetAddressUtils() {
        super();
    }

    /**
     * Get the host.
     * @return
     * @throws UnknownHostException
     */
    public static InetAddress getLocalhost() throws UnknownHostException{
        return InetAddress.getLocalHost();
    }

    /**
     * Get the host's IP(byte[]).
     * @return
     */
    public static byte[] getIpByte() {
        try {
            return getLocalhost().getAddress();
        } catch (Exception e) {
            return new byte[]{0, 0, 0, 0};
        }
    }

    /**
     * Get the host's IP(String).
     * @return
     */
    public static String getIpString() {
        try {
            return getLocalhost().getHostAddress();
        } catch (Exception e) {
            return "0.0.0.0";
        }
    }

    /**
     * Get the host's name.
     * @return
     */
    public static String getHostName() {
        try {
            return getLocalhost().getHostName();
        } catch (Exception e) {
            return "localhost";
        }
    }
}
