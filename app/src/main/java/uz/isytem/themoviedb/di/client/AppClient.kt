package uz.isytem.themoviedb.di.client

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.isytem.themoviedb.utills.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object AppClient {
    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        @[Provides Singleton]
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @[Provides Singleton]
        fun provideOkHttp(chuckerInterceptor: ChuckerInterceptor , interceptor: Interceptor): OkHttpClient {
            return OkHttpClient
                .Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(interceptor)
                .build()
        }

        @[Provides Singleton]
        fun provideChuckerInterceptor(
            collector: ChuckerCollector,
            @ApplicationContext context: Context
        ): ChuckerInterceptor {

            return ChuckerInterceptor.Builder(context)
                .collector(collector)
                .maxContentLength(250_000L)
                .redactHeaders("Auth-Token", "Bearer")
                .alwaysReadResponseBody(true)
                .build()
        }

        @[Provides Singleton]

        fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
            return ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
        }

        @[Provides Singleton]
        fun provideGson(): Gson {
            return GsonBuilder().setLenient().create()
        }

        @[Provides Singleton]
        fun interception(): Interceptor {

            return Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val builder: Request.Builder = request.newBuilder()
                builder
                    .header("Connection", "close")
                    .addHeader("Content-type", "application/json")
                    .addHeader("Authorization" , "Bearer eyJhdWQiOiI3YTFkYzVkZTRhNjllM2MxZWY1OWYzOTBlNTM5NmUxOSIsInN1YiI6IjY0ZmFmMmNmZGI0ZWQ2MTAzNjNjYTZiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ztzcoOr__lRsa7pyduXUqVZbYtJWrOiukA-q5iOrjjo")
                    .addHeader("api_key","7a1dc5de4a69e3c1ef59f390e5396e19")
                val response = chain.proceed(builder.build())
                response
            }
        }
    }
}