package olucasmoro.android.desafiodqrtech.data.network.source

import olucasmoro.android.desafiodqrtech.data.network.ApiService
import olucasmoro.android.desafiodqrtech.data.network.model.CurrencyDTO
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO
import olucasmoro.android.desafiodqrtech.presenter.util.Constants.API.KEY
import olucasmoro.android.desafiodqrtech.presenter.util.ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRemoteDataImpl(
    private val apiService: ApiService
) : AppRemoteData {

    override suspend fun getAllCurrencyApi(): CurrencyDTO =
        apiService.getAllCurrency(KEY)

    private fun <T> execRequest(
        call: Call<T>,
        success: (ok: T?) -> Unit,
        error: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    error(ERROR)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                error(t.message)
            }
        })
    }

    override suspend fun getConverter(
        currency: String,
        source: String,
        format: String,
        success: (converter: ExchangeDTO?) -> Unit,
        error: (error: String?) -> Unit
    ) {
        execRequest(
            apiService.getConverter(KEY, currency, source, format),
            success,
            error

        )
    }

}