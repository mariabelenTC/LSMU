package marbel.mov.urjc.dospixeles;
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


public class MainActivity extends AppCompatActivity {


    private int seekR=255, seekG=255, seekB=255;

    private static final String TAG = "MyActivity";
    private static final String TAG1 = "variables";
    public MyBoton[][] botonera;


/*
    private void addBottons(int totalBotones, int color, int totalx, int totaly) {
        botonera=new MyBoton[totalBotones];
        MyBoton boton = new MyBoton();
        for (int i =0; i< totalx; i++){
            for(int j=0; j< totaly; j++){
                boton.setPos_x(i);
                boton.setPos_y(j);
                boton.setColor(color);
                botonera[i*j]

            }
        }

 */

    private View.OnClickListener put_toast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            put_toasts(v);
        }
    };
    private void put_toasts(View v){
        MyBoton b= (MyBoton) v;

        int time = Toast.LENGTH_SHORT;
        int color = Color.rgb(seekR, seekG, seekB);
        b.setBackground(new ColorDrawable(color));


        Toast msg = Toast.makeText(MainActivity.this, "pulsado", time);
        msg.show();




    }
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
    private void doSomethingWithColor() {
        int color = Color.rgb(seekR, seekG, seekB);
        Button muestra= (Button)findViewById(R.id.muestra_color);
        muestra.setBackground(new ColorDrawable(color));
        String txt = "tu configuracion es: " + Integer.toString(seekR) + Integer.toString( seekG) + Integer.toString(seekB);
        String txt2 = "mi color: " + Integer.toString(color);
        Log.v(TAG, txt);
        Log.v(TAG, txt2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Opciones de imagen (escalado = false)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        //Imagen
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.doscolores , options);



        //Layout padre
        LinearLayout parentLayout = findViewById(R.id.botonera);

        //Layout hijo (botonera)
        GridLayout gridLayout = this.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        //Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);


        //Añade los tres seekbar
        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);
        //Asigna el listenner a los seekbar
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

        Log.v(TAG1, ("el Height:"+ bm.getHeight() + " el width: " + bm.getWidth()));
        int[] pixels = new int[bm.getWidth() * bm.getHeight()];
        MyBoton b = null;

        //Inserta los píxeles del Bitmap (imagen) en el array pixels
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        Log.v(TAG1, ("total pixeles:"+ Integer.toString(totalPixels) + " witdth: " + Integer.toString(bm.getWidth()) + " Height: "+ Integer.toString(bm.getHeight())));

        for(int i = 0; i < totalPixels; i++){
            b = (MyBoton) this.getDefaultButton(this.getDisplayDimensions(), bm);

            //this.addBottons(totalPixels,);

            //Entero que representa el color del píxel del array de píxeles
            pixel = pixels[i];
            String text3= "pixel: " + Integer.toString(pixel);
            Log.v(TAG1, text3);


            //Color a partir del píxel
            int color = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));
            String txt = "int color: " + Integer.toString(pixel);
            Log.v(TAG1, txt);

            Log.v(TAG1, ("red: "+ Integer.toString(Color.red(pixel)) + " green: " + Integer.toString(Color.green(pixel)) + " blue: "+ Integer.toString(Color.blue(pixel))));

            //int color = Color.rgb(255 ,255,255 );
            //Asigna al botón el color blanco

            b.setBackground(new ColorDrawable(color));
            //Asignamose el Listener
            b.setOnClickListener(put_toast);

            //Añade el botón al GridLayout
            gridLayout.addView(b, i);





        }

        int colorboton1 =( (ColorDrawable) gridLayout.getChildAt(1).getBackground()).getColor();
        Log.v(TAG1, ("boton 1 gridlayout: "+ Integer.toString(colorboton1)));

        int colorboton0 =( (ColorDrawable) gridLayout.getChildAt(0).getBackground()).getColor();
        Log.v(TAG1, ("boton 1 gridlayout: "+ Integer.toString(colorboton0)));


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

        gridLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


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
        int[] buttonDimensions = new int[]{ dispDimensions[0]/ (bm.getHeight()*4), dispDimensions[1]/ (bm.getWidth()*2)};

        MyBoton b = new MyBoton(this);

        b.setMinHeight(0);
        b.setMinWidth(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonDimensions[1], buttonDimensions[0]);
        params.setMargins(1, 1, 1, 1);
        b.setLayoutParams(params);

        return b;
    }



    /**
     * Obtener dimensiones de la pantalla.
     * @return
     */
    private int[] getDisplayDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{displayMetrics.heightPixels, displayMetrics.widthPixels};
    }

}
