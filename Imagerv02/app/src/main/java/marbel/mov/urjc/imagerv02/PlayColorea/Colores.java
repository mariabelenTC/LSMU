package marbel.mov.urjc.imagerv02.PlayColorea;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import marbel.mov.urjc.imagerv02.Dibujar;
import marbel.mov.urjc.imagerv02.R;


public class Colores implements SeekBar.OnSeekBarChangeListener {
    Dibujar mainWin;

    private Button boton_muestra;
    private static final String TAG = "idSeekBar";
    private static final String TAG1 = "seekbar";

    public Colores(Dibujar w, Button muestra){
        mainWin=w;

        this.boton_muestra=muestra;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.RedSeekBar:
                mainWin.seekR = seekBar.getProgress();
                Log.v(TAG, ("id del R: "+mainWin.seekR));
                break;
            case R.id.GreenSeekBar:
                mainWin.seekG = seekBar.getProgress();
                Log.v(TAG, ("id del G: "+mainWin.seekR));
                break;
            case R.id.BlueSeekBar:
                mainWin.seekB = seekBar.getProgress();
                Log.v(TAG, ("id del B: "+mainWin.seekR));
                break;

            default:
                Log.v(TAG, ("hola que asen"));
                break;
        }

        showFinalColor();
    }


    private void showFinalColor() {

        int color = Color.rgb(mainWin.seekR, mainWin.seekG, mainWin.seekB);
        boton_muestra.setBackground(new ColorDrawable(color));

        String txt = "tu configuracion es: " + Integer.toString(mainWin.seekR) + Integer.toString( mainWin.seekG) + Integer.toString(mainWin.seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        Log.v(TAG1, txt);
        Log.v(TAG1, txt2);


    }
}
