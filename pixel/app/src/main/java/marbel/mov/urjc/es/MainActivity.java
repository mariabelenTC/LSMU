package marbel.mov.urjc.es;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;


public class MainActivity extends AppCompatActivity {


    private int seekR=255, seekG=255, seekB=255;
    private static final String TAG = "MyActivity";
    private static final String TAG1 = "variables";


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
    /**
     * Añade 3 seekbar a un LinearLayout.
     *
     */
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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
        setContentView(R.layout.activity_main);

        //Opciones de imagen (escalado = false)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        //Imagen
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.mario5 , options);

        //Layout padre
        LinearLayout parentLayout = findViewById(R.id.botonera);

        //Layout hijo (botonera)
        GridLayout gridLayout = this.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        //Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);

        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);




        sbR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbB.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    /**
     * Añade botones a un gridLayout con los colores de una imagen.
     * @param gridLayout GridLayout
     * @param bm Bitmap de la imagen
     */
    private void setGridLayoutButtons(GridLayout gridLayout, Bitmap bm) {
        int pixel = 0;
        int totalPixels = bm.getHeight() * bm.getWidth();
        int[] pixels = new int[bm.getWidth() * bm.getHeight()];
        Button b = null;

        //Inserta los píxeles del Bitmap (imagen) en el array pixels
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        Log.v(TAG1, ("total pixeles:"+ Integer.toString(totalPixels) + "witdth: " + Integer.toString(bm.getWidth()) + "Height: "+Integer.toString(bm.getHeight()) ));

        for(int i = 0; i < totalPixels; i++){
            b = this.getDefaultButton(this.getDisplayDimensions(), bm);

            //Entero que representa el color del píxel del array de píxeles
            pixel = pixels[i];

            //Color a partir del píxel
            String text3= "El pixel: " + Integer.toString(pixel);
            Log.v(TAG, text3);

            String txt = "mis pixeles originales son: " + Integer.toString(pixel);
            Log.v(TAG, txt);

            int color = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));
            //int color = Color.rgb(Color.red(255), Color.green(255), Color.blue(255));
            //Asigna al botón el color de fondo
            b.setBackground(new ColorDrawable(color));
            //Asignamose el Listener
            b.setOnClickListener(put_toast);

            //Añade el botón al GridLayout
            gridLayout.addView(b, i);


        }
    }

    /**
     * Construye un GridLayout cuyo tamaño viene dado por el de una imagen, en píxeles.
     * @param bm Bitmap de la imagen
     * @return
     */
    private GridLayout getDefaultGridLayout(Bitmap bm) {
        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setOrientation(GridLayout.HORIZONTAL);
        gridLayout.setRowCount(bm.getHeight());
        gridLayout.setColumnCount(bm.getWidth());

        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        return gridLayout;
    }

    /**
     * Construye un botón cuyo tamaño viene dado por el de la pantalla y el de una imagen,
     * ambos en píxeles.
     * @param dispDimensions Dimensiones de la pantalla
     * @param bm Bitmap de la imagen
     * @return
     */
    private Button getDefaultButton(int[] dispDimensions, Bitmap bm) {
        int[] buttonDimensions = new int[]{
                dispDimensions[0]/ (bm.getHeight()*4),
                dispDimensions[1]/ (bm.getWidth()*2)
        };

        Button b = new Button(this);

        b.setMinHeight(0);
        b.setMinWidth(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                buttonDimensions[1],
                buttonDimensions[0]);

        params.setMargins(1, 1, 1, 1);
        b.setLayoutParams(params);

        return b;
    }

    /**
     * Recupera las dimensiones de la pantalla.
     * @return
     */
    private int[] getDisplayDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{
                displayMetrics.heightPixels, displayMetrics.widthPixels
        };
    }

    private void doSomethingWithColor() {
        int color = Color.rgb(seekR, seekG, seekB);

        String txt = "tu configuracion es: " + Integer.toString(seekR) + Integer.toString( seekG) + Integer.toString(seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        Log.v(TAG, txt);
        Log.v(TAG, txt2);
    }
}