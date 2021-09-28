package com.moringaschool.glamspace.network;

import static com.moringaschool.glamspace.Constants.UNSPLASH_BASE_URL;

import com.moringaschool.glamspace.models.ImageSearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashClient {
    private static Retrofit retrofit = null;
    public static  UnsplashPhotosApi getClient(){
        if(retrofit==null){
            retrofit  = new Retrofit.Builder()
                    .baseUrl(UNSPLASH_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UnsplashPhotosApi.class);
    }
}
