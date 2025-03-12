package com.example.miertkelleztafeladatotcsinalni;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIInstance {
    public static final String BASE_URL = "https://retoolapi.dev/05kYKz/books/";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
