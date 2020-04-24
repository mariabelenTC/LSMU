package marbel.mov.urjc.imagerv02.PlayDibujar;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.TopScore;

public class Temporizador extends CountDownTimer {
    private Intent intchange;


    private Dibujar m;
    private String nameUser;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Temporizador(Dibujar main,long millisInFuture, long countDownInterval,String name) {
        super(millisInFuture, countDownInterval);
        m=main;
        this.nameUser=name;
    }


    @Override
    public void onFinish() {
        m.timeRunning=false;
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(m, "time finished", time);
        msg.show();
        int score= m.score.getScore();
        m.timeRunning = false;
        intchange =new Intent(m, TopScore.class);
        String juego="Dibujar";
        intchange.putExtra("juego", juego);
        intchange.putExtra("nombre", nameUser);
        intchange.putExtra("score", score);
        m.startActivity(intchange);
        m.timeRunning=true;


    }

    @Override
    public void onTick(long millisUntilFinished) {
        m.timeleftinMilliseconds=millisUntilFinished;
        updateTimer();
    }


    public void restartTime(){
        m.timeleftinMilliseconds=m.tiempo;

        if(m.timeRunning){
            m.timeRunning=false;

            cancel();
            updateTimer();

        }


    }
    public void updateTimer(){
        int minutes=(int) m.timeleftinMilliseconds/60000;
        int seconds=(int) m.timeleftinMilliseconds % 60000/1000;
        String timelefText;
        timelefText = "" + minutes;
        timelefText+=":";
        if (seconds<2) timelefText +="0";
        timelefText+=seconds;
        m.countadown_Text.setText(timelefText);
    }

}
