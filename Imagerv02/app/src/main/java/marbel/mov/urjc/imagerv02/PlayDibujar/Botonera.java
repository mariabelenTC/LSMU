package marbel.mov.urjc.imagerv02.PlayDibujar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;



public class Botonera {
    Dibujar m;
    private static final String TAG1 = "colores";
    public Botonera(Dibujar main){
        m=main;

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
    public MyBoton[][] addBottons(MyBoton boton, int x, int y, int color_original, MyBoton[][] bot) {

        boton.setPos_x(x);
        boton.setPos_y(y);
        boton.setColor(color_original);
        boton.setColor_play(-1);
        bot[x][y]=boton;

        Log.v(TAG1,("x:"+ Integer.toString(x)+ " y: " + Integer.toString(y)));
        return bot;

    }

    public void restartColorbotons(){
        for(int i = 0; i < m.fila; i++) {
            for (int j = 0; j < m.columna; j++) {
                //Asigna al botón el color blanco
                int color = Color.rgb(255, 255, 255);
                m.botonera[i][j].setBackground(new ColorDrawable(color));
                m.botonera[i][j].setColor_play(color);
            }
        }

    }
    public void helpColorbotons(){
        for(int i = 0; i < m.fila; i++) {
            for (int j = 0; j < m.columna; j++) {
                //Asigna al botón el color blanco
                int color =m.botonera[i][j].getColor();
                m.botonera[i][j].setBackground(new ColorDrawable(color));
            }
        }


    }

}
