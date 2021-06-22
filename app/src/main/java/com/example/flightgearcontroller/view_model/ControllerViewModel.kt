package com.example.flightgearcontroller.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flightgearcontroller.model.Model

class ControllerViewModel : ViewModel() {
    var throttle: MutableLiveData<Int> = MutableLiveData(0);
    var rudder: MutableLiveData<Int> = MutableLiveData(0);
    var aileron: MutableLiveData<Float> = MutableLiveData(0F);
    var elevator: MutableLiveData<Float> = MutableLiveData(0F);

    init {
        throttle.observeForever {
            Model.sendThrottle(it);
        }
        rudder.observeForever {
            Model.sendRudder(it);
        }
        aileron.observeForever {
            Model.sendAileron(it);
        }
        elevator.observeForever {
            Model.sendElevator(it);
        }
    }

    var connectionStatus: MutableLiveData<String>
        get() = Model.connectionStatus;
        set(value) {
            Model.connectionStatus.value = value.value;
        }

    fun connect(ip: String, port: Int) {
        Model.connectAsync(ip, port);
    }


}