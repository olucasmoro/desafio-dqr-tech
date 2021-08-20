package olucasmoro.android.desafiodqrtech.domain.repository

import androidx.lifecycle.LiveData
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Resource
import olucasmoro.android.desafiodqrtech.data.network.model.CurrencyDTO
import olucasmoro.android.desafiodqrtech.data.network.model.ExchangeDTO

interface AppRepositoryData {

    suspend fun getAllCurrencyApi()

    suspend fun getCurrenciesOrderByCode(): List<Currency>

    suspend fun searchCurrenciesOrderByName(field: String): List<Currency>

    suspend fun converter(currency: String, source: String, format: String): LiveData<Resource<ExchangeDTO>>

    fun getList(): LiveData<List<Currency>>
}