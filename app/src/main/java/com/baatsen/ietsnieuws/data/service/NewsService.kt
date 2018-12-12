package com.baatsen.ietsnieuws.data.service

import com.baatsen.ietsnieuws.data.model.NewsJson
import com.baatsen.ietsnieuws.data.model.SourcesJson
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getNews(@Header("X-Api-Key") apiKey: String, @Query("sources") source: String): Observable<NewsJson>

    @GET("v2/sources")
    fun getSources(@Header("X-Api-Key") apiKey: String): Observable<SourcesJson>
}