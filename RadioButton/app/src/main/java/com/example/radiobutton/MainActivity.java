package com.example.radiobutton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private RadioGroup Group1;
    private Button boton1, boton2, boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup Group1 = (RadioGroup) findViewById(R.id.Grupo_botones);

        Button boton1= (Button)findViewById(R.id.button_1);
        Button boton2= (Button)findViewById(R.id.button_2);
        Button boton3= (Button)findViewById(R.id.button_3);

        Orientador or = new Orientador(this);

        
        // registrando un manejador en el boton
        boton1.setOnClickListener(or);
        boton2.setOnClickListener(or);
        boton3.setOnClickListener(or);


    }




}
