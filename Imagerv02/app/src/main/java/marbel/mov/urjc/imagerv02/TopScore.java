package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import marbel.mov.urjc.imagerv02.BD.Usuario;
import marbel.mov.urjc.imagerv02.ComandosBD.ComandosBD;

public class TopScore extends AppCompatActivity {

    private TextView score1,score2,score3,player1,player2,player3,tv_mess;
    private String nameJugador,modoJuego, mensaje;
    private int scoreJugador;
    private Button btn_VolverMenu;
    private ConnexionSQLiteHelper conn;
    private Change_Activity changeRegistro;

    private Bundle bundle;
    private Intent intchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);


        conn =new ConnexionSQLiteHelper(this, "bd usuarios",null,1);

        btn_VolverMenu=(Button) findViewById(R.id.BTN_goMenu);


        score1=(TextView) findViewById(R.id.tv_score1);
        score2=(TextView) findViewById(R.id.tv_score2);
        score3=(TextView) findViewById(R.id.tv_score3);

        player1=(TextView) findViewById(R.id.tv_jugador1);
        player2=(TextView) findViewById(R.id.tv_jugador2);
        player3=(TextView) findViewById(R.id.tv_jugador3);
        TextView[] textVPlayers= new TextView[]{player1,player2,player3};
        TextView[] textVScores= new TextView[]{score1,score2,score3};

        tv_mess=(TextView) findViewById(R.id.mgs);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            nameJugador=bundle.getString("nombre"," ");
            modoJuego=bundle.getString("juego", " ");
            scoreJugador=bundle.getInt("score", 0);

            mensaje="Tu puntuación es de " + Integer.toString(scoreJugador) + "puntos en el juego de: "+ modoJuego;

            tv_mess.setText(mensaje);
            actualizarPuntuacion(nameJugador, modoJuego,scoreJugador);
            printTop(modoJuego,textVPlayers,textVScores);

        }
    }
    public void onClick(View view){

        intchange = new Intent(this,MainActivity.class );

        this.startActivity(intchange);

    }

    private void printTop( String modoJuego, TextView[] name, TextView[] score){

        Cursor cursor;
        SQLiteDatabase db = conn.getReadableDatabase();
        int i=0;
        String query;


        if (modoJuego.equals("Dibujar")){
            query="SELECT "+ComandosBD.CAMPO_NICK + ", " + ComandosBD.CAMPO_DRAW+ " FROM "+ ComandosBD.TABLA_USUARIO+ " ORDER BY " +  ComandosBD.CAMPO_DRAW + " DESC LIMIT 3" ;
            cursor =db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                do{
                    name[i].setText(cursor.getString(cursor.getColumnIndex(ComandosBD.CAMPO_NICK)));
                    score[i].setText(cursor.getString(cursor.getColumnIndex(ComandosBD.CAMPO_DRAW)));
                    i+=1;
                }while(cursor.moveToNext());
            }

        }else {
            query="SELECT "+ComandosBD.CAMPO_NICK + ", " + ComandosBD.CAMPO_ORDER+ " FROM "+ ComandosBD.TABLA_USUARIO+ " ORDER BY " +  ComandosBD.CAMPO_ORDER+ " DESC LIMIT 3";
            cursor =db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                do{
                    name[i].setText(cursor.getString(cursor.getColumnIndex(ComandosBD.CAMPO_NICK)));
                    score[i].setText(cursor.getString(cursor.getColumnIndex(ComandosBD.CAMPO_ORDER)));
                    i+=1;
                }while(cursor.moveToNext());
            }

        }

        cursor.close();
        db.close();

    }

    private void actualizarPuntuacion(String nombreUsuario, String modoJuego,Integer score){

        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros= new String[]{nombreUsuario};
        ContentValues values =new ContentValues();
        if (modoJuego.equals("Dibujar")){

            values.put(ComandosBD.CAMPO_DRAW,score);
        }else{
            values.put(ComandosBD.CAMPO_ORDER,score);
        }
        db.update(ComandosBD.TABLA_USUARIO,values,ComandosBD.CAMPO_NICK+"=?",parametros);


        db.close();
    }
}
