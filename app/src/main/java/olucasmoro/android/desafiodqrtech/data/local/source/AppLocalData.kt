package olucasmoro.android.desafiodqrtech.data.local.source

import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Exchange

interface AppLocalData {

    suspend fun insertAllCurrency(currencyList: List<Currency>)

    suspend fun searchCurrenciesOrderByName(field: String): List<Currency>

    suspend fun getCurrenciesOrderByCode(): List<Currency>

    suspend fun insertAllExchange(exchangeList: List<Exchange>)

}