package com.example.flightgearcontroller

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.internal.ContextUtils.getActivity
import java.util.concurrent.ForkJoinPool

object Model {
    var pool : ForkJoinPool = ForkJoinPool(1,ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    var throttle : MutableLiveData<Int> = MutableLiveData<Int>(0)
    var rudder : MutableLiveData<Int> = MutableLiveData<Int>(0)
    var aileron : MutableLiveData<Float> = MutableLiveData<Float>(0F)
    var elevator : MutableLiveData<Float> = MutableLiveData<Float>(0F)
    var client : TcpClient? = null;

    init {
        throttle.observeForever {
            Log.v("THROTTLE","THROTTLE CHANGED TO $it");
            val floatVal : Float = it.toFloat() / 100F;
            send("set /controls/engines/current-engine/throttle $floatVal");
        }
        rudder.observeForever {
            Log.v("RUDDER","RUDDER CHANGED TO $it");
            val floatVal : Float = it.toFloat() / 50F - 1F;
            send("set /controls/flight/rudder $floatVal");
        }
        aileron.observeForever {
            Log.v("AILERON","AILERON CHANGED TO $it");
            send("set /controls/flight/aileron $it");
        }
        elevator.observeForever {
            Log.v("ELEVATOR","ELEVATOR CHANGED TO $it");
            send("set /controls/flight/elevator $it");
        }
    }

    fun connect(ip : String, port : Int) {
        try {
            pool.execute {
                client = TcpClient(ip, port);
            };
        } catch(e : InterruptedException) {
            Log.v("FAIL","FAILEEEEEDDD TASKKKKK");
        }
    }

    fun send(s : String) {
        try {
            pool.execute {
                client?.send(s);
            };
        } catch(e : InterruptedException) {
            Log.v("FAIL","FAILEEEEEDDD TASKKKKK");
        }
    }
}