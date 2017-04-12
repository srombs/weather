package com.sample.srombs.weather.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by srombs on 4/11/17.
 */

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl url =  request.url().newBuilder().addQueryParameter("APPID", "60cd6928576e8b913b16da027feac101").build();
        request = request.newBuilder().url(url).build();

        return chain.proceed(request);

    }
}
