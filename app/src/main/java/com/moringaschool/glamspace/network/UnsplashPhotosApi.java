package com.moringaschool.glamspace.network;

import com.moringaschool.glamspace.models.ImageSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashPhotosApi {
    @GET("search/photos")
    Call<ImageSearch>getImages(
          @Query("client_id")String client_id,
          @Query("query") String query,
          @Query("page") String page,
          @Query("per_page") String per_page
    );

}
