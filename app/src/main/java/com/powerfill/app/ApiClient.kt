package com.powerfill.app
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object Config {
    var BASE_URL = "https://ivan-fw46.powerfill.app"
    var TOKEN: String? = null // Will be set after login

    var DAY_PRICE = 0.25
    var NIGHT_PRICE = 0.15

    var DAY_START = 8
    var DAY_END = 20
    var NIGHT_START = 21
    var NIGHT_END = 7
}

object ApiClient {
    val api: PowerfillApi = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PowerfillApi::class.java)
}
