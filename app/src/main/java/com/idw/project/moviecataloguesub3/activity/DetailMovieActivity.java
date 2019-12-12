package com.idw.project.moviecataloguesub3.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.idw.project.moviecataloguesub3.MainViewModel;
import com.idw.project.moviecataloguesub3.R;
import com.idw.project.moviecataloguesub3.config.Config;
import com.idw.project.moviecataloguesub3.model.Genre;
import com.idw.project.moviecataloguesub3.model.Movie;
import com.idw.project.moviecataloguesub3.response.ResponseMovieDetail;
import com.idw.project.moviecataloguesub3.rest.ApiClient;
import com.idw.project.moviecataloguesub3.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String TAG = "movie";

    Integer id;
    String genre, title, tv_rb, tv_tanggal_rilis_string, tv_deskripsi_string, key;
    float rb;

    ApiInterface apiService;

    ImageView iv_poster_movie, iv_poster_depan_movie;
    TextView tv_judul_movie_detail, tv_rb_tv, tv_genre, tv_tanggal_rilis, tv_deskripsi;
    RatingBar rb_tv;

    private static final String API_KEY= Config.API_KEY;

    private MainViewModel mainViewModel;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Detail Movie");
        setContentView(R.layout.activity_detail_movie);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        iv_poster_movie = findViewById(R.id.iv_poster_movie);
        iv_poster_depan_movie = findViewById(R.id.iv_poster_depan_movie);
        tv_judul_movie_detail = findViewById(R.id.tv_judul_movie_detail);
        tv_genre = findViewById(R.id.tv_genre);
        tv_tanggal_rilis = findViewById(R.id.tv_tanggal_rilis);
        tv_deskripsi = findViewById(R.id.tv_deskripsi);
        tv_rb_tv = findViewById(R.id.tv_rb_tv);
        rb_tv = findViewById(R.id.rb_tv);
        webView = findViewById(R.id.mWebView);

        final Movie movie = getIntent().getExtras().getParcelable(TAG);

        System.out.println("id nya sekarang"+ movie.getId());
        System.out.println("judul nya adalah"+ movie.getTitle());
        System.out.println("tanggal rilisnya nya adalah"+ movie.getReleaseDate());
        System.out.println("deskripsi nya adalah"+ movie.getOverview());

        //get Id
        id = movie.getId();

        System.out.println("id now"+id);

        iv_poster_depan_movie.bringToFront();

        apiService.getMovieDetails(id, API_KEY).enqueue(new Callback<ResponseMovieDetail>() {
            @Override
            public void onResponse(Call<ResponseMovieDetail> call, Response<ResponseMovieDetail> response) {
                System.out.println("response detail"+response);
                if (response.isSuccessful()){
                    List<Genre> genres = response.body().getGenres();
                    genre = String.valueOf(genres.get(0).getName());
                    System.out.println("genre"+genre);

                    tv_genre.setText(genre);

                }else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseMovieDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });

        title = movie.getTitle();
        rb = Float.parseFloat(String.valueOf(movie.getVoteAverage()));
        tv_rb = String.valueOf(movie.getVoteAverage());
        tv_tanggal_rilis_string = movie.getReleaseDate();
        tv_deskripsi_string = movie.getOverview();

        tv_judul_movie_detail.setText(title);
        rb_tv.setRating(rb);
        tv_rb_tv.setText(tv_rb);
        tv_tanggal_rilis.setText(tv_tanggal_rilis_string);
        tv_deskripsi.setText(tv_deskripsi_string);
        Picasso.with(DetailMovieActivity.this)
                .load(Config.IMAGE_W500+movie.getBackdropPath())
                .transform(new BlurTransformation(getApplicationContext(), 5,1))
                .fit()
                .centerCrop()
                .into(iv_poster_movie);

        Picasso.with(DetailMovieActivity.this)
                .load(Config.IMAGE_W500+movie.getPosterPath())
                .fit()
                .centerCrop()
                .into(iv_poster_depan_movie);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getListMovieVideos(String.valueOf(id)).observe(this, getListMovieVideo);

    }

    private Observer<String> getListMovieVideo = new Observer<String>() {
        @Override
        public void onChanged(String movies) {
            if (movies != null){

                String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"320\" height=\"250\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

                System.out.println("cekPath11"+movies);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });

                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadData(frameVideo, "text/html", "utf-8");
                webView.loadUrl(movies);
            }
        }
    };

}
