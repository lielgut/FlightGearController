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

// the main activity of the app
class MainActivity : AppCompatActivity() {
    // contains the view model
    private var vm = ControllerViewModel();
    // ip and port inputs
    private lateinit var ipInput : EditText;
    private lateinit var portInput : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set data binding
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.viewmodel = vm;
        binding.lifecycleOwner = this;

        // get the EditText views of ip and port
        ipInput = findViewById(R.id.ipInput);
        portInput = findViewById(R.id.portInput);

        // get the joystick view
        val joystick = findViewById<Joystick>(R.id.joystick);
        // set onChange event for joystick
        joystick.onChange = fun(a: Float, e: Float) {
            vm.aileron.value = a;
            vm.elevator.value = e;
        }
    }

    // connect to the server when connect button is clicked
    fun connectClicked(view : View) {
        // get the ip input
        val ip : String = ipInput.text.toString();
        try {
            // get the port input
            val port : Int = portInput.text.toString().toInt();
            // connect to the server
            vm.connect(ip, port);
        } catch(e : java.lang.NumberFormatException) {
            // show error if the input is invalid
            Toast.makeText(applicationContext,"invalid port number",Toast.LENGTH_SHORT).show();
        }

    }
}