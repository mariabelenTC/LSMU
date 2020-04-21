package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.List;

import marbel.mov.urjc.imagerv02.PlayColorea.*;


public class Dibujar extends AppCompatActivity {
    private static final String TAG = "xx";
    private Bundle bundle;
    private Button boton_Start, boton_Restart, boton_finisht, boton_help,muestra;
    private TextView Texto_contador;
    private ImageView ImagenMuestra;
    private Bitmap bm;
    private Integer fila,columna;
    private Botonera myBotonera;
    private MyBoton[][] botonera;
    private List<Integer> seekList;
    public int seekR=0, seekG=0, seekB=0, color_muestra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar);

        //Datos recibidos del Activity Dificultad
        bundle=getIntent().getExtras();

        //Botones de mi Actividad
        Texto_contador=findViewById(R.id.countdown_text);
        boton_Start=findViewById(R.id.start);
        boton_Restart=findViewById(R.id.restart);
        boton_finisht=findViewById(R.id.finish);
        boton_help=findViewById(R.id.help);
        ImagenMuestra=findViewById(R.id.imageView);
        muestra= (Button) findViewById(R.id.muestra_color);

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

            Log.v(TAG, ("nivel: "+ Integer.toString(nivel)));
            switch (nivel){
                case 0:
                    Log.v(TAG, ("nivel 0"));
                    ConstruirDiseño(R.drawable.cuatrocolores,R.drawable.cuatrocolores_r,"01:00" );


                    break;
                case 1:
                    Log.v(TAG, ("nivel 1"));
                    ConstruirDiseño(R.drawable.mario_final5finalfinal,R.drawable.mario_final2min,"04:00" );

                    break;
                case 2:
                    Log.v(TAG, ("nivel 2"));
                    ConstruirDiseño(R.drawable.pikamuestra,R.drawable.pikachupixel,"07:00" );

                    break;

            }
        }

    }

    private void ConstruirDiseño(Integer imgMuestra, Integer imgPixel,String tiempo){
        BitmapFactory.Options options;
        LinearLayout parentLayout;
        GridLayout gridLayout;

        //Imagen
        ImagenMuestra.setImageResource(imgMuestra);
        Texto_contador.setText(tiempo);

        //Botonera
        options= new BitmapFactory.Options();
        options.inScaled = false;
        bm = BitmapFactory.decodeResource(getResources(), imgPixel, options);
        myBotonera= new Botonera(this);
        myBotonera.setFila(bm.getHeight());
        myBotonera.setColumna(bm.getWidth());


        parentLayout = (LinearLayout) findViewById(R.id.botonera);
        // Layout hijo (botonera)
        gridLayout = this.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        // Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);


    }

    /**
     * Construye un GridLayout cuyo tamaño viene dado por el de una imagen, en píxeles.
     * @param bm Bitmap de la imagen
     * @return
     */

    public GridLayout getDefaultGridLayout(Bitmap bm) {
        GridLayout gridLayout = new GridLayout(this);

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
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{displayMetrics.heightPixels, displayMetrics.widthPixels};
    }

    /**
     * Añade botones a un gridLayout con los colores de una imagen.
     * @param gridLayout GridLayout
     *
     */
    private void setGridLayoutButtons(GridLayout gridLayout,Bitmap bm) {
        int pixel = 0;
        int totalPixels = bm.getHeight() * bm.getWidth();
        int[] pixels = new int[totalPixels];

        MyBoton b = null;

        //Inserta los píxeles del Bitmap (imagen) en el array pixels
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());

        int indice = 0;
        for(int i = 0; i < bm.getHeight(); i++){
            for(int j =0; j < bm.getWidth(); j++){
                b = (MyBoton) this.getDefaultButton(this.getDisplayDimensions(), bm);

                //Entero que representa el color del píxel del array de píxeles
                pixel = pixels[indice];
                String text3= "pixel: " + Integer.toString(pixel);


                //Color a partir del píxel
                int color_original = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));

                //Asigna al botón el color blanco
                int color = Color.rgb(255 ,255,255 );
                b.setBackground(new ColorDrawable(color));

                //Asignamose el Listener
                //b.setOnClickListener(put_toast);
                botonera=new MyBoton[myBotonera.getFila()][myBotonera.getColumna()];
                // Guardar botones en el Array botonera;
                b.crearMiBoton(i,j,color_original);
                botonera[i][j]= b;

                //Añade el botón al GridLayout
                gridLayout.addView(b, indice);
                indice = indice+1;

            }
        }
        myBotonera.setBotonera(botonera);


    }
    /**
     * Construye un botón cuyo tamaño viene dado por el de la pantalla y el de una imagen,
     * ambos en píxeles.
     * @param dispDimensions Dimensiones de la pantalla
     * @param bm Bitmap de la imagen
     * @return
     */
    private MyBoton getDefaultButton(int[] dispDimensions, Bitmap bm) {
        int[] buttonDimensions = new int[]{ dispDimensions[0]/ (bm.getHeight()*4), dispDimensions[1]/ (bm.getWidth()*2)};
        MyBoton b = new MyBoton(this);
        b.setMinHeight(0);
        b.setMinWidth(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonDimensions[1], buttonDimensions[0]);
        params.setMargins(1, 1, 1, 1);
        b.setLayoutParams(params);

        return b;
    }


}
