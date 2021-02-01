package com.breakingbad

import android.app.Application
import androidx.lifecycle.*
import com.breakingbad.network.SingleActor

class ActorDetailedViewModel(singleActor: SingleActor, app: Application) : AndroidViewModel(app) {

    private val _selectedActor = MutableLiveData<SingleActor>()

    val selectedActor: LiveData<SingleActor>
        get() = _selectedActor

    init {
        _selectedActor.value = singleActor
    }

}