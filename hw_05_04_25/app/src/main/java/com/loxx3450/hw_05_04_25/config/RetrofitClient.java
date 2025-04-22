package com.loxx3450.hw_05_04_25.config;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.loxx3450.hw_05_04_25.BuildConfig;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY_QUERY_PARAM = "api_key";

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        HttpUrl originalUrl = chain.request().url();

                        HttpUrl url = originalUrl.newBuilder()
                                .addQueryParameter(API_KEY_QUERY_PARAM, BuildConfig.TMDB_API_KEY)
                                .build();

                        Request request = chain.request().newBuilder().url(url).build();
                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
