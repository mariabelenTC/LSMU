package marbel.mov.urjc.imagerv02.PlayColorea;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import marbel.mov.urjc.imagerv02.Dibujar;
import marbel.mov.urjc.imagerv02.MainActivity;


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


    public MyBoton(Dibujar mainActivity) {
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


    public void crearMiBoton( int x, int y, int color_original) {

        setPos_x(x);
        setPos_y(y);
        setColor(color_original);
        setColor_play(-1);

    }






}