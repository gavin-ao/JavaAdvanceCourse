package javaAdvance.web.gateway.router.impl;

import java.util.List;
import java.util.Random;

import javaAdvance.web.gateway.router.EndpointRouter;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class SingleRandomRouter implements EndpointRouter {
    @Override
    public String route(List<String> backendsServers) {
        int size = backendsServers.size();
        Random random = new Random(System.currentTimeMillis());
        return backendsServers.get(random.nextInt(size));
    }
}
