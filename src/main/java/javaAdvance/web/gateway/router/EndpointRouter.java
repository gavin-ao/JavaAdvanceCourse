package javaAdvance.web.gateway.router;

import java.util.List;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public interface EndpointRouter {
    String route(List<String> backendsServers);
}
