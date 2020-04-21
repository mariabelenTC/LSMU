package marbel.mov.urjc.imagerv02.PlayDibujar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import marbel.mov.urjc.imagerv02.R;

public class OnClickListener implements View.OnClickListener {
    Dibujar m;
    private static final String TAG1 = "colores";

    public OnClickListener(Dibujar main){
        m=main;

    }

    public void put_toasts(View v){
        MyBoton b= (MyBoton) v;

        int time = Toast.LENGTH_SHORT;

        int color = Color.rgb(m.seekR, m.seekG, m.seekB);
        if (m.timeRunning){
            b.setBackground(new ColorDrawable(color));
            m.botonera[b.getPos_x()][b.getPos_y()].setColor_play(color);

            Log.v(TAG1,("color original: "+ Integer.toString(b.getColor())));
            Log.v(TAG1,("Color del jugador: "+ Integer.toString(b.getColor_play())));

            Toast msg = Toast.makeText(m, "color added", time);
            msg.show();

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
                    m.temporizador.cancel();
                    m.score.getScore();
                    m.timeRunning = false;

                } else {
                    Toast msg = Toast.makeText(m, "press START to play the game", m.time);
                    msg.show();

                }
                break;
            case R.id.help:
                if(m.timeRunning==false){
                    m.bot.helpColorbotons();
                    Toast msg = Toast.makeText(m, "help", m.time);
                    msg.show();

                }else{
                    Toast msg = Toast.makeText(m, "not allowed", m.time);
                    msg.show();
                }
                break;

            default:
                put_toasts(v);
                break;
        }
    }

}
