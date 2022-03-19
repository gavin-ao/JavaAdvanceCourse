package javaAdvance.web.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public interface HttpResponseFilter {

    void filter(FullHttpResponse response);

}
