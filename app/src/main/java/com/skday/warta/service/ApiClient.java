package com.skday.warta.service;

import com.skday.warta.model.ArticlesDao;
import com.skday.warta.model.SourcesDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by skday on 4/18/17.
 */

public interface ApiClient {
    @GET("articles?apiKey="+Service.API_KEY)
    Call<ArticlesDao> getArticles(
            @Query("source") String source
    );

    @GET("sources?language=en")
    Call<SourcesDao> getSources();
}
