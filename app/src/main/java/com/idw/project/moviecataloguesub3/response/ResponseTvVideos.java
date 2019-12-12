package com.idw.project.moviecataloguesub3.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idw.project.moviecataloguesub3.model.TvVideos;

public class ResponseTvVideos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<TvVideos> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TvVideos> getResults() {
        return results;
    }

    public void setResults(List<TvVideos> results) {
        this.results = results;
    }

}
