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
    private String modoJuego,nombre;
    private Integer nivel;


    Change_Activity(Activity w){
        this.mainwin=w;


    }

    public Change_Activity(Activity w, String n, String m, Integer nl){
        this.mainwin=w;
        this.modoJuego=m;
        this.nivel=nl;
        this.nombre=n;

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
                intchange.putExtra("modo", modoJuego);
                intchange.putExtra("nombre", nombre);
                break;
            case R.id.BTN_PuzZle:
                intchange =new Intent(mainwin,Dificultad.class);
                intchange.putExtra("modo", modoJuego);
                intchange.putExtra("nombre",nombre);

                break;
            case R.id.BTN_next:
                if (modoJuego.equals("Dibujar")){
                    intchange =new Intent(mainwin,Dibujar.class);
                }else{
                    intchange =new Intent(mainwin,Puzzle.class);
                }

                intchange.putExtra("nivel", nivel);
                intchange.putExtra("nombre", nombre);
                break;
            case R.id.puzzleFinish:
                intchange =new Intent(mainwin,TopScore.class);

                intchange.putExtra("nombre", nombre);
                intchange.putExtra("score", nivel);

            default:
                throw new IllegalStateException("Pasa algo raro");
        }

        mainwin.startActivity(intchange);
    }
}
