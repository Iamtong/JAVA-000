package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class TongHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        //System.out.println(fullRequest.headers().get("content-type"));
        System.out.println("Request filter start:"+fullRequest.headers().get("content-type"));
        fullRequest.headers().add("nio","liujiantong");
        System.out.println("Request filter add nio");
    }
}
