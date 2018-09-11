package com.moducode.votest.dagger

import android.content.Context
import com.moducode.votest.schedulers.BaseSchedulerProvider
import com.moducode.votest.schedulers.SchedulerProvider
import com.moducode.votest.service.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class SchedulerModule{

    @Provides
    @Singleton
    fun provideSchedulers(): SchedulerProvider = BaseSchedulerProvider()

}


@Module(includes = [RetrofitModule::class])
class WeatherModule{

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): RetrofitService = retrofit.create(RetrofitService::class.java)

}

@Module(includes = [ContextModule::class])
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl("https://www.metaweather.com/api/")
                    .build()

    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor {Timber.d(it)}.apply { level = HttpLoggingInterceptor.Level.BASIC })
                    .cache(Cache(context.cacheDir, 5 * 5 * 1024))
                    .build()

}

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

}