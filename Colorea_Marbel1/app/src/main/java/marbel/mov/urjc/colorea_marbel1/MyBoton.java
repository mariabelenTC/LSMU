package marbel.mov.urjc.colorea_marbel1;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.LinearLayout;


public class MyBoton extends Button {

    private int pos_x;
    private int pos_y;
    private  int color;
    private  int color_play=0;

    //Constructor de la clase
    public MyBoton(Context context, int cx, int cy , int mycolor, int color_jugador) {

        super(context);
        this.pos_x= cx;
        this.pos_y = cy;
        this.color= mycolor;
        this.color_play= color_jugador;


    }

    public MyBoton(MainActivity mainActivity) {
        super(mainActivity);
    }

    //Metdodos:
    public int getPos_x() { return this.pos_x; }
    public void setPos_x(int pos_x) { this.pos_x = pos_x; }
    public int getPos_y() { return this.pos_y; }
    public void setPos_y(int pos_y) { this.pos_y = pos_y; }
    public int getColor() {
        return this.color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getColor_play() { return this.color_play; }
    public void setColor_play(int color_play) { this.color_play = color_play; }

    /**
     * Construye un botón cuyo tamaño viene dado por el de la pantalla y el de una imagen,
     * ambos en píxeles.
     * @param dispDimensions Dimensiones de la pantalla
     * @param bm Bitmap de la imagen
     * @return
     */
    public static MyBoton getDefaultButton(int[] dispDimensions, Bitmap bm, MainActivity m) {
        int[] buttonDimensions = new int[]{ dispDimensions[0]/ (bm.getHeight()*4), dispDimensions[1]/ (bm.getWidth()*2)};
        MyBoton b = new MyBoton(m);
        b.setMinHeight(0);
        b.setMinWidth(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonDimensions[1], buttonDimensions[0]);
        params.setMargins(1, 1, 1, 1);
        b.setLayoutParams(params);

        return b;
    }



}