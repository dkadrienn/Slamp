package com.example.slampapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ConnectionActivity extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    ListView myListView;
    List<String> devicesName = new ArrayList<String>();
    List<String> devicesId = new ArrayList<String>();
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket btSocket = null, btSockets;
    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                //System.out.println(device.getName().toString() + ": " +  device.toString());
                devicesName.add(device.getName().toString());
                devicesId.add(device.toString());
            }
        } else {
            showToast("Turn on Bluetooth to get paired devices");
        }

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);


        ItemAdapter itemAdapter = new ItemAdapter(this, devicesName, devicesId);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println(position + " " + id);
                int counter = 0;
                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(devicesId.get(position));
                System.out.println(device.getName());

                do {
                    try {
                        btSocket = device.createRfcommSocketToServiceRecord(mUUID);
                        System.out.println(btSocket);
                        btSocket.connect();
                        globalClass.setBtSocket(btSocket);
                        //System.out.println(btSocket.isConnected());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    counter++;
                } while (!btSocket.isConnected() && counter < 3);

                if (btSocket.isConnected()) {
                    //System.out.println(btSocket);
                    showToast("Connected!");
                }
                else{
                    showToast("NOT Connected!");
                }

            }
        });
    }


    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}