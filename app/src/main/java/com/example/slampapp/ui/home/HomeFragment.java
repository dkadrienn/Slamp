package com.example.slampapp.ui.home;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slampapp.ConnectionActivity;
import com.example.slampapp.GlobalClass;
import com.example.slampapp.R;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SwitchCompat mySwitch = root.findViewById(R.id.mySwitch);
        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();

        mySwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){ //Parositott eszkozok listajanak megnyitasa
                    Intent startIntent = new Intent( getActivity().getApplicationContext(), ConnectionActivity.class);
                    startActivity(startIntent);
                }
                else{
                    BluetoothSocket btSocket = globalClass.getBtSocket();
                    try {
                        btSocket.close(); //Bluetooth kapcsolat megszakitasa
                        showToast("Disconnect");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return root;
        }

    private void showToast(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
