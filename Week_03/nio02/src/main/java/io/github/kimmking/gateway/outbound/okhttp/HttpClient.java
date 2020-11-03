package io.github.kimmking.gateway.outbound.okhttp;


import okhttp3.*;

import java.io.IOException;
// Okhttp 请求客户端 使用3个异步执行观察
public class HttpClient {
    public static void main(String[] args){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8888").build();
        System.out.println("start:fetchGet3");
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("获取数据1:"+response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("获取数据2:"+response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("获取数据3:"+response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

}
