package marbel.mov.urjc.colorea_marbel1;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "variables";
    private static final String TAG1 = "colores";
    public int seekR=0, seekG=0, seekB=0, color_muestra;
    public int tiempo=240000; // tiempo inicial en milisegundos -> 3 minuto = 3*60*1000
    public long timeleftinMilliseconds=tiempo, countDownInterval=1000;
    public TextView countadown_Text;
    public boolean  timeRunning=false;
    public int time = Toast.LENGTH_SHORT;
    public   int totalBotones;
    public   int fila=0, columna=0;
    public   Temporizador temporizador;
    public MyBoton[][] botonera;
    public Button boton_Start, boton_Restart, boton_finisht, boton_help;
    public Score score = new Score(MainActivity.this);
    public Botonera bot=new Botonera(MainActivity.this);
    public  DimensionesPantalla dimp=new DimensionesPantalla(MainActivity.this);

    public OnClickListener put_toast = new OnClickListener(MainActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Temporizador:

        countadown_Text=findViewById(R.id.countdown_text);
        boton_Start=findViewById(R.id.start);
        boton_Restart=findViewById(R.id.restart);
        boton_finisht=findViewById(R.id.finish);
        boton_help=findViewById(R.id.help);

        boton_finisht.setOnClickListener(put_toast);
        boton_Start.setOnClickListener(put_toast);
        boton_Restart.setOnClickListener(put_toast);
        boton_help.setOnClickListener(put_toast);


        // Opciones de imagen (escalado = false)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        // Imagen
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.mario_final2min , options);

        fila=bm.getHeight();
        columna=bm.getWidth();

        botonera = new MyBoton[fila][columna];

        // Layout padre
        LinearLayout parentLayout = findViewById(R.id.botonera);

        // Layout hijo (botonera)
        GridLayout gridLayout = dimp.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        // Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);


        // Añade los tres seekbar
        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);


        Button muestra= (Button) findViewById(R.id.muestra_color);


        Colores seek = new Colores(this,muestra);
        // Asigna el listenner a los seekbar
        sbR.setOnSeekBarChangeListener(seek);
        sbG.setOnSeekBarChangeListener(seek);
        sbB.setOnSeekBarChangeListener(seek);

        temporizador=new Temporizador(MainActivity.this,timeleftinMilliseconds,countDownInterval);



    }
    /**
     * Añade botones a un gridLayout con los colores de una imagen.
     * @param gridLayout GridLayout
     * @param bm Bitmap de la imagen
     */
    public void setGridLayoutButtons(GridLayout gridLayout, Bitmap bm) {
        int pixel = 0;
        int totalPixels = bm.getHeight() * bm.getWidth();

        totalBotones=totalPixels;

        int[] pixels = new int[bm.getWidth() * bm.getHeight()];
        MyBoton b = null;

        //Inserta los píxeles del Bitmap (imagen) en el array pixels
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        Log.v(TAG1,("total pixeles:"+ Integer.toString(totalPixels) +
                " witdth: " + Integer.toString(bm.getWidth()) +
                " Height: "+ Integer.toString(bm.getHeight())));

        int indice = 0;
        for(int i = 0; i < bm.getHeight(); i++){
            for(int j =0; j < bm.getWidth(); j++){
                b = (MyBoton) MyBoton.getDefaultButton(dimp.getDisplayDimensions(), bm, MainActivity.this);

                //Entero que representa el color del píxel del array de píxeles
                pixel = pixels[indice];
                String text3= "pixel: " + Integer.toString(pixel);
                Log.v(TAG1, text3);

                //Color a partir del píxel
                int color_original = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));
                /*
                String txt = "int color: " + Integer.toString(pixel);
                Log.v(TAG1, txt);

                Log.v(TAG1,("red: "+ Integer.toString(Color.red(pixel)) +
                            " green: " + Integer.toString(Color.green(pixel)) +
                            " blue: "+ Integer.toString(Color.blue(pixel))));

                 */
                //Asigna al botón el color blanco
                int color = Color.rgb(255 ,255,255 );
                b.setBackground(new ColorDrawable(color));

                //Asignamose el Listener
                b.setOnClickListener(put_toast);

                // Guardar botones en el Array botonera;
                botonera= bot.addBottons(b,i, j, color_original,botonera);

                //Añade el botón al GridLayout
                gridLayout.addView(b, indice);
                indice = indice+1;

            }
        }
        /*
        int colorboton0 =( (ColorDrawable) gridLayout.getChildAt(0).getBackground()).getColor();
        Log.v(TAG1, ("boton 0 gridlayout: "+ Integer.toString(colorboton0)));

        int colorboton1 =( (ColorDrawable) gridLayout.getChildAt(1).getBackground()).getColor();
        Log.v(TAG1, ("boton 1 gridlayout: "+ Integer.toString(colorboton1)));

        int colorboton2 =( (ColorDrawable) gridLayout.getChildAt(2).getBackground()).getColor();
        Log.v(TAG1, ("boton 2 gridlayout: "+ Integer.toString(colorboton2)));

        int colorboton3 =( (ColorDrawable) gridLayout.getChildAt(3).getBackground()).getColor();
        Log.v(TAG1, ("boton 3 gridlayout: "+ Integer.toString(colorboton3)));

         */
    }






}

