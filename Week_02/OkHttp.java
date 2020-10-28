import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttp {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8801/").build();
        Response response = okHttpClient.newCall(request).execute();
        String body = response.body().string();
        String contentType = response.header("content-type");
        System.out.println(body+' '+contentType);
    }

}
