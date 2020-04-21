package marbel.mov.urjc.imagerv02;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;


class Change_Activity implements View.OnClickListener {
    private Activity mainwin;
    private Intent intchange;
    private static final String TAG = "Nivel que elijo";
    private String identificador;
    private Integer valor;


    Change_Activity(Activity w){
        mainwin=w;

    }


    Change_Activity(Activity w, String id, Integer value){
        mainwin=w;
        identificador=id;
        valor=value;

    }


    @Override
    public void onClick(View v) {
        String juego;

        switch(v.getId()){
            case R.id.btn_reg:
               intchange =new Intent(mainwin,Registro.class);
               break;
            case R.id.btn_empezar:
                intchange =new Intent(mainwin,Menu.class);
                break;
            case R.id.BTN_Dibujar:
                intchange =new Intent(mainwin,Dificultad.class);

                juego="Dibujar";
                intchange.putExtra("opcion", juego);

                break;
            case R.id.BTN_PuzZle:
                intchange =new Intent(mainwin,Dificultad.class);
                juego= "Puzzle";
                intchange.putExtra("opcion", juego);

                break;
            case R.id.BTN_next:
                if (identificador.equals("Dibujar")){
                    intchange =new Intent(mainwin,Dibujar.class);
                }else{
                    intchange =new Intent(mainwin,Puzzle.class);
                }

                intchange.putExtra("nivel", valor);
                break;

            default:
                throw new IllegalStateException("Pasa algo raro");
        }

        mainwin.startActivity(intchange);
    }
}
