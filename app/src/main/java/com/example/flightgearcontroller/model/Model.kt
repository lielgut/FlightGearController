package com.example.flightgearcontroller.model

import androidx.lifecycle.MutableLiveData
import java.lang.Exception
import java.util.concurrent.ForkJoinPool

// the model is responsible for asynchronously executing tasks with a tcp client
object Model {
    // threadpool with one thread for executing tasks asynchronously
    private var pool: ForkJoinPool =
        ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    // observable string for the connection status
    var connectionStatus : MutableLiveData<String> = MutableLiveData<String>("disconnected")
    // tcp client for connection to server
    private var client: TcpClient? = null;

    // send the throttle value to the server
    fun sendThrottle(value : Int) {
        // convert from integer in range [1,100] to float value in range [0,1]
        val floatVal: Float = value.toFloat() / 100F;
        // send string to server
        sendAsync("set /controls/engines/current-engine/throttle $floatVal");
    }

    // send the rudder value to the server
    fun sendRudder(value : Int) {
        // convert from integer in range [1,100] to float value in range [-1,1]
        val floatVal: Float = value.toFloat() / 50F - 1F;
        // send string to server
        sendAsync("set /controls/flight/rudder $floatVal");
    }

    // send the aileron value to the server
    fun sendAileron(value : Float) {
        // send string to server
        sendAsync("set /controls/flight/aileron $value");
    }

    // send the elevator value to the server
    fun sendElevator(value : Float) {
        // send string to server
        sendAsync("set /controls/flight/elevator $value");
    }

    // connect to a server with  given ip and port
    fun connectAsync(ip: String, port: Int) {
        try {
            // add task to the threadpool
            pool.execute {
                // initialize tcp client
                client = TcpClient();
                // connect at ip + port and update status
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

    // send a string to the server
    private fun sendAsync(s: String) {
        // add task to threadpool
        try {
            pool.execute {
                client?.send(s);
            }
        } catch (e: InterruptedException) {
        }
    }
}