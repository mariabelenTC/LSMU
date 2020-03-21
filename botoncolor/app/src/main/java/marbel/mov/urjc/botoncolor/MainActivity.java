package marbel.mov.urjc.botoncolor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private Button b1;

    private int seekR=0, seekG=0, seekB=0;
    private SeekBar MyseekBar;
    private static final String TAG = "Mycolor seekbkar";


    private View.OnClickListener put_toast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int time = Toast.LENGTH_SHORT;

            int color = Color.rgb(seekR, seekG, seekB);
            v.setBackground(new ColorDrawable(color));

            Toast msg = Toast.makeText(MainActivity.this, "pulsado", time);
            msg.show();
        }
    };

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            boolean pulsado;

            pulsado= seekBar.isActivated();

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

            doSomethingWithColor();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Establecemos el layout main
        setContentView(R.layout.activity_main);

        LinearLayout Layout = (LinearLayout) findViewById(R.id.botonera);

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMarginStart(6);

        put_bottons(b1,Layout,lp);


        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);


        sbR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbB.setOnSeekBarChangeListener(onSeekBarChangeListener);


    }


    private void put_bottons(Button b1, LinearLayout Layout, LinearLayout.LayoutParams lp){
        for (int i=0; i<2 ; i++ ){
            b1 = new Button(this);
            //Asignamos propiedades de layout al boton
            b1.setLayoutParams(lp);

            //Añadimos el botón a la botonera
            Layout.addView(b1);

            //Asignamose el Listener
            b1.setOnClickListener(put_toast);
        }

    }
    private void doSomethingWithColor() {
        int color = Color.rgb(seekR, seekG, seekB);

        String txt = "tu configuracion es: " + Integer.toString(seekR) + Integer.toString( seekG) + Integer.toString(seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        Log.v(TAG, txt);
        Log.v(TAG, txt2);

    }

}
