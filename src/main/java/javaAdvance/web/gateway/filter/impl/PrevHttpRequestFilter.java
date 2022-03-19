package javaAdvance.web.gateway.filter.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import javaAdvance.web.gateway.filter.HttpRequestFilter;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class PrevHttpRequestFilter  implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("king", "aoyg");
    }


}
