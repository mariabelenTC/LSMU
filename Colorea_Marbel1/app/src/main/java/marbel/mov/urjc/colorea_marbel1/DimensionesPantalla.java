package marbel.mov.urjc.colorea_marbel1;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridLayout;

public class DimensionesPantalla {
    MainActivity m;
    public DimensionesPantalla(MainActivity main){
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
