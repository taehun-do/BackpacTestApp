package kr.backpac.test.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kr.backpac.test.common.Define
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network private constructor() {
    companion object {
        private val instance = Network()

        @Synchronized
        private fun getInstance(): Network {
            return instance
        }

        fun getWeatherApi(): WeatherApi {
            return getInstance().weatherApi
        }

        fun <T> request(disposable: CompositeDisposable, single: Single<T>, onSuccess: Consumer<T>) {
            disposable.add(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onSuccess))
        }
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Define.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient().newBuilder().apply {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    connectTimeout(Define.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
                    writeTimeout(Define.TIME_OUT_WRITE, TimeUnit.SECONDS)
                    readTimeout(Define.TIME_OUT_READ, TimeUnit.SECONDS)
                }.build())
                .build()
    }
    private val weatherApi: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

}