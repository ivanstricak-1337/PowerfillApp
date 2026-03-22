package com.powerfill.app
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.util.*

class MainViewModel : ViewModel() {
    val power = MutableLiveData<Double>()
    val energy = MutableLiveData<Double>()
    val cost = MutableLiveData<Double>()
    val status = MutableLiveData<String>()
    val history = MutableLiveData<List<Session>>()

    private var lastStatus: String? = null

    private fun getTariff(): Double {
        val tz = TimeZone.getTimeZone("CET")
        val cal = Calendar.getInstance(tz)
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        return if (hour in Config.DAY_START..Config.DAY_END) Config.DAY_PRICE else Config.NIGHT_PRICE
    }

    fun refresh() {
        viewModelScope.launch {
            val charger = ApiClient.api.getChargers(Config.TOKEN).firstOrNull()
            val session = ApiClient.api.getSession(Config.TOKEN)
            charger?.let {
                power.value = it.power
                status.value = it.status
            }
            session?.let {
                energy.value = it.energy
                cost.value = it.energy * getTariff()
            }
            val past = ApiClient.api.getPastSessions(Config.TOKEN)
            history.value = past.map { it.copy(cost = it.energy * getTariff()) }
        }
    }

    fun start(id: String) = viewModelScope.launch { ApiClient.api.start(Config.TOKEN, StartRequest(id,1,"HOME1")) }
    fun stop(id: String) = viewModelScope.launch { ApiClient.api.stop(Config.TOKEN, StopRequest(id)) }

    init { viewModelScope.launch { while(true){ refresh(); delay(5000) } } }
}
