package javaAdvance.web.gateway.filter.impl;

import io.netty.handler.codec.http.FullHttpResponse;
import javaAdvance.web.gateway.filter.HttpResponseFilter;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class PostHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("queen", "beauty woman");
    }
}
