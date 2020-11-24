package com.example.slampapp;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

public class GlobalClass extends Application {

    private BluetoothSocket btSocket;

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }
    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }

    public void writeNumber (BluetoothSocket btSocket, String numberString){
        byte[]number = new byte[numberString.length()]; // byte tomb a szam eltarolasahoz
        for ( int i = 0; i < numberString.length(); i++){ // a szamunk vegigjarasa szamjegyenkent (karakterenkent) es a byte tombe valo elhelyezese
            number[i] = (byte)numberString.charAt(i);
        }
        try { // A byte tombunk (szamunk) elkuldese a bluetooth modulnak
            OutputStream outputStream = btSocket.getOutputStream();
            outputStream.write(number, 0, number.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseAction(BluetoothSocket btSocket, int actionIdentification){
        if ( btSocket != null) {
            try { // Az adott effektnek megfelelo jelzes kuldese a bluetooth modulnak
                OutputStream outputStream = btSocket.getOutputStream();
                outputStream.write(actionIdentification);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
