package com.example.slampapp;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

public class GlobalClass extends Application {

    private BluetoothSocket btSocket;

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }
    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }

}
