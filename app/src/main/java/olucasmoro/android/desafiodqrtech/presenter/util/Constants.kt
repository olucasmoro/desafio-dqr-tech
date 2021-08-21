package olucasmoro.android.desafiodqrtech.presenter.util

class Constants private constructor() {

    object API {
        const val BASE_URL = "http://api.currencylayer.com/api/"
        const val KEY = "2b3016266ea3bb43a5ff14546026daea"
        const val ROUTE_LIST = "list"
        const val ROUTE_LIVE = "live"
    }
}

const val ID_SOURCE = "source"
const val ID_DESTINATION = "destination"
const val ERROR = "Falha na conexão com a API"
const val NO_NETWORK = "Falha na conexão com a internet"
const val SELECT_CURRENCY_SOURCE = "Escolha a moeda de origem"
const val SELECT_CURRENCY_DESTINATION = "Escolha a moeda de destino"
const val ENTER_THE_VALUE = "Informe o valor"
const val ERROR_CONVERT = "Erro na conversão"