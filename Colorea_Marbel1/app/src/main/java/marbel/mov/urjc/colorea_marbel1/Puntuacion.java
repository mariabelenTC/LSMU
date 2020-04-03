package marbel.mov.urjc.colorea_marbel1;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

public class Puntuacion {
    private static final String TAG1 = "colores";
    private static final String TAG2 = "puntuacion";
    private static final String TAG3 = "score_botton";
    private static final String TAG4 = "rgb_score";
    int totalBotones, fila,columna,time;
    MyBoton[][] botonera;
    MainActivity mainWin;

    public Puntuacion(MainActivity w,int tb,int f,int c,MyBoton[][] botner,int t){
        mainWin=w;

        this.totalBotones=tb;
        this.fila=f;
        this.columna=c;
        this.botonera=botner;
        this.time=t;

    }


    private int score(int color_original, int color_asignado){
        float resta = Math.abs(color_original - color_asignado);
        Log.v(TAG4, ("resta: " + Float.toString(resta)));
        float score = (float)((-510/255)* (int)resta +510);
        //float score = (float) ((-100/255)*resta+100);
        Log.v(TAG4, ("calcular score: " + Integer.toString((int)score)));
        return (int)score;
    }
    /**
     * Obtener puntuacion de todos los botones
     * y calcular si se ha superado el minimo o no.
     */
    public void getScore(){
        int total_score=0;
        int score_max=1530*totalBotones;
        Log.v(TAG2, ("puntuacion Maxima: " + Integer.toString(score_max)));

        int colorR_score, colorG_score, colorB_score, score_boton;

        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {


                Log.v(TAG3, ("posicion: (" + Integer.toString(i) +
                        ", " + Integer.toString(j) + ") "));

                int r = Color.red(botonera[i][j].getColor());
                int g = Color.green(botonera[i][j].getColor());
                int b = Color.blue(botonera[i][j].getColor());
                Log.v(TAG3, ("red_o: " + Integer.toString(r) +
                        " green_o: " + Integer.toString(g) +
                        " blue_o: " + Integer.toString(b)));


                int r_p = Color.red(botonera[i][j].getColor_play());
                int g_p = Color.green(botonera[i][j].getColor_play());
                int b_p = Color.blue(botonera[i][j].getColor_play());
                Log.v(TAG3, ("red_play: " + Integer.toString(r_p) +
                        " green_play: " + Integer.toString(g_p) +
                        " blue_play: " + Integer.toString(b_p)));


                colorR_score = score(r, r_p);
                colorG_score = score(g, g_p);
                colorB_score = score(b, b_p);
                Log.v(TAG3, ("score_R: " + Integer.toString(colorR_score) +
                        " score G: " + Integer.toString(colorG_score) +
                        " score_B: " + Integer.toString(colorB_score)));

                score_boton = colorR_score + colorG_score + colorB_score;
                Log.v(TAG3, ("score del boton: " + Integer.toString(score_boton)));

                total_score = total_score + score_boton;
            }
        }
        int min=(score_max *8)/9;
        Log.v(TAG2,("puntuación minima: " + Integer.toString(min)));

        if (total_score>= ((score_max *8)/9)){

            Log.v(TAG2,("has ganado y tu puntación es: " + Integer.toString(total_score)));

            String msg_w = ("::: WINNER :::  score"  + Integer.toString(total_score));
            Toast msg;
            msg = Toast.makeText(mainWin, msg_w, time);
            msg.show();

        }else {

            Log.v(TAG2,("has perdido y tu puntación es: " + Integer.toString(total_score)));

            String msg_l = ("::: LOSER :::score"  + Integer.toString(total_score));
            Toast msg;
            msg = Toast.makeText(mainWin, msg_l, time);
            msg.show();
        }
    }

}
