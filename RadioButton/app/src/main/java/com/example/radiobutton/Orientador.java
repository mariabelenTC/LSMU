package com.example.radiobutton;
import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Button;

class Orientador implements View.OnClickListener {
    Activity mainWin;

    //
    // REGISTRANDO UN MANEJADOR EN EL BOTON Y NO EN EL GRUPO


    Orientador(Activity w){
        mainWin = w;
    }

    // creando clase que tiene
    // El view que me pasan es la super clase del boton
    @Override
    public void onClick(View arg0) {
        boolean pulsado;
        // lo que hago
        RadioButton b = (RadioButton) arg0;
        pulsado= b.isChecked();

        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(mainWin, "pulsado: " + b.getText(), time);

        if (pulsado){
            msg.show();
        }

    }
}