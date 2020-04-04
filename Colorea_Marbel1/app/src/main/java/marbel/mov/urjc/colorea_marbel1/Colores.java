package marbel.mov.urjc.colorea_marbel1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Colores implements SeekBar.OnSeekBarChangeListener {
    MainActivity mainWin;
    int seekR,seekG,seekB;
    private Button boton_muestra;
    private static final String TAG = "idSeekBar";
    private static final String TAG1 = "seekbar";
    private int colorRGB;

    public Colores(Button muestra){

        boton_muestra=muestra;
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
                seekR = seekBar.getProgress();
                Log.v(TAG, ("id del R: "+seekR));
                break;
            case R.id.GreenSeekBar:
                seekG = seekBar.getProgress();
                Log.v(TAG, ("id del G: "+seekR));
                break;
            case R.id.BlueSeekBar:
                seekB = seekBar.getProgress();
                Log.v(TAG, ("id del B: "+seekR));
                break;

            default:
                Log.v(TAG, ("hola que asen"));
                break;
        }

        showFinalColor();

    }


    private void showFinalColor() {

        int color = Color.rgb(seekR, seekG, seekB);
        int colorRGB = color;
        boton_muestra.setBackground(new ColorDrawable(color));

        String txt = "tu configuracion es: " + Integer.toString(seekR) + Integer.toString( seekG) + Integer.toString(seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        Log.v(TAG1, txt);
        Log.v(TAG1, txt2);
    }
}
