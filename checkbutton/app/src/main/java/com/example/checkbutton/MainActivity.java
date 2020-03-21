package com.example.checkbutton;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnClick(View v){
        CheckBox boton1 = findViewById(R.id.b1);
        CheckBox boton2 = findViewById(R.id.b2);
        CheckBox boton3 = findViewById(R.id.b3);
        CheckBox boton4 = findViewById(R.id.b4);

        String txt = "tu configuracion es: " + " \n";

        if (boton1.isChecked()) {
            txt += boton1.getText() + " ";
        }
        if (boton2.isChecked()) {
            txt += boton2.getText() + " ";
        }

        if (boton3.isChecked()){
            txt += boton3.getText() + " ";
        }
        if(boton4.isChecked()){
            txt += boton4.getText() + " ";
        }

        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(MainActivity.this, txt, time);
        msg.show();
    }
}
