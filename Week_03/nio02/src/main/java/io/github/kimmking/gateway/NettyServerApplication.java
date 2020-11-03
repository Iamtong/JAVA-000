package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;
import io.github.kimmking.gateway.router.TongHttpendpointRouter;
import okhttp3.*;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        //String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        //添加随机路由
        TongHttpendpointRouter router = new TongHttpendpointRouter();
        List<String> endpoints = Arrays.asList("http://localhost:8088/api/hello","http://localhost:8088/api/hello");
        String proxyServer = router.route(endpoints);

        String proxyPort = System.getProperty("proxyPort","8888");
        
          //  http://localhost:8888/api/hello  ==> gateway API
          //  http://localhost:8088/api/hello  ==> backend service
    
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
