package com.example.youtubeapi.data.di

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.service.YouTubeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        provideLoggingInterceptor()
    }
    single {
        provideOkHttpClient(get())
    }
    factory {
        provideRetrofit(get())
    }
    factory {
        provideYouTubeApiService(get())
    }

}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).build()


fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
):OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()


fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

fun provideYouTubeApiService(
    retrofit: Retrofit
):YouTubeApiService = retrofit.create(YouTubeApiService::class.java)
