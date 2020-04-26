package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import marbel.mov.urjc.imagerv02.BD.Usuario;
import marbel.mov.urjc.imagerv02.ComandosBD.ComandosBD;

public class TopScore extends AppCompatActivity {

    private static final String TAG = "cursor";
    private TextView score1,score2,score3,player1,player2,player3,tv_mess;
    private String nameJugador,modoJuego, mensaje;
    private int scoreJugador;
    private Button btn_VolverMenu;
    ConnexionSQLiteHelper conn;
    private Change_Activity changeRegistro;


    private Bundle bundle;
    private JugadorScore[] listdatos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);
        ArrayList<Usuario> lisjugadores;

        conn =new ConnexionSQLiteHelper(this);

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
            actualizarPuntuacion(nameJugador, modoJuego,scoreJugador);
            lisjugadores= getlistUserInfo(modoJuego);
            printTop(lisjugadores);


        }
    }
    private void printTop( ArrayList<Usuario> listR){


    }


    private ArrayList<Usuario> getlistUserInfo( String modoJuego){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        Cursor cursor;
        SQLiteDatabase db = conn.getWritableDatabase();
        Usuario usuario=null;
        String[] columnas={ComandosBD.FeedEntry.CAMPO_NICK,ComandosBD.FeedEntry.CAMPO_DRAW,ComandosBD.FeedEntry.CAMPO_ORDER};

        String sortOrder;


        if (modoJuego.equals("Dibujar")){
            sortOrder =
                    ComandosBD.FeedEntry.CAMPO_DRAW + " DESC";

        }else {
            sortOrder =
                    ComandosBD.FeedEntry.CAMPO_ORDER+ " DESC";

        }
        try {

            cursor =db.query(
                ComandosBD.FeedEntry.TABLA_USUARIO,
                columnas,
                null,
                null,
                null,
                null,
                sortOrder
            );

            Log.v(TAG,Integer.toString(cursor.getColumnCount()));
            /*
            while (cursor.moveToNext()){
                usuario.setUsario(cursor.getString(0));
                usuario.setDraw(cursor.getInt(4));
                usuario.setOrder(cursor.getInt(5));
                lista.add(usuario);

            }
            */

            cursor.close();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"No existe el usuario",Toast.LENGTH_LONG).show();

        }

        return lista;

    }


    private void actualizarPuntuacion(String nombreUsuario, String modoJuego,Integer score){

        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros= new String[]{nombreUsuario};
        ContentValues values =new ContentValues();
        if (modoJuego.equals("Dibujar")){

            values.put(ComandosBD.FeedEntry.CAMPO_DRAW,score);
        }else{
            values.put(ComandosBD.FeedEntry.CAMPO_ORDER,score);
        }
        db.update(ComandosBD.FeedEntry.TABLA_USUARIO,values,ComandosBD.FeedEntry.CAMPO_NICK+"=?",parametros);


        db.close();;
    }
}

