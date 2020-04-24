package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TopScore extends AppCompatActivity {

    private TextView score1,score2,score3,player1,player2,player3,tv_mess;
    private String nameJugador,modoJuego, mensaje;
    private int scoreJugador;
    private Button btn_VolverMenu;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);



        btn_VolverMenu=(Button) findViewById(R.id.BTN_goMenu);


        score1=(TextView) findViewById(R.id.tv_score1);
        score2=(TextView) findViewById(R.id.tv_score2);
        score3=(TextView) findViewById(R.id.tv_score3);

        player1=(TextView) findViewById(R.id.tv_jugador1);
        player2=(TextView) findViewById(R.id.tv_jugador2);
        player3=(TextView) findViewById(R.id.tv_jugador3);

        tv_mess=(TextView) findViewById(R.id.mgs);


        bundle=getIntent().getExtras();
        if(bundle!=null){
            nameJugador=bundle.getString("nombre"," ");
            modoJuego=bundle.getString("juego", " ");
            scoreJugador=bundle.getInt("score", 0);

            mensaje="Tu puntuación es de " + Integer.toString(scoreJugador) + " "+ nameJugador+" en: "+ modoJuego;

            tv_mess.setText(mensaje);

        }
    }
}
