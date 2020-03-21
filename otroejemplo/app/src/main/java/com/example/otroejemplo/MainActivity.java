package com.example.otroejemplo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button b1;

    private View.OnClickListener put_toast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            put_toast();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Establecemos el layout main
        setContentView(R.layout.activity_main);

        LinearLayout Layout = (LinearLayout) findViewById(R.id.mb);

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i=0; i<2; i++ ){
            b1 = new Button(this);
            //Asignamos propiedades de layout al boton

            b1.setLayoutParams(lp);
            //Asignamos Texto al botón
            b1.setText("b");
            //Añadimos el botón a la botonera
            Layout.addView(b1);

            //Asignamose el Listener
            b1.setOnClickListener(put_toast);
        }

    }

    private void put_toast() {
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(MainActivity.this, "pulsado", time);
        msg.show();

    }



}
