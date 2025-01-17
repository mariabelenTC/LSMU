package marbel.mov.urjc.colorea_marbel1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private static final String TAG1 = "colores";

    private int seekR=0, seekG=0, seekB=0;
    private int tiempo=240000; // tiempo inicial en milisegundos -> 3 minuto = 3*60*1000
    private long timeleftinMilliseconds=tiempo;
    private long countDownInterval=1000;
    private boolean  timeRunning=false;
    private int time = Toast.LENGTH_SHORT;

    private  int totalBotones;
    private  int fila=0, columna=0;

    private MyBoton[][] botonera;
    private TextView countadown_Text;
    private Button boton_Start, boton_Restart, boton_finisht, boton_help;
    private CountDownTimer countDownTimer;

    //Instanciar la clase
    Puntuacion score = new Puntuacion(MainActivity.this,totalBotones,fila,columna,botonera,time);

    Botonera bot=new Botonera(botonera,fila,columna);
    final Temporizador temp=new Temporizador(this,timeleftinMilliseconds,countDownInterval,timeRunning,tiempo,score);

    private View.OnClickListener put_toast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start:
                    timeleftinMilliseconds = tiempo;
                    //Log.v(TAG1,("pulsado start"));
                    bot.restartColorbotons();
                    temp.start();
                    timeRunning=true;
                    break;

                case R.id.restart:
                    //Log.v(TAG1, ("pulsar restart"));
                    bot.restartColorbotons();
                    temp.restartTime();
                    break;
                case R.id.finish:
                    if (timeRunning) {

                        countDownTimer.cancel();
                        score.getScore();
                        timeRunning = false;

                    } else {
                        Toast msg = Toast.makeText(MainActivity.this, "press START to play the game", time);
                        msg.show();

                    }
                    break;
                case R.id.help:
                    if (timeRunning == false) {
                        bot.helpColorbotons();
                        Toast msg = Toast.makeText(MainActivity.this, "help", time);
                        msg.show();

                    } else {
                        Toast msg = Toast.makeText(MainActivity.this, "not allowed", time);
                        msg.show();
                    }

                default:
                    put_toasts(v);
                    break;
            }

        }



    };


    private void put_toasts(View v){
        MyBoton b= (MyBoton) v;
        int time = Toast.LENGTH_SHORT;
        int color = Color.rgb(seekR, seekG, seekB);
        if (timeRunning){
            b.setBackground(new ColorDrawable(color));
            botonera[b.getPos_x()][b.getPos_y()].setColor_play(color);

            Log.v(TAG1,("color original: "+ Integer.toString(b.getColor())));
            Log.v(TAG1,("Color del jugador: "+ Integer.toString(b.getColor_play())));

            Toast msg = Toast.makeText(MainActivity.this, "color added", time);
            msg.show();

        }else{
            Toast msg = Toast.makeText(MainActivity.this, "press START to play the game", time);
            msg.show();
        }

    }




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
        GridLayout gridLayout = this.getDefaultGridLayout(bm);

        this.setGridLayoutButtons(gridLayout, bm);

        // Añade el layout hijo al layout padre.
        parentLayout.addView(gridLayout);


        // Añade los tres seekbar
        SeekBar sbR = (SeekBar) findViewById(R.id.RedSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.GreenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.BlueSeekBar);

        SeekBar_Listener seek = new SeekBar_Listener(this,seekR,seekG,seekB);
        // Asigna el listenner a los seekbar
        sbR.setOnSeekBarChangeListener(seek);
        sbG.setOnSeekBarChangeListener(seek);
        sbB.setOnSeekBarChangeListener(seek);

    }


    /**
     * Añade botones a un gridLayout con los colores de una imagen.
     * @param gridLayout GridLayout
     * @param bm Bitmap de la imagen
     */
    private void setGridLayoutButtons(GridLayout gridLayout, Bitmap bm) {
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
                b = (MyBoton) this.getDefaultButton(this.getDisplayDimensions(), bm);

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
                botonera= bot.addBottons(b,i, j, color_original);

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

