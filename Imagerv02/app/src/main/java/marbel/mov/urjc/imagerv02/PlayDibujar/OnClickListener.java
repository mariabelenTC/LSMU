package marbel.mov.urjc.imagerv02.PlayDibujar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.Change_Activity;
import marbel.mov.urjc.imagerv02.ConnexionSQLiteHelper;
import marbel.mov.urjc.imagerv02.R;
import marbel.mov.urjc.imagerv02.TopScore;

public class OnClickListener implements View.OnClickListener {
    private Dibujar m;
    private static final String TAG1 = "colores";
    private Intent intchange;
    private ConnexionSQLiteHelper conn;
    private int time = Toast.LENGTH_SHORT;
    private String name;
    private Change_Activity change;


    public OnClickListener(Dibujar main ,String name){
        m=main;
        this.name=name;

    }

    public void put_toasts(View v){
        MyBoton b= (MyBoton) v;

        int time = Toast.LENGTH_SHORT;

        int color = Color.rgb(m.seekR, m.seekG, m.seekB);
        if (m.timeRunning){
            b.setBackground(new ColorDrawable(color));
            m.botonera[b.getPos_x()][b.getPos_y()].setColor_play(color);

        }else{
            Toast msg = Toast.makeText(m, "press START to play the game", time);
            msg.show();
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.start:
                //Log.v(TAG1,("pulsado start"));
                m.bot.restartColorbotons();

                m.timeRunning=true;
                m.temporizador.start();
                break;

            case R.id.restart:
                //Log.v(TAG1, ("pulsar restart"));

                m.bot.restartColorbotons();
                m.temporizador.restartTime();
                m.temporizador.cancel();
                break;
            case R.id.finish:

                if (m.timeRunning) {
                    //consultar();
                    //actualizarScore();
                    m.temporizador.cancel();
                    int score= m.score.getScore();
                    m.timeRunning = false;
                    intchange =new Intent(m, TopScore.class);
                    intchange.putExtra("juego", "Dibujar");
                    intchange.putExtra("nombre", name);
                    intchange.putExtra("score", score);
                    m.startActivity(intchange);

                } else {
                    Toast msg = Toast.makeText(m, "press START to play the game", time);
                    msg.show();

                }
                break;
            case R.id.help:
                if(m.timeRunning==false){
                    m.bot.helpColorbotons();
                    Toast msg = Toast.makeText(m, "help", time);
                    msg.show();

                }else{
                    Toast msg = Toast.makeText(m, "not allowed", time);
                    msg.show();
                }
                break;

            default:
                put_toasts(v);
                break;
        }


    }







}
