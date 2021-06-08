package com.example.flightgearcontroller.view_model
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flightgearcontroller.Model
import com.example.flightgearcontroller.TcpClient

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