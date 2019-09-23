package zcq.myjpa.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/23
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    private static int port;

    private static String hostAddress;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        port = webServerInitializedEvent.getWebServer().getPort();
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ServerConfig.port = port;
    }

    public static String getHostAddress() {
        return hostAddress;
    }

    public static void setHostAddress(String hostAddress) {
        ServerConfig.hostAddress = hostAddress;
    }
}
