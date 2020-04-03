package marbel.mov.urjc.colorea_marbel1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class SeekBar_Listener implements SeekBar.OnSeekBarChangeListener {
    MainActivity mainWin;
    int seekR,seekG,seekB, progress;



    public SeekBar_Listener(MainActivity w, int r,int g, int b){
        mainWin=w;
        seekR=r;
        seekG=g;
        seekB=b;

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
                seekR = progress;
                break;
            case R.id.GreenSeekBar:
                seekG = progress;
                break;
            case R.id.BlueSeekBar:
                seekB = progress;
                break;
        }

        showFinalColor();

    }

    private void showFinalColor() {
        int color = Color.rgb(seekR, seekG, seekB);

        Button muestra= (Button) mainWin.findViewById(R.id.muestra_color);
        muestra.setBackground(new ColorDrawable(color));
        String txt = "tu configuracion es: " + Integer.toString(seekR) + Integer.toString( seekG) + Integer.toString(seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        /*Log.v(TAG, txt);
        Log.v(TAG, txt2);*/
    }
}
