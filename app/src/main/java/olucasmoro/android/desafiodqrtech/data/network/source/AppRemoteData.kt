package olucasmoro.android.desafiodqrtech.data.network.source

import olucasmoro.android.desafiodqrtech.data.network.model.CurrencyDTO
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO

interface AppRemoteData {

    suspend fun getAllCurrencyApi(): CurrencyDTO

    suspend fun getConverter(
        currency: String,
        source: String,
        format: String,
        success: (converter: ExchangeDTO?) -> Unit,
        error: (error: String?) -> Unit
    )

}