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
import com.idw.project.moviecataloguesub3.config.Config;
import com.idw.project.moviecataloguesub3.activity.DetailTvActivity;
import com.idw.project.moviecataloguesub3.model.Tv;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private Context context;

    private ArrayList<Tv> dataList;
    public static final String TAG = TvAdapter.class.getSimpleName();

    public TvAdapter(Context context, ArrayList<Tv> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.tv_item, viewGroup, false);

        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder tvViewHolder, final int i) {

        final Tv Item = dataList.get(i);

        Picasso.with(context)
                .load(Config.IMAGE_W342+Item.getPosterPath())
                .fit()
                .centerCrop()
                .into(tvViewHolder.iv_poster_tv);

        tvViewHolder.tv_judul_tv.setText(Item.getOriginalName());
        tvViewHolder.tv_tanggal_rilis_tv.setText("Rilis: "+Item.getFirstAirDate());
        tvViewHolder.tv_deskripsi_tv.setText(Item.getOverview());
        tvViewHolder.tv_rb_tv.setText(String.valueOf(Item.getVoteAverage()));
        tvViewHolder.rb_tv.setRating(Float.parseFloat(String.valueOf(Item.getVoteAverage())));

        tvViewHolder.cv_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.TAG, Item);
                System.out.println("id nya adalah"+ Item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() :0;
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {


        CircleImageView iv_poster_tv;
        TextView tv_judul_tv, tv_tanggal_rilis_tv, tv_deskripsi_tv, tv_rb_tv;
        CardView cv_tv;
        RatingBar rb_tv;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster_tv = itemView.findViewById(R.id.iv_poster_tv);
            tv_judul_tv = itemView.findViewById(R.id.tv_judul_tv);
            tv_tanggal_rilis_tv = itemView.findViewById(R.id.tv_tanggal_rilis_tv);
            rb_tv = itemView.findViewById(R.id.rb_tv);
            tv_rb_tv = itemView.findViewById(R.id.tv_rb_tv);
            tv_deskripsi_tv = itemView.findViewById(R.id.tv_deskripsi_tv);
            cv_tv = itemView.findViewById(R.id.cv_tv);
        }
    }
}

