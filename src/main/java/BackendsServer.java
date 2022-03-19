import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javaAdvance.web.server.SingleThreadSocketServer;

/**
 * Created by aoyonggang on 2022/3/19.
 */
public class BackendsServer {

    public static void main(String[] args) {
        Map<String, Class> serverMaps = new HashMap<>();
        serverMaps.put("SingleThreadSocket", SingleThreadSocketServer.class);

        Class clazz = serverMaps.get("SingleThreadSocket");
        try {
            Method method = clazz.getDeclaredMethod("main", new Class[]{String[].class});
            method.invoke(null, new Object[]{new String[]{}});
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
