package javaAdvance.web.gateway.inbound;

import java.util.List;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class InboundInitializer  extends ChannelInitializer<SocketChannel> {
    private List<String> backendsServers;
    public InboundInitializer(List<String> backendsServers) {
        this.backendsServers = backendsServers;
    }

    @Override
    public void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new InboundHandler(this.backendsServers));
    }
}
