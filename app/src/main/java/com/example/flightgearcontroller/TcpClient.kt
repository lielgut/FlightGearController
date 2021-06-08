package com.example.flightgearcontroller

import android.util.Log
import java.io.PrintWriter
import java.net.Socket

class TcpClient(ip : String, port : Int) {
    private var fg : Socket = Socket(ip, port);
    private var out = PrintWriter(fg.getOutputStream(),true);

    init {
        Log.v("TESTT","CREATED TCP CLIENTTTTTTTTTTTTTT");
    }

    fun send(s : String) {
        Log.v("TEST","SENDINGGGGGGGGGGGGGG $s");
        out.print(s + "\r\n");
        out.flush();
    }
}