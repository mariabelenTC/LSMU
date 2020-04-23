package marbel.mov.urjc.imagerv02.PlayPuzzle;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

public class ScorePuzzle {
    private static final String TAG2 = "puntuacion";
    private static final String TAG3 = "score_botton";
    private static final String TAG4 = "rgb_score";
    private Puzzle m;
    public int time = Toast.LENGTH_SHORT;


    private PiezaBoton[][] botonera;
    private int fila,columna;

    public ScorePuzzle(PiezaBoton[][] bot, int f, int c){


        botonera=bot;
        fila=f;
        columna=c;

    }

    /**
     * Obtiener la puntuacion de un elemento del color (R,G,B)
     * @param color_original el color que deberia tener
     * @param color_asignado el color que asigna el jugador.
     * @return
     */
    public int score(int color_original, int color_asignado){
        int score=0;

        if (color_original==color_asignado){
            score=1;

        }

        return score;

    }
    /**
     * Obtener puntuacion de todos los botones
     * y calcular si se ha superado el minimo o no. 644267769
     */
    public int getScore(){
        int total_score=0;
        //int score_max=m.totalBotones;
        //int score_min=score_max*2/3;

        int score;

        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                int colorOrginal = botonera[i][j].getColor();
                int colorJugador=botonera[i][j].getColorPlay();
                score = score(colorOrginal,colorJugador);
                total_score = total_score + score;
            }
        }

        /*

        if (total_score>= score_min){

            Log.v(TAG2,("has ganado y tu puntación es: " + Integer.toString(total_score)));

            String msg_w = ("::: WINNER :::  score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(m, msg_w, time);
            msg.show();

        }else{

            Log.v(TAG2,("has perdido y tu puntación es: " + Integer.toString(total_score)));

            String msg_l = ("::: LOSER :::score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(m, msg_l, time);
            msg.show();
        }

         */
        return total_score;
    }
}
