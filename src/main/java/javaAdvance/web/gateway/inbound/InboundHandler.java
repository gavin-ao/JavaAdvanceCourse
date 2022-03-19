package javaAdvance.web.gateway.inbound;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import javaAdvance.web.gateway.filter.HttpRequestFilter;
import javaAdvance.web.gateway.filter.impl.PrevHttpRequestFilter;
import javaAdvance.web.gateway.outbound.HttpOutboundHandler;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class InboundHandler extends ChannelInboundHandlerAdapter{

    private static Logger logger = LoggerFactory.getLogger(InboundHandler.class);
    private final List<String> backendsServers;
    private HttpOutboundHandler handler;
    private PrevHttpRequestFilter filter = new PrevHttpRequestFilter();

    public InboundHandler (List<String> backendsServers) {
        this.backendsServers = backendsServers;
        this.handler = new HttpOutboundHandler(this.backendsServers);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;

            handler.handle(fullRequest, ctx, filter);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

}
