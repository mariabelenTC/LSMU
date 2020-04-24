package marbel.mov.urjc.imagerv02.PlayPuzzle;

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
import android.widget.TextView;
import java.util.Random;


import marbel.mov.urjc.imagerv02.R;
import static marbel.mov.urjc.imagerv02.PlayPuzzle.PiezaBoton.getDefaultButton;


public class Puzzle extends AppCompatActivity {
    private static final String TAG = "variables";

    public Button boton_Start, boton_Restart, boton_finish;
    public TextView  puzzleCountadownText;
    private Bundle bundle;
    private ImageView ImagenMuestra;
    public int tiempo; // tiempo inicial en milisegundos -> 3 minuto = 3*60*1000
    public long timeleftinMilliseconds, countDownInterval=1000;
    public int totalBotones;
    public int fila=0, columna=0;
    private Bitmap bm;
    public PiezaBoton[][] botonera;
    public DimensionesPantalla dimp=new DimensionesPantalla(this);
    public boolean  timeRunning=false;
    private OnClickLisnerPuzzle put_toast;
    private String nameUsuario;
    public TemporizadorPuzzle temporizador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        puzzleCountadownText=findViewById(R.id.puzzleContador);
        boton_Start=findViewById(R.id.puzzleStart);
        boton_finish=findViewById(R.id.puzzleFinish);
        ImagenMuestra=findViewById(R.id.puzzleMuestra);


        //Datos recibidos del Activity Dificultad
        bundle=getIntent().getExtras();

        if(bundle!=null){
            Integer nivel=bundle.getInt("nivel",0);
            nameUsuario =bundle.getString("nombre"," ");
            put_toast = new OnClickLisnerPuzzle(this,nameUsuario);
            boton_finish.setOnClickListener(put_toast);
            boton_Start.setOnClickListener(put_toast);


            Log.v(TAG, ("nivel: "+ Integer.toString(nivel)));
            switch (nivel){
                case 0:
                    Log.v(TAG, ("nivel 0"));
                    tiempo=60*1000;
                    timeleftinMilliseconds=tiempo;
                    ConstruirDiseño(R.drawable.cuatrocolores,R.drawable.cuatrocolores_r,"00:50" );


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
            temporizador=new TemporizadorPuzzle(this,timeleftinMilliseconds,countDownInterval,nameUsuario);
        }

    }
    private void ConstruirDiseño(Integer imgMuestra, Integer imgPixel,String tiempo){
        BitmapFactory.Options options;
        LinearLayout parentLayout;
        GridLayout gridLayout;

        //Imagen
        ImagenMuestra.setImageResource(imgMuestra);
        puzzleCountadownText.setText(tiempo);

       //Botonera
        options= new BitmapFactory.Options();
        options.inScaled = false;
        bm = BitmapFactory.decodeResource(getResources(), imgPixel, options);
        fila=bm.getHeight();
        columna=bm.getWidth();

        botonera = new PiezaBoton[fila][columna];

        // Layout padre
        parentLayout = findViewById(R.id.layoutBotonera);

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
        Random random = new Random();
        int pixel = 0;
        int totalPixels = bm.getHeight() * bm.getWidth();


        totalBotones=totalPixels;

        int[] pixels = new int[bm.getWidth() * bm.getHeight()];
        PiezaBoton b = null;

        //Inserta los píxeles del Bitmap (imagen) en el array pixels
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());

        int indice = 0;
        for(int i = 0; i < bm.getHeight(); i++){
            for(int j =0; j < bm.getWidth(); j++){
                b = (PiezaBoton) getDefaultButton(dimp.getDisplayDimensions(), bm, this);

                //Entero que representa el color del píxel del array de píxeles
                pixel = pixels[indice];

                // Elegimos un índice al azar, entre 0 y el total de pixeles
                //Color a partir del píxel
                int color_original = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));


                b.setColor(color_original);
                b.setColorPlay(color_original);
                // Guardar botones en el Array botonera;
                botonera[i][j]= b;
                indice +=1;

            }
        }

        addBotonBotonera(gridLayout,botonera,bm.getHeight(),bm.getWidth());


    }
    
    private void addBotonBotonera(GridLayout g, PiezaBoton[][] bot, int n, int m){
        int color;
        //Añade el botón al GridLayout
        int indice=0;
        for(int i = 0; i <n; i++) {
            for (int j = 0; j<m; j++) {
                PiezaBoton b =bot[i][j];
                color=b.getColor();
                b.setPos_x(i);
                b.setPos_y(j);
                b.setColorPlay(color);
                b.setBackground(new ColorDrawable(color));
                g.addView(b, indice);
                b.setOnClickListener(put_toast);
                indice+=1;
            }
        }

    }


}
