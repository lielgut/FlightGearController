package com.example.flightgearcontroller

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.flightgearcontroller.databinding.ActivityMainBinding
import com.example.flightgearcontroller.view_model.ControllerViewModel

class MainActivity : AppCompatActivity() {
    private var vm = ControllerViewModel();
    private lateinit var ipInput : EditText;
    private lateinit var portInput : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.viewmodel = vm;
        binding.lifecycleOwner = this;

        ipInput = findViewById(R.id.ipInput);
        portInput = findViewById(R.id.portInput);

        var joystick = findViewById<Joystick>(R.id.joystick);
        var joystickChange = fun(a: Float, e: Float) : Unit {
            Log.v("CHANGE","CHANGED JOYSTICK!!!! ($a,$e)");
            vm.aileron = MutableLiveData<Float>(a);
            vm.elevator = MutableLiveData<Float>(e);
        }
        joystick.onChange = joystickChange;
    }

    fun connectClicked(view : View) {
        val ip : String = ipInput.text.toString();
        val port : Int = portInput.text.toString().toInt();
        Toast.makeText(applicationContext,"connecting - ip: $ip, port: $port",Toast.LENGTH_SHORT).show();
        vm.connect(ip,port);
    }

    fun testClicked(view : View) {
        Toast.makeText(applicationContext,"throttle: ${Model.throttle.value}, rudder: ${Model.rudder.value}",Toast.LENGTH_SHORT).show();
    }
}