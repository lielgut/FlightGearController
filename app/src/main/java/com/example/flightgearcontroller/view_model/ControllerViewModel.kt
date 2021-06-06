package com.example.flightgearcontroller.view_model
import androidx.lifecycle.ViewModel

class ControllerViewModel : ViewModel() {
    var aileron : Float = 0F;
    var elevator : Float = 0F;

    var throttle : Float = 0F
    set(value) {
        field = value;
        println("throttle changed to: $value");
    }
    var rudder : Float = 0F;
}