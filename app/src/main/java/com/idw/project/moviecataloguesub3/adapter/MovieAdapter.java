package com.idw.project.moviecataloguesub3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.idw.project.moviecataloguesub3.R;
import com.idw.project.moviecataloguesub3.activity.DetailMovieActivity;
import com.idw.project.moviecataloguesub3.config.Config;
import com.idw.project.moviecataloguesub3.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    private ArrayList<Movie> dataList;
    public static final String TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Context context, ArrayList<Movie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.movie_item, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, final int i) {

        final Movie Item = dataList.get(i);
        Picasso.with(context)
                .load(Config.IMAGE_W342+Item.getPosterPath())
                .fit()
                .centerCrop()
                .into(movieViewHolder.iv_poster_movie);

        movieViewHolder.tv_judul_movie.setText(Item.getTitle());
        movieViewHolder.tv_tanggal_rilis_movie.setText("Rilis: "+Item.getReleaseDate());
        movieViewHolder.tv_deskripsi_movie.setText(Item.getOverview());
        movieViewHolder.tv_rb_movie.setText(String.valueOf(Item.getVoteAverage()));
        movieViewHolder.rb_movie.setRating(Float.parseFloat(String.valueOf(Item.getVoteAverage())));

        movieViewHolder.cv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.TAG, Item);
                System.out.println("id nya adalah"+ Item.getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_poster_movie;
        TextView tv_judul_movie, tv_tanggal_rilis_movie, tv_deskripsi_movie, tv_rb_movie;
        CardView cv_movie;
        RatingBar rb_movie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster_movie = itemView.findViewById(R.id.iv_poster_movie);
            tv_judul_movie = itemView.findViewById(R.id.tv_judul_movie);
            tv_tanggal_rilis_movie = itemView.findViewById(R.id.tv_tanggal_rilis_movie);
            rb_movie = itemView.findViewById(R.id.rb_movie);
            tv_rb_movie = itemView.findViewById(R.id.tv_rb_movie);
            tv_deskripsi_movie = itemView.findViewById(R.id.tv_deskripsi_movie);
            cv_movie = itemView.findViewById(R.id.cv_movie);
        }
    }

}
