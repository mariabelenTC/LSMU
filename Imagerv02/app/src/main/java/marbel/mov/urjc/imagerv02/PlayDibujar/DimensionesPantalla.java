package marbel.mov.urjc.imagerv02.PlayDibujar;


import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;

public class DimensionesPantalla {
    Dibujar m;
    public DimensionesPantalla(Dibujar main){
        m=main;

    }




    /**
     * Construye un GridLayout cuyo tamaño viene dado por el de una imagen, en píxeles.
     * @param bm Bitmap de la imagen
     * @return
     */

    public GridLayout getDefaultGridLayout(Bitmap bm) {
        GridLayout gridLayout = new GridLayout(m);

        gridLayout.setOrientation(GridLayout.HORIZONTAL);
        gridLayout.setRowCount(bm.getHeight());
        gridLayout.setColumnCount(bm.getWidth());

        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        return gridLayout;
    }



    /**
     * Obtener dimensiones de la pantalla.
     * @return
     */
    public int[] getDisplayDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{displayMetrics.heightPixels, displayMetrics.widthPixels};
    }

}
