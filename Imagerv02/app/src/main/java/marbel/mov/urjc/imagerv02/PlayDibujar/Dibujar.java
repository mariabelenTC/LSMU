package marbel.mov.urjc.imagerv02.PlayDibujar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import marbel.mov.urjc.imagerv02.R;

public class Dibujar extends AppCompatActivity {
    private Bundle bundle;
    private static final String TAG = "variables";
    private static final String TAG1 = "colores";
    public int seekR=0, seekG=0, seekB=0, color_muestra;
    public int tiempo; // tiempo inicial en milisegundos -> 3 minuto = 3*60*1000
    public long timeleftinMilliseconds, countDownInterval=1000;
    public TextView countadown_Text;
    public boolean  timeRunning=false;

    private ImageView ImagenMuestra;
    private Bitmap bm;
    public int totalBotones;
    public int fila=0, columna=0;
    public Temporizador temporizador;
    public MyBoton[][] botonera;
    public Button boton_Start, boton_Restart, boton_finisht, boton_help,muestra;
    public Score score = new Score(this);
    public Botonera bot=new Botonera(this);
    public DimensionesPantalla dimp=new DimensionesPantalla(this);
    private String nameUser;
    public OnClickListener put_toast;
    public boolean ganador =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar2);

        //Datos recibidos del Activity Dificultad
        bundle=getIntent().getExtras();

        // Temporizador:

        countadown_Text=findViewById(R.id.countdown_text);
        boton_Start=findViewById(R.id.start);
        boton_Restart=findViewById(R.id.restart);
        boton_finisht=findViewById(R.id.finish);
        boton_help=findViewById(R.id.help);
        muestra= (Button) findViewById(R.id.muestra_color);
        ImagenMuestra=findViewById(R.id.imageView);

        // Añade los tres seekbar
        Colores seek = new Colores(this,muestra);

        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);
        SeekBar[] seekbList= new SeekBar[]{sbR,sbG,sbB};

        for (int i =0; i<seekbList.length; i++){
            seekbList[i].setOnSeekBarChangeListener(seek);
        }

        // Opciones de imagen (escalado = false)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        if(bundle!=null){

            Integer nivel=bundle.getInt("nivel",0);
            nameUser=bundle.getString("nombre"," ");

            put_toast = new OnClickListener(this,nameUser);

            boton_finisht.setOnClickListener(put_toast);
            boton_Start.setOnClickListener(put_toast);
            boton_Restart.setOnClickListener(put_toast);
            boton_help.setOnClickListener(put_toast);


            Log.v(TAG, ("nivel: "+ Integer.toString(nivel)));
            switch (nivel){
                case 0:
                    Log.v(TAG, ("nivel 0"));
                    tiempo=60*1000;
                    timeleftinMilliseconds=tiempo;
                    ConstruirDiseño(R.drawable.cuatrocolores,R.drawable.cuatrocolores_r,"01:00" );


                    break;
                case 1:
                    Log.v(TAG, ("nivel 1"));
                    tiempo=4*60*1000;
                    timeleftinMilliseconds=tiempo;
                    ConstruirDiseño(R.drawable.mario_final5finalfinal,R.drawable.mario_final2min,"04:00" );

                    break;
                case 2:
                    Log.v(TAG, ("nivel 2"));
                    tiempo=7*60*1000;
                    timeleftinMilliseconds=tiempo;
                    ConstruirDiseño(R.drawable.pikamuestra,R.drawable.pikachupixel,"07:00" );

                    break;

            }
        }
        temporizador=new Temporizador(this,timeleftinMilliseconds,countDownInterval,nameUser);

    }
    private void ConstruirDiseño(Integer imgMuestra, Integer imgPixel,String tiempo){
        BitmapFactory.Options options;
        LinearLayout parentLayout;
        GridLayout gridLayout;

        //Imagen
        ImagenMuestra.setImageResource(imgMuestra);
        countadown_Text.setText(tiempo);

        //Botonera
        options= new BitmapFactory.Options();
        options.inScaled = false;
        bm = BitmapFactory.decodeResource(getResources(), imgPixel, options);
        fila=bm.getHeight();
        columna=bm.getWidth();

        botonera = new MyBoton[fila][columna];

        // Layout padre
        parentLayout = findViewById(R.id.botonera);

        // Layout hijo (botonera)
        gridLayout = dimp.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        // Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);


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

        int indice = 0;
        for(int i = 0; i < bm.getHeight(); i++){
            for(int j =0; j < bm.getWidth(); j++){
                b = (MyBoton) MyBoton.getDefaultButton(dimp.getDisplayDimensions(), bm, this);

                //Entero que representa el color del píxel del array de píxeles
                pixel = pixels[indice];
                String text3= "pixel: " + Integer.toString(pixel);
                Log.v(TAG1, text3);

                //Color a partir del píxel
                int color_original = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));

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

    }


}
