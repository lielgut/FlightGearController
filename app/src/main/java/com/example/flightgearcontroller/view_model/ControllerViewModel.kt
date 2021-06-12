package com.example.flightgearcontroller.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flightgearcontroller.model.Model

class ControllerViewModel : ViewModel() {
    var throttle: MutableLiveData<Int>
        get() = Model.throttle;
        set(value) {
            Model.throttle.value = value.value;
        }

    var rudder: MutableLiveData<Int>
        get() = Model.rudder;
        set(value) {
            Model.rudder.value = value.value;
        }

    var aileron: MutableLiveData<Float>
        get() = Model.aileron;
        set(value) {
            Model.aileron.value = value.value;
        }

    var elevator: MutableLiveData<Float>
        get() = Model.elevator;
        set(value) {
            Model.elevator.value = value.value;
        }

    var connectionStatus: MutableLiveData<String>
        get() = Model.connectionStatus;
        set(value) {
            Model.connectionStatus.value = value.value;
        }

    fun connect(ip: String, port: Int) {
        Model.connect(ip, port);
    }


}