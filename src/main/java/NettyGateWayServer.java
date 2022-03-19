import java.util.Arrays;

import javaAdvance.web.gateway.inbound.InboundServer;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class NettyGateWayServer {

    public final static String GATEWAY_NAME = "NettyGateway";
    public final static String GATEWAY_VERSION = "2.0.0";

    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort", "8888");

        String backendsServers = System.getProperty("backendsServers", "http://localhost:8801");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " is starting......");

        InboundServer server = new InboundServer(port, Arrays.asList(backendsServers.split(",")));

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + "started at http://localhost: " +
                port + " for server: " + server.toString());
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
