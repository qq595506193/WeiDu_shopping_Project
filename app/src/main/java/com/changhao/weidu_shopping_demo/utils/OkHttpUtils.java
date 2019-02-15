package com.changhao.weidu_shopping_demo.utils;

import android.os.Handler;

import com.changhao.weidu_shopping_demo.net.OkHttpCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    private static OkHttpUtils instance;
    private Handler handler;
    private OkHttpClient okHttpClient;

    private OkHttpUtils() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    public void doGet(String apiUrl, HashMap<String, String> params, final OkHttpCallback okHttpCallback) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> map : params.entrySet()) {
                stringBuilder.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }
        }

        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (okHttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.onFailed(e + "");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okHttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.onSuccess(result);
                        }
                    });
                }
            }
        });
    }

    public void doPost(String apiUrl, HashMap<String, String> params, final OkHttpCallback okHttpCallback) {

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, String> map : params.entrySet()) {
            builder.add(map.getKey(), map.getValue());
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (okHttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.onFailed(e + "");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okHttpCallback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.onSuccess(result);
                        }
                    });
                }
            }
        });

    }

}
