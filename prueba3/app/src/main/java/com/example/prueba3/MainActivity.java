package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v (TAG, "onDestroy called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v (TAG, "onStart called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v (TAG, "onStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v (TAG, "onPause called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v (TAG, "onResume called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v (TAG, "onRestart called");
    }

    public void butClick(View button) {
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(this, "Pulsado!", time);
        msg.show();
    }
}
