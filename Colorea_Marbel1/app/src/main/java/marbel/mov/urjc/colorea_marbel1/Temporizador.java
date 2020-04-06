package marbel.mov.urjc.colorea_marbel1;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class Temporizador extends CountDownTimer {


    MainActivity m;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Temporizador(MainActivity main,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        m=main;
    }


    @Override
    public void onFinish() {
        m.timeRunning=false;
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(m, "time finished", time);
        msg.show();
        m.score.getScore();
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
