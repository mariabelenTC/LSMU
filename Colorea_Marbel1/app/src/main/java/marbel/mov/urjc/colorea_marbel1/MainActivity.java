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

    private static final String TAG = "variables";
    private static final String TAG1 = "colores";
    private static final String TAG2 = "puntuacion";
    private static final String TAG3 = "score_botton";
    private static final String TAG4 = "rgb_score";

    public int seekR=0, seekG=0, seekB=0, color_muestra;
    public int tiempo=240000; // tiempo inicial en milisegundos -> 3 minuto = 3*60*1000
    public long timeleftinMilliseconds=tiempo, countDownInterval=1000;

    public TextView countadown_Text;
    public boolean  timeRunning=false;
    public CountDownTimer countDownTimer;
    public int time = Toast.LENGTH_SHORT;

    public   int totalBotones;
    public   int fila=0, columna=0;
    public   Temporizador temporizador;
    public MyBoton[][] botonera;

    public Button boton_Start, boton_Restart, boton_finisht, boton_help;


    /**
     * Obtiener la puntuacion de un elemento del color (R,G,B)
     * @param color_original el color que deberia tener
     * @param color_asignado el color que asigna el jugador.
     * @return
     */
    private int score(int color_original, int color_asignado){
        float resta = Math.abs(color_original - color_asignado);
        Log.v(TAG4, ("resta: " + Float.toString(resta)));
        float score = (float)((-510/255)* (int)resta +510);
        //float score = (float) ((-100/255)*resta+100);
        Log.v(TAG4, ("calcular score: " + Integer.toString((int)score)));
        return (int)score;
    }
    /**
     * Obtener puntuacion de todos los botones
     * y calcular si se ha superado el minimo o no. 644267769
     */
    private void getScore(){
        int total_score=0;
        int score_max=1530*totalBotones;
        Log.v(TAG2, ("puntuacion Maxima: " + Integer.toString(score_max)));

        int colorR_score, colorG_score, colorB_score, score_boton;

        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {


                Log.v(TAG3, ("posicion: (" + Integer.toString(i) +
                        ", " + Integer.toString(j) + ") "));

                int r = Color.red(botonera[i][j].getColor());
                int g = Color.green(botonera[i][j].getColor());
                int b = Color.blue(botonera[i][j].getColor());
                Log.v(TAG3, ("red_o: " + Integer.toString(r) +
                        " green_o: " + Integer.toString(g) +
                        " blue_o: " + Integer.toString(b)));


                int r_p = Color.red(botonera[i][j].getColor_play());
                int g_p = Color.green(botonera[i][j].getColor_play());
                int b_p = Color.blue(botonera[i][j].getColor_play());
                Log.v(TAG3, ("red_play: " + Integer.toString(r_p) +
                        " green_play: " + Integer.toString(g_p) +
                        " blue_play: " + Integer.toString(b_p)));


                colorR_score = score(r, r_p);
                colorG_score = score(g, g_p);
                colorB_score = score(b, b_p);
                Log.v(TAG3, ("score_R: " + Integer.toString(colorR_score) +
                        " score G: " + Integer.toString(colorG_score) +
                        " score_B: " + Integer.toString(colorB_score)));

                score_boton = colorR_score + colorG_score + colorB_score;
                Log.v(TAG3, ("score del boton: " + Integer.toString(score_boton)));

                total_score = total_score + score_boton;
            }
        }
        int min=(score_max *8)/9;
        Log.v(TAG2,("puntuación minima: " + Integer.toString(min)));

        if (total_score>= ((score_max *8)/9)){

            Log.v(TAG2,("has ganado y tu puntación es: " + Integer.toString(total_score)));

            String msg_w = ("::: WINNER :::  score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(MainActivity.this, msg_w, time);
            msg.show();

        }else {

            Log.v(TAG2,("has perdido y tu puntación es: " + Integer.toString(total_score)));
            
            String msg_l = ("::: LOSER :::score"  + Integer.toString(total_score));
            Toast msg = Toast.makeText(MainActivity.this, msg_l, time);
            msg.show();
        }
    }

    private View.OnClickListener put_toast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.start:
                    //Log.v(TAG1,("pulsado start"));
                    restartColorbotons();
                    //temporizador=new Temporizador(MainActivity.this,timeleftinMilliseconds,countDownInterval);
                    timeRunning=true;
                    //temporizador.start();

                case R.id.restart:
                    //Log.v(TAG1, ("pulsar restart"));

                    if(timeRunning){
                        restartColorbotons();
                        //temporizador.restartTime();

                    }

                case R.id.finish:
                    if (timeRunning) {

                        //temporizador.cancel();
                        //getScore();
                        timeRunning = false;

                    } else {
                        Toast msg = Toast.makeText(MainActivity.this, "press START to play the game", time);
                        msg.show();

                    }
                default:
                    put_toasts(v);

            }
        }
    };


    private void restartColorbotons(){
        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                //Asigna al botón el color blanco
                int color = Color.rgb(255, 255, 255);
                botonera[i][j].setBackground(new ColorDrawable(color));
                botonera[i][j].setColor_play(color);
            }
        }

    }
    private void helpColorbotons(){
        for(int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                //Asigna al botón el color blanco
                int color =botonera[i][j].getColor();
                botonera[i][j].setBackground(new ColorDrawable(color));
            }
        }


    }
    /*
    private void startTime(){
        countDownTimer = new CountDownTimer(timeleftinMilliseconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinMilliseconds=millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {
                timeRunning=false;
                int time = Toast.LENGTH_SHORT;
                Toast msg = Toast.makeText(MainActivity.this, "time finished", time);
                msg.show();
                getScore();


            }
        }.start();
        timeRunning=true;
    }
    private void restartTime(){
        timeleftinMilliseconds=tiempo;
        if(timeRunning){
            timeRunning=false;
            countDownTimer.cancel();
            updateTimer();
        }

    }
    private void updateTimer(){
        int minutes=(int) timeleftinMilliseconds/60000;
        int seconds=(int) timeleftinMilliseconds % 60000/1000;
        String timelefText;
        timelefText = "" + minutes;
        timelefText+=":";
        if (seconds<2) timelefText +="0";
        timelefText+=seconds;
        countadown_Text.setText(timelefText);
    }

     */


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


        Button muestra= (Button) findViewById(R.id.muestra_color);


        Colores seek = new Colores(this,muestra);
        // Asigna el listenner a los seekbar
        sbR.setOnSeekBarChangeListener(seek);
        sbG.setOnSeekBarChangeListener(seek);
        sbB.setOnSeekBarChangeListener(seek);




    }



    /**
     * Añade Myboton a un arraybidimensional.
     * @param boton boton de tipo MyBoton que quiero guardar
     * @param x fila
     * @param y columna
     * @param color_original color del pixel que deberia tener.
     * @return
     *
     */
    private MyBoton[][] addBottons(MyBoton boton, int x, int y, int color_original) {

        boton.setPos_x(x);
        boton.setPos_y(y);
        boton.setColor(color_original);
        boton.setColor_play(-1);
        botonera[x][y]=boton;

        Log.v(TAG1,("x:"+ Integer.toString(x)+ " y: " + Integer.toString(y)));
        return botonera;

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
                botonera= this.addBottons(b,i, j, color_original);

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

