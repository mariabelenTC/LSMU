package marbel.mov.urjc.imagerv02;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Switch;


class Change_Activity implements View.OnClickListener {
    Activity mainwin;
    String opcion;


    Change_Activity(Activity w,String op){
        mainwin=w;
        opcion=op;


    }

    @Override
    public void onClick(View v) {
        Intent intchange;
        switch(opcion){
            case "aRegistro":
               intchange =new Intent(mainwin,Registro.class);
               break;
            case "aColorea":
                intchange =new Intent(mainwin,Menu.class);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + opcion);
        }

        mainwin.startActivity(intchange);
    }
}
