package com.breakingbad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.breakingbad.network.ActorApi
import com.breakingbad.network.SingleActor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorsListViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _status = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _status

    private val _actors = MutableLiveData<List<SingleActor>>()

    val actors: LiveData<List<SingleActor>>
        get() = _actors

    private val _navigateToSelectedProperty = MutableLiveData<SingleActor>()

    val navigateToSelectedProperty: LiveData<SingleActor>
        get() = _navigateToSelectedProperty

    fun displayActorDetails(singleActor: SingleActor) {
        _navigateToSelectedProperty.value = singleActor
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    init {
        getActors()
    }

    private fun getActors() {
        coroutineScope.launch {
            var getPropertiesDeferred = ActorApi.retrofitService.getActors()
            try {
                var listResult = getPropertiesDeferred.await()
                _status.value = "Success: ${listResult.size} Number of actors retrieved"
                if (listResult.size > 0) {
                    _actors.value = listResult
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}