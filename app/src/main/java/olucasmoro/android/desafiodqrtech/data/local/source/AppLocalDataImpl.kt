package olucasmoro.android.desafiodqrtech.data.local.source

import olucasmoro.android.desafiodqrtech.data.local.AppDao
import olucasmoro.android.desafiodqrtech.data.local.model.Currency
import olucasmoro.android.desafiodqrtech.data.local.model.Exchange

class AppLocalDataImpl(
    private val appDao: AppDao
) : AppLocalData {

    override suspend fun insertAllCurrency(currencyList: List<Currency>) =
        appDao.insertAllCurrency(currencyList)

    override suspend fun searchCurrenciesOrderByName(field: String): List<Currency> =
        appDao.searchCurrenciesOrderByName(field)

    override suspend fun getCurrenciesOrderByCode(): List<Currency> =
        appDao.getCurrenciesOrderByCode()

    override suspend fun insertAllExchange(exchangeList: List<Exchange>) =
        appDao.insertAllExchange(exchangeList)

}