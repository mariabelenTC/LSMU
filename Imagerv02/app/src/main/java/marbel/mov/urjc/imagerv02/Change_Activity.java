package marbel.mov.urjc.imagerv02;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import marbel.mov.urjc.imagerv02.PlayDibujar.Dibujar;
import marbel.mov.urjc.imagerv02.PlayPuzzle.Puzzle;


public class Change_Activity implements View.OnClickListener {
    private Activity mainwin;
    private Intent intchange;
    private static final String TAG = "Nivel que elijo";
    private String identificador,name;
    private Integer valor;
    private String valor2;

    Change_Activity(Activity w){
        this.mainwin=w;

    }
    Change_Activity(Activity w, String id, Integer value){
        this.mainwin=w;
        this.identificador=id;
        this.valor=value;
    }


    Change_Activity(Activity w, String id, String val){
        mainwin=w;
        identificador=id;
        valor2=val;

    }

    Change_Activity(Activity w, String id, Integer value,String name){
        this.mainwin=w;
        this.identificador=id;
        this.valor=value;
        this.name=name;

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
                intchange.putExtra(identificador, valor2);

                break;
            case R.id.BTN_PuzZle:
                intchange =new Intent(mainwin,Dificultad.class);
                juego= "Puzzle";
                intchange.putExtra("opcion", juego);
                intchange.putExtra("nombre", valor2);

                break;
            case R.id.BTN_next:
                if (identificador.equals("Dibujar")){
                    intchange =new Intent(mainwin,Dibujar.class);
                }else{
                    intchange =new Intent(mainwin,Puzzle.class);
                }

                intchange.putExtra("nivel", valor);
                intchange.putExtra("nombre", valor2);
                break;
            case R.id.puzzleFinish:
                intchange =new Intent(mainwin,TopScore.class);

                intchange.putExtra("nombre", identificador);
                intchange.putExtra("score", valor);

            default:
                throw new IllegalStateException("Pasa algo raro");
        }

        mainwin.startActivity(intchange);
    }
}
