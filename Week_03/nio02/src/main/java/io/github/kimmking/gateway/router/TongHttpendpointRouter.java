package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class TongHttpendpointRouter implements HttpEndpointRouter {
    @Override
    //创建一个随机取值；
    public String route(List<String> endpoints) {
        //endpoints.add("http://localhost:8888/api/hello");
        //endpoints.add("http://localhost:8888/api/hello");
        //随机
        Random random = new Random();
        int n = random.nextInt(endpoints.size());
        return endpoints.get(n);
    }
}
