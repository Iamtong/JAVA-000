package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        URI uri = new URI("/");
        FullHttpRequest request;
        request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        ctx.writeAndFlush(request);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("msg -> "+msg);
        DefaultLastHttpContent response = (DefaultLastHttpContent) msg;
        ByteBuf buf = response.content();
        String result = buf.toString(CharsetUtil.UTF_8);
        System.out.println("response -> "+result);
    }
}