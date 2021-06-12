package com.example.flightgearcontroller.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.flightgearcontroller.R
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
            this, R.layout.activity_main
        )
        binding.viewmodel = vm;
        binding.lifecycleOwner = this;

        ipInput = findViewById(R.id.ipInput);
        portInput = findViewById(R.id.portInput);

        val joystick = findViewById<Joystick>(R.id.joystick);
        val joystickChange = fun(a: Float, e: Float) : Unit {
            vm.aileron = MutableLiveData<Float>(a);
            vm.elevator = MutableLiveData<Float>(e);
        }
        joystick.onChange = joystickChange;
    }

    fun connectClicked(view : View) {
        val ip : String = ipInput.text.toString();
        try {
            val port : Int = portInput.text.toString().toInt();
            vm.connect(ip, port);
        } catch(e : java.lang.NumberFormatException) {
            Toast.makeText(applicationContext,"invalid port number",Toast.LENGTH_SHORT).show();
        }

    }
}