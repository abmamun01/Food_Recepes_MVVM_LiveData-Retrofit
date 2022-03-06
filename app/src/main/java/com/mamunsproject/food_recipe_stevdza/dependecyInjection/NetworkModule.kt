package com.mamunsproject.food_recipe_stevdza.dependecyInjection

import com.mamunsproject.food_recipe_stevdza.utils.Constant.Companion.BASE_URL
import com.mamunsproject.food_recipe_stevdza.data.Network.FoodRecipesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    // ai function ta amader HILT library k bolche j kivabe ai Dependency r instance create korbo
    // SingleTon diya bujay j ai funciton r shudu akta instance hobe
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    // ai function ta amader HILT library k bolche j kivabe ai Dependency r instance create korbo

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    // To satisfy Retrofit dependency we need to provide OkHttp and Gson Conver
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Singleton
    //provide because we are using retrofit which we didn't create
    @Provides
    fun provideApiService(retrofit: Retrofit): FoodRecipesApi {
        return retrofit.create(FoodRecipesApi::class.java)
    }


}
