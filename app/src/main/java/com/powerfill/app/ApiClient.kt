package com.powerfill.app
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object Config {
    var BASE_URL = "https://YOUR-POWERFILL-URL/"
    var TOKEN: String? = null  // will be set after login

    var DAY_PRICE = 0.25
    var NIGHT_PRICE = 0.15

    var DAY_START = 8
    var DAY_END = 20
    var NIGHT_START = 21
    var NIGHT_END = 7
}

// Models for login
data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)

// Charger/session models
data class Charger(val id: String, val status: String, val power: Double)
data class Session(val id: String, val energy: Double, var cost: Double? = null)
data class StartRequest(val chargerId: String, val connectorId: Int, val idTag: String)
data class StopRequest(val transactionId: String)

interface PowerfillApi {
    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @GET("/chargers")
    suspend fun getChargers(@Header("Authorization") token: String): List<Charger>

    @GET("/sessions/active")
    suspend fun getSession(@Header("Authorization") token: String): Session?

    @POST("/start")
    suspend fun start(@Header("Authorization") token: String, @Body req: StartRequest)

    @POST("/stop")
    suspend fun stop(@Header("Authorization") token: String, @Body req: StopRequest)

    @GET("/sessions/history")
    suspend fun getPastSessions(@Header("Authorization") token: String): List<Session>
}

object ApiClient {
    val api: PowerfillApi = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PowerfillApi::class.java)
}
