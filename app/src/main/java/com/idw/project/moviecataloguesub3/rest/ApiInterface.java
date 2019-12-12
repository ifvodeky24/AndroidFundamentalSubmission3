package com.idw.project.moviecataloguesub3.rest;

import com.idw.project.moviecataloguesub3.response.ResponseMovie;
import com.idw.project.moviecataloguesub3.response.ResponseMovieDetail;
import com.idw.project.moviecataloguesub3.response.ResponseMovieVideos;
import com.idw.project.moviecataloguesub3.response.ResponseTv;
import com.idw.project.moviecataloguesub3.response.ResponseTvDetail;
import com.idw.project.moviecataloguesub3.response.ResponseTvVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<ResponseMovie> getMovie(
            @Query("api_key") String apiKey
    );

    @GET("tv/top_rated")
    Call<ResponseTv> getTv(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/videos")
    Call<ResponseMovieVideos> getMovieVideos(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}/videos")
    Call<ResponseTvVideos> getTvVideos(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<ResponseMovieDetail> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<ResponseTvDetail> getTvDetails(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey
    );
}
