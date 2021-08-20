package olucasmoro.android.desafiodqrtech.data.network

import olucasmoro.android.desafiodqrtech.data.network.model.CurrencyDTO
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO
import olucasmoro.android.desafiodqrtech.presenter.util.Constants.API.ROUTE_LIST
import olucasmoro.android.desafiodqrtech.presenter.util.Constants.API.ROUTE_LIVE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ROUTE_LIST)
    suspend fun getAllCurrency(
        @Query("access_key") userKey: String
    ): CurrencyDTO

    @GET(ROUTE_LIVE)
    fun getConverter(
        @Query("access_key") key: String,
        @Query("currencies") currency: String,
        @Query("source") source: String,
        @Query("format") format: String
    ): Call<ExchangeDTO>

}