package com.idw.project.moviecataloguesub3;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.idw.project.moviecataloguesub3.config.Config;
import com.idw.project.moviecataloguesub3.model.Movie;
import com.idw.project.moviecataloguesub3.model.MovieVideos;
import com.idw.project.moviecataloguesub3.model.Tv;
import com.idw.project.moviecataloguesub3.model.TvVideos;
import com.idw.project.moviecataloguesub3.response.ResponseMovie;
import com.idw.project.moviecataloguesub3.response.ResponseMovieVideos;
import com.idw.project.moviecataloguesub3.response.ResponseTv;
import com.idw.project.moviecataloguesub3.response.ResponseTvVideos;
import com.idw.project.moviecataloguesub3.rest.ApiClient;
import com.idw.project.moviecataloguesub3.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private ArrayList<Movie> MovieArrayList = new ArrayList<>();
    private ArrayList<Tv> TvArrayList = new ArrayList<>();

    ApiInterface apiService;

    private static final String API_KEY= Config.API_KEY;
    public static final String URL_VIDEO = Config.VIDEO_URL;

    //bagian movie

    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();

    public void setListMovie(){
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getMovie(API_KEY).enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                System.out.println("responseIn"+response);
                if (response.isSuccessful()){
                    System.out.println("responseSize"+response.body().getResults());

                    if (response.body().getResults().size()>0){
                        MovieArrayList.addAll(response.body().getResults());
                        System.out.println(response.body().getResults().get(0).getId());
                        listMovie.postValue(MovieArrayList);

                    }else {
                        Log.d("response", String.valueOf(response));
                    }

                }else{
                    Log.d("response", String.valueOf(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getListMovies() {
        setListMovie();
        return listMovie;
    }

    //bagian tv

    private MutableLiveData<ArrayList<Tv>> listTv = new MutableLiveData<>();

    public void setListTv(){
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getTv(API_KEY).enqueue(new Callback<ResponseTv>() {
            @Override
            public void onResponse(Call<ResponseTv> call, Response<ResponseTv> response) {
                System.out.println("responseIn"+response);
                if (response.isSuccessful()){
                    System.out.println("responseSize"+response.body().getResults());

                    if (response.body().getResults().size()>0){
                        TvArrayList.addAll(response.body().getResults());
                        System.out.println(response.body().getResults().get(0).getId());
                        listTv.postValue(TvArrayList);

                    }else {
                        Log.d("response", String.valueOf(response));
                    }

                }else{
                    Log.d("response", String.valueOf(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseTv> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Tv>> getListTv() {
        setListTv();
        return listTv;
    }

    //bagian movie video

    private MutableLiveData<String> StringMovieVideo = new MutableLiveData<>();

    public void setListMovieVideo(final String id){
        System.out.println("cekId"+ id);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getMovieVideos(Integer.parseInt(id), API_KEY).enqueue(new Callback<ResponseMovieVideos>() {
            @Override
            public void onResponse(Call<ResponseMovieVideos> call, Response<ResponseMovieVideos> response) {
                System.out.println("responseIn"+response);
                if (response.isSuccessful()){
                    System.out.println("responseSize"+response.body().getResults());

                    if (response.body().getResults().size()>0){
                        System.out.println("testing"+ id);
                        List<MovieVideos> movieVideos = response.body().getResults();
                        String key = movieVideos.get(0).getKey();
                        System.out.println("cek_key"+key);

                        String path = URL_VIDEO+key;
                        System.out.println("cekPath"+ path);

                        StringMovieVideo.postValue(path);

                    }else {
                        Log.d("response", String.valueOf(response));
                    }

                }else{
                    Log.d("response", String.valueOf(response));
                }
            }
            @Override
            public void onFailure(Call<ResponseMovieVideos> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", t.getMessage());
            }
        });
    }

    public LiveData<String> getListMovieVideos(String id) {
        setListMovieVideo(id);
        return StringMovieVideo;
    }

    //bagian tv video

    private MutableLiveData<String> StringTvVideo = new MutableLiveData<>();

    public void setListTvVideo(final String id){
        System.out.println("cekId"+ id);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.getTvVideos(Integer.parseInt(id), API_KEY).enqueue(new Callback<ResponseTvVideos>() {
            @Override
            public void onResponse(Call<ResponseTvVideos> call, Response<ResponseTvVideos> response) {
                System.out.println("responseIn"+response);
                if (response.isSuccessful()){
                    System.out.println("responseSize"+response.body().getResults());

                    if (response.body().getResults().size()>0){
                        System.out.println("testing"+ id);
                        List<TvVideos> tvVideos = response.body().getResults();
                        String key = tvVideos.get(0).getKey();
                        System.out.println("cek_key"+key);

                        String path = URL_VIDEO+key;
                        System.out.println("cekPath"+ path);

                        StringTvVideo.postValue(path);

                    }else {
                        Log.d("response", String.valueOf(response));
                    }

                }else{
                    Log.d("response", String.valueOf(response));
                }
            }
            @Override
            public void onFailure(Call<ResponseTvVideos> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", t.getMessage());
            }
        });
    }

    public LiveData<String> getListTvVideos(String id) {
        setListTvVideo(id);
        return StringTvVideo;
    }

}
