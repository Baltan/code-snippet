package documentation_test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-09-24 15:39
 */
public class HostsFileUtil {
    public static void main(String[] args) throws UnknownHostException {
        String[] urls = {
                "github.com",
                "gist.github.com",
                "assets-cdn.github.com",
                "raw.githubusercontent.com",
                "gist.githubusercontent.com",
                "cloud.githubusercontent.com",
                "camo.githubusercontent.com",
                "avatars0.githubusercontent.com",
                "avatars1.githubusercontent.com",
                "avatars2.githubusercontent.com",
                "avatars3.githubusercontent.com",
                "avatars4.githubusercontent.com",
                "avatars5.githubusercontent.com",
                "avatars6.githubusercontent.com",
                "avatars7.githubusercontent.com",
                "avatars8.githubusercontent.com",
                "github.global.ssl.fastly.net",
                "assets-cdn.github.com"
        };
        getIp(urls);
    }

    /**
     * 查询域名对应的IP
     *
     * @param urls
     * @throws UnknownHostException
     */
    public static void getIp(String[] urls) throws UnknownHostException {
        for (String url : urls) {
            InetAddress[] ips = InetAddress.getAllByName(url);

            for (InetAddress ip : ips) {
                System.out.println(ip.getHostAddress() + " " + url);
            }
        }
    }
}
