package com.baatsen.ietsnieuws.data.service

import com.baatsen.ietsnieuws.data.model.NewsJson
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface NewsService {
    @GET("v2/top-headlines?sources=rtl-nieuws")
    fun getNews(@Header("X-Api-Key") apiKey: String): Observable<NewsJson>
}