package com.example.flightgearcontroller.model

import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

// class for connecting to a socket with tcp
class TcpClient {
    // socket for connection
    private var fg: Socket? = null;
    // PrintWriter for sending strings to server
    private var out: PrintWriter? = null;

    // connect to ip and port and return if succeeded
    fun connect(ip: String, port: Int): Boolean {
        return try {
            // try creating a socket
            fg = Socket(ip, port);
            // get an output stream
            out = PrintWriter(fg!!.getOutputStream(), true);
            true;
        } catch (e: Exception) {
            false;
        }
    }

    // send a string with the printwriter
    fun send(s: String) {
        out?.print(s + "\r\n");
        out?.flush();
    }
}