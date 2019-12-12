package com.idw.project.moviecataloguesub3.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.idw.project.moviecataloguesub3.MainViewModel;
import com.idw.project.moviecataloguesub3.R;
import com.idw.project.moviecataloguesub3.adapter.TvAdapter;
import com.idw.project.moviecataloguesub3.model.Tv;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter adapter;
    private ArrayList<Tv> TvArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;
    private MainViewModel TvViewModel;

    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        TvViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        TvViewModel.getListTv().observe(getActivity(), getListTv);

        initView(view);
        return view;
    }

    //fungsi saat layar diswipe keatas
    private void initView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorAccent);

        refresh();

    }

    private void refresh() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        TvArrayList.clear();
                        TvViewModel.setListTv();
                    }
                }, 1000);
            }
        });

    }

    private Observer<ArrayList<Tv>> getListTv = new Observer<ArrayList<Tv>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Tv> tvs) {
            if (tvs != null){
                adapter = new TvAdapter(getContext(),tvs);
                adapter.notifyDataSetChanged();

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmerAnimation();

    }

}
