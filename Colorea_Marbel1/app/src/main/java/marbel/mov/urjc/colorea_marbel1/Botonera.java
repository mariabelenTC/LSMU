package marbel.mov.urjc.colorea_marbel1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

public class Botonera {
    private static final String TAG1 = "colores";
    MyBoton[][] botonera;
    int fila,columna;
    public Botonera(MyBoton[][] botner,int f, int c){
        this.botonera=botner;
        this.fila=f;
        this.columna=c;
    }

    /**
     * Añade Myboton a un arraybidimensional.
     * @param boton boton de tipo MyBoton que quiero guardar
     * @param x fila
     * @param y columna
     * @param color_original color del pixel que deberia tener.
     * @return
     *
     */
    public MyBoton[][] addBottons(MyBoton boton, int x, int y, int color_original) {

        boton.setPos_x(x);
        boton.setPos_y(y);
        boton.setColor(color_original);
        boton.setColor_play(-1);
        botonera[x][y]=boton;

        Log.v(TAG1,("x:"+ Integer.toString(x)+ " y: " + Integer.toString(y)));
        return botonera;

    }

    public void restartColorbotons(){
        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                //Asigna al botón el color blanco
                int color = Color.rgb(255, 255, 255);
                botonera[i][j].setBackground(new ColorDrawable(color));
                botonera[i][j].setColor_play(color);
            }
        }

    }
    public void helpColorbotons(){
        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                //Asigna al botón el color blanco
                int color =botonera[i][j].getColor();
                botonera[i][j].setBackground(new ColorDrawable(color));
            }
        }


    }
}
