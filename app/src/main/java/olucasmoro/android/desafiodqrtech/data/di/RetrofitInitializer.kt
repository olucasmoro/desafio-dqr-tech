package olucasmoro.android.desafiodqrtech.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import olucasmoro.android.desafiodqrtech.data.network.ApiService
import olucasmoro.android.desafiodqrtech.presenter.util.Constants.API.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
    try {
        single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
    } catch (e: Exception) {

    }
}