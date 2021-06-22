package com.example.flightgearcontroller.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flightgearcontroller.model.Model

class ControllerViewModel : ViewModel() {
    // observable liva data objects, saving the presented values for the seekbars and joystick
    var throttle: MutableLiveData<Int> = MutableLiveData(0);
    var rudder: MutableLiveData<Int> = MutableLiveData(0);
    var aileron: MutableLiveData<Float> = MutableLiveData(0F);
    var elevator: MutableLiveData<Float> = MutableLiveData(0F);

    init {
        // when one of the properties above is changed, send a string to the server
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

    // observable string for the connection status
    var connectionStatus: MutableLiveData<String>
        get() = Model.connectionStatus;
        set(value) {
            Model.connectionStatus.value = value.value;
        }

    // connect to the server
    fun connect(ip: String, port: Int) {
        Model.connectAsync(ip, port);
    }


}