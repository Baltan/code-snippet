package ip_test;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.net.NetUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Description: IP网段工具类
 *
 * @author baltan
 * @date 2024/9/10 10:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IpAddressRangeUtil {
    /**
     * 判断IP网段checkedIpAddressRange是否和已有IP网段otherIpAddressRanges存在重叠
     *
     * @param checkedIpAddressRange
     * @param ipAddressRange
     * @return
     */
    public static boolean isIpAddressOverlapped(String checkedIpAddressRange, String ipAddressRange) {
        String[] checkedIpStrings = checkedIpAddressRange.split("/");
        String checkedIp = checkedIpStrings[0];
        int checkedMask = Integer.parseInt(checkedIpStrings[1]);
        /**
         * 例如：135.178.43.1/1
         * 网络地址：128.0.0.0
         * 广播地址：255.255.255.255
         */
        String checkedNetworkAddress = Ipv4Util.getBeginIpStr(checkedIp, checkedMask);
        String checkedBroadcastAddress = Ipv4Util.getEndIpStr(checkedIp, checkedMask);
        long checkedFirstUsableIpAddressDecimalValue = getFirstUsableIpAddressDecimalValue(checkedNetworkAddress);
        long checkedLastUsableIpAddressDecimalValue = getLastUsableIpAddressDecimalValue(checkedBroadcastAddress);

        String[] ipStrings = ipAddressRange.split("/");
        String ip = ipStrings[0];
        int mask = Integer.parseInt(ipStrings[1]);
        String networkAddress = Ipv4Util.getBeginIpStr(ip, mask);
        String broadcastAddress = Ipv4Util.getEndIpStr(ip, mask);
        long firstUsableIpAddressDecimalValue = getFirstUsableIpAddressDecimalValue(networkAddress);
        long lastUsableIpAddressDecimalValue = getLastUsableIpAddressDecimalValue(broadcastAddress);
        return isIpAddressBetween(checkedFirstUsableIpAddressDecimalValue, firstUsableIpAddressDecimalValue, lastUsableIpAddressDecimalValue) ||
                isIpAddressBetween(firstUsableIpAddressDecimalValue, checkedFirstUsableIpAddressDecimalValue, checkedLastUsableIpAddressDecimalValue);
    }

    /**
     * 根据广播地址计算最后一个可用IP
     *
     * @param broadcastAddress
     * @return
     */
    public static long[] getLastUsableIpAddress(String broadcastAddress) {
        long[] lastUsableIpAddress = new long[4];
        /**
         * 最后一个可用IP的二进制值比广播地址的二进制值小1
         */
        long ipAddressDecimalValue = getIpAddressDecimalValue(broadcastAddress) - 1;

        for (int i = lastUsableIpAddress.length - 1; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                lastUsableIpAddress[i] += (ipAddressDecimalValue & 1) << j;
                ipAddressDecimalValue >>= 1;
            }
        }
        return lastUsableIpAddress;
    }

    /**
     * 根据广播地址计算最后一个可用IP的十进制值
     *
     * @param broadcastAddress
     * @return
     */
    public static long getLastUsableIpAddressDecimalValue(String broadcastAddress) {
        /**
         * 第一个可用IP的二进制值比网络地址的二进制值大1
         */
        return getIpAddressDecimalValue(broadcastAddress) - 1;
    }

    /**
     * 根据网络地址计算第一个可用IP
     *
     * @param networkAddress
     * @return
     */
    public static long[] getFirstUsableIpAddress(String networkAddress) {
        long[] firstUsableIpAddress = new long[4];
        /**
         * 第一个可用IP的二进制值比网络地址的二进制值大1
         */
        long ipAddressDecimalValue = getIpAddressDecimalValue(networkAddress) + 1;

        for (int i = firstUsableIpAddress.length - 1; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                firstUsableIpAddress[i] += (ipAddressDecimalValue & 1) << j;
                ipAddressDecimalValue >>= 1;
            }
        }
        return firstUsableIpAddress;
    }

    /**
     * 根据网络地址计算第一个可用IP的十进制值
     *
     * @param networkAddress
     * @return
     */
    public static long getFirstUsableIpAddressDecimalValue(String networkAddress) {
        /**
         * 第一个可用IP的二进制值比网络地址的二进制值大1
         */
        return getIpAddressDecimalValue(networkAddress) + 1;
    }

    /**
     * 判断IP地址checkedIpAddress是否介于startIpAddress和endIpAddress之间（均包含）
     *
     * @param checkedIpAddress
     * @param startIpAddress
     * @param endIpAddress
     * @return
     */
    public static boolean isIpAddressBetween(long[] checkedIpAddress, long[] startIpAddress, long[] endIpAddress) {
        /**
         * 如果checkedIpAddress在startIpAddress之后（包含），则可能有以下四种情况：
         */
        return (
                (checkedIpAddress[0] > startIpAddress[0]) ||
                        (checkedIpAddress[0] == startIpAddress[0] && checkedIpAddress[1] > startIpAddress[1]) ||
                        (checkedIpAddress[0] == startIpAddress[0] && checkedIpAddress[1] == startIpAddress[1] && checkedIpAddress[2] > startIpAddress[2]) ||
                        (checkedIpAddress[0] == startIpAddress[0] && checkedIpAddress[1] == startIpAddress[1] && checkedIpAddress[2] == startIpAddress[2] && checkedIpAddress[3] >= startIpAddress[3])
                /**
                 * 如果checkedIpAddress在endIpAddress之前（包含），则可能有以下四种情况：
                 */
        ) && (
                (checkedIpAddress[0] < endIpAddress[0]) ||
                        (checkedIpAddress[0] == endIpAddress[0] && checkedIpAddress[1] < endIpAddress[1]) ||
                        (checkedIpAddress[0] == endIpAddress[0] && checkedIpAddress[1] == endIpAddress[1] && checkedIpAddress[2] < endIpAddress[2]) ||
                        (checkedIpAddress[0] == endIpAddress[0] && checkedIpAddress[1] == endIpAddress[1] && checkedIpAddress[2] == endIpAddress[2] && checkedIpAddress[3] <= endIpAddress[3])
        );
    }

    /**
     * 判断IP地址checkedIpAddress是否介于startIpAddress和endIpAddress之间（均包含）
     *
     * @param checkedIpAddressDecimalValue
     * @param startIpAddressDecimalValue
     * @param endIpAddressDecimalValue
     * @return
     */
    public static boolean isIpAddressBetween(long checkedIpAddressDecimalValue, long startIpAddressDecimalValue, long endIpAddressDecimalValue) {
        return checkedIpAddressDecimalValue >= startIpAddressDecimalValue && checkedIpAddressDecimalValue <= endIpAddressDecimalValue;
    }

    /**
     * 计算IP地址的十进制值
     *
     * @param ipAddress
     * @return
     */
    public static long getIpAddressDecimalValue(String ipAddress) {
        String[] ipAddressSegments = ipAddress.split("\\.");
        long segment0 = Long.parseLong(ipAddressSegments[0]);
        long segment1 = Long.parseLong(ipAddressSegments[1]);
        long segment2 = Long.parseLong(ipAddressSegments[2]);
        long segment3 = Long.parseLong(ipAddressSegments[3]);
        return (segment0 << 24) + (segment1 << 16) + (segment2 << 8) + segment3;
    }

    /**
     * 判断ip是否在网段ipAddressRange范围内
     *
     * @param ip
     * @param ipAddressRange
     * @return
     */
    public static boolean isInRange(String ip, String ipAddressRange) {
        return NetUtil.isInRange(ip, ipAddressRange);
    }
}
