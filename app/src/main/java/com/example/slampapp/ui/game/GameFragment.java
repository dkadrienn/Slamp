package com.example.slampapp.ui.game;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slampapp.GlobalClass;
import com.example.slampapp.R;

import java.io.IOException;
import java.io.OutputStream;

public class GameFragment extends Fragment {

    private GameViewModel gameViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        BluetoothSocket btSocket = globalClass.getBtSocket();
        EditText numberOfPlayersEditText = (EditText) root.findViewById(R.id.numberOfPlayersEditText);
        EditText timersValueTextEdit = (EditText) root.findViewById(R.id.timersValueEditText);
        Button startBtn = (Button) root.findViewById(R.id.startBtn);
        Button timerBtn = (Button) root.findViewById(R.id.timerBtn);
        Button exitBtn = (Button) root.findViewById(R.id.exitBtn);
        ImageButton rollBtn = (ImageButton) root.findViewById(R.id.rollBtn);
        ImageButton skipBtn = (ImageButton) root.findViewById(R.id.skipBtn);
        ImageButton rollAgainBtn = (ImageButton) root.findViewById(R.id.rollAgainBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 48); // '0' karakter kuldese az elozo effekt befejezese vegett
                globalClass.chooseAction(btSocket, 107); // 'k' karakter kuldese -> dice effekt elinditas
                String numberOfPlayersString = numberOfPlayersEditText.getText().toString(); // A jatekosok szamanak kiolvasasa
                globalClass.writeNumber(btSocket, numberOfPlayersString); // A jatekosok szamanak elkuldese
            }
        });

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 108); //'l' karakter kuldese -> timer effekt inditasa
                String timersValueString = timersValueTextEdit.getText().toString(); // timer ertekenek kiolvasasa
                globalClass.writeNumber(btSocket, timersValueString); // A timer ertekenek az elkuldese
            }
        });

        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 49); // '1' karakter kuldes -> dobas
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 50); // '2' karakter kuldese -> kimaradas
            }
        });

        rollAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 54); // '6' karakter kuldese -> ujradobas
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket, 48); // '0' karakter kuldese -> kimaradas
            }
        });

        return root;
    }
}