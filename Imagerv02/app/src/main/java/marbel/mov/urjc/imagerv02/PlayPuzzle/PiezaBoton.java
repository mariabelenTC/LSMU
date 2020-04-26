package marbel.mov.urjc.imagerv02.PlayPuzzle;

import android.content.Context;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;


public class PiezaBoton extends Button{

    private static final String TAG = "pieza";
    private int pos_x;
    private int pos_y;
    private int color;
    private int colorPlay;



    private int colorput;




    //Constructor de la clase
    public PiezaBoton(Context context, int cx, int cy , int c,int cp,int cpt){

        super(context);
        this.pos_x= cx;
        this.pos_y = cy;
        this.color= c;
        this.colorPlay=cp;
        this.colorput=cpt;



    }
    public PiezaBoton(Puzzle mainActivity) {
        super(mainActivity);
    }

    public int getColorPlay() {
        return colorPlay;
    }
    public void setColorPlay(int colorPlay) {
        this.colorPlay = colorPlay;
    }
    public int getPos_x() {
        return pos_x;
    }
    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }
    public int getPos_y() {
        return pos_y;
    }
    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getColorput() {
        return colorput;
    }
    public void setColorput(int colorput) {
        this.colorput = colorput;
    }

    /**
     * Construye un botón cuyo tamaño viene dado por el de la pantalla y el de una imagen,
     * ambos en píxeles.
     * @param dispDimensions Dimensiones de la pantalla
     * @param bm Bitmap de la imagen
     * @return
     */
    public static PiezaBoton getDefaultButton(int[] dispDimensions, Bitmap bm, Puzzle m) {
        int[] buttonDimensions = new int[]{ dispDimensions[0]/ (bm.getHeight()*2), dispDimensions[1]/ (bm.getWidth())};
        PiezaBoton b = new PiezaBoton(m);
        b.setMinHeight(0);
        b.setMinWidth(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonDimensions[1], buttonDimensions[0]);
        params.setMargins(1, 1, 1, 1);
        b.setLayoutParams(params);

        return b;
    }




}
