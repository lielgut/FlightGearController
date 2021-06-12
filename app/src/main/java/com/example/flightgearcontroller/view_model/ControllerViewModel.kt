package com.example.flightgearcontroller.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flightgearcontroller.model.Model

class ControllerViewModel : ViewModel() {
    var throttle : MutableLiveData<Int>
    get() = Model.throttle;
    public set(value) {
        Model.throttle.value = value.value;
    }

    var rudder : MutableLiveData<Int>
        get() = Model.rudder;
        public set(value) {
            Model.rudder.value = value.value;
        }

    var aileron : MutableLiveData<Float>
    get() = Model.aileron;
    public set(value) {
        Model.aileron.value = value.value;
    }

    var elevator : MutableLiveData<Float>
        get() = Model.elevator;
        public set(value) {
            Model.elevator.value = value.value;
        }

    fun connect(ip : String, port : Int) {
        Model.connect(ip, port);
    }


}