package marbel.mov.urjc.imagerv02.PlayDibujar;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

public class Score {
    private static final String TAG2 = "puntuacion";
    private static final String TAG3 = "score_botton";
    private static final String TAG4 = "rgb_score";
    private Dibujar m;
    private int time = Toast.LENGTH_SHORT;


    public Score(Dibujar mainWin){
        m=mainWin;
    }

    /**
     * Obtiener la puntuacion de un elemento del color (R,G,B)
     * @param color_original el color que deberia tener
     * @param color_asignado el color que asigna el jugador.
     * @return
     */
    public int score(int color_original, int color_asignado){
        float resta,score;
        resta = Math.abs(color_original - color_asignado);
        score = (float)((-510/255)* (int)resta +510);
        return (int)score;

    }
    /**
     * Obtener puntuacion de todos los botones
     * y calcular si se ha superado el minimo o no. 644267769
     */
    public int getScore(){
        int total_score=0;
        int score_max=1530*m.totalBotones;

        int colorR_score, colorG_score, colorB_score, score_boton;

        for(int i = 0; i < m.fila; i++) {
            for (int j = 0; j < m.columna; j++) {

                int r = Color.red(m.botonera[i][j].getColor());
                int g = Color.green(m.botonera[i][j].getColor());
                int b = Color.blue(m.botonera[i][j].getColor());


                int r_p = Color.red(m.botonera[i][j].getColor_play());
                int g_p = Color.green(m.botonera[i][j].getColor_play());
                int b_p = Color.blue(m.botonera[i][j].getColor_play());


                colorR_score = score(r, r_p);
                colorG_score = score(g, g_p);
                colorB_score = score(b, b_p);

                score_boton = colorR_score + colorG_score + colorB_score;

                total_score = total_score + score_boton;


            }
        }

        int min=(score_max *8)/9;
        Log.v(TAG2,("puntuación minima: " + Integer.toString(min)));

        if (total_score>= ((score_max *8)/9)){

            Log.v(TAG2,("has ganado y tu puntación es: " + Integer.toString(total_score)));

            String msg_w = ("::: WINNER :::  score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(m, msg_w, time);
            msg.show();

        }else {

            Log.v(TAG2,("has perdido y tu puntación es: " + Integer.toString(total_score)));

            String msg_l = ("::: LOSER :::score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(m, msg_l, time);
            msg.show();
        }
        return total_score;
    }
}
