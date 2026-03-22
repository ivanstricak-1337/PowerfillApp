package com.powerfill.app
import retrofit2.http.*

data class Charger(val id: String, val status: String, val power: Double)
data class Session(val id: String, val energy: Double, var cost: Double? = null)
data class StartRequest(val chargerId: String, val connectorId: Int, val idTag: String)
data class StopRequest(val transactionId: String)

interface PowerfillApi {
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
