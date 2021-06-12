package com.example.flightgearcontroller.model

import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

class TcpClient {
    private var fg : Socket? = null;
    private var out : PrintWriter? = null;

    fun connect(ip : String, port : Int) : Boolean {
        return try {
            fg = Socket(ip, port);
            out = PrintWriter(fg!!.getOutputStream(),true);
            true;
        } catch (e : Exception) {
            false;
        }
    }

    fun send(s : String) {
        out?.print(s + "\r\n");
        out?.flush();
    }
}