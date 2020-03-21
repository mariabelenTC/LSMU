package marbel.mov.urjc.dospixeles;
import android.content.Context;
import android.widget.Button;


public class MyBoton extends Button {

    private int pos_x;
    private int pos_y;
    private  int color;
    private  int indice;

    //Constructor de la clase
    public MyBoton(Context context, int cx, int cy , int mycolor, int myindice) {

        super(context);
        this.pos_x= cx;
        this.pos_y = cy;
        this.color= mycolor;
        this.indice=myindice;


    }

    public MyBoton(MainActivity mainActivity) {
        super(mainActivity);
    }

    //Metdodos:
    public int getPos_x() { return this.pos_x; }
    public void setPos_x(int pos_x) { this.pos_x = pos_x; }
    public int getPos_y() { return this.pos_y; }
    public void setPos_y(int pos_y) { this.pos_y = pos_y; }
    public int getIndice() {
        return this.indice;
    }
    public void setIndice(int indice) { this.indice = indice; }
    public int getColor() {
        return this.color;
    }
    public void setColor(int color) {
        this.color = color;
    }
}