package com.example.flightgearcontroller.model

import androidx.lifecycle.MutableLiveData
import java.lang.Exception
import java.util.concurrent.ForkJoinPool

object Model {
    private var pool: ForkJoinPool =
        ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    var throttle: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var rudder: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var aileron: MutableLiveData<Float> = MutableLiveData<Float>(0F)
    var elevator: MutableLiveData<Float> = MutableLiveData<Float>(0F)
    var connectionStatus : MutableLiveData<String> = MutableLiveData<String>("disconnected")
    private var client: TcpClient? = null;

    init {
        throttle.observeForever {
            val floatVal: Float = it.toFloat() / 100F;
            send("set /controls/engines/current-engine/throttle $floatVal");
        }
        rudder.observeForever {
            val floatVal: Float = it.toFloat() / 50F - 1F;
            send("set /controls/flight/rudder $floatVal");
        }
        aileron.observeForever {
            send("set /controls/flight/aileron $it");
        }
        elevator.observeForever {
            send("set /controls/flight/elevator $it");
        }
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