package com.example.flightgearcontroller.model

import androidx.lifecycle.MutableLiveData
import java.lang.Exception
import java.util.concurrent.ForkJoinPool

object Model {
    private var pool: ForkJoinPool =
        ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    var connectionStatus : MutableLiveData<String> = MutableLiveData<String>("disconnected")
    private var client: TcpClient? = null;

    fun sendThrottle(value : Int) {
        val floatVal: Float = value.toFloat() / 100F;
        send("set /controls/engines/current-engine/throttle $floatVal");
    }

    fun sendRudder(value : Int) {
        val floatVal: Float = value.toFloat() / 50F - 1F;
        send("set /controls/flight/rudder $floatVal");
    }

    fun sendAileron(value : Float) {
        send("set /controls/flight/aileron $value");
    }

    fun sendElevator(value : Float) {
        send("set /controls/flight/elevator $value");
    }

    fun connect(ip: String, port: Int) {
        try {
            pool.execute {
                client = TcpClient();
                if(client!!.connect(ip, port)) {
                    connectionStatus.postValue("connected");
                } else {
                    connectionStatus.postValue("connection failed");
                }
            };
        } catch (e: Exception) {
            connectionStatus.postValue("connection failed");
        }
    }

    private fun send(s: String) {
        try {
            pool.execute {
                client?.send(s);
            }
        } catch (e: InterruptedException) {
        }
    }
}