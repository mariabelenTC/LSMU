package marbel.mov.urjc.colorea_marbel1;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class Temporizador extends CountDownTimer{
    int tiempo;
    boolean timeRunning;
    long timeleftinMilliseconds,countDownInterval;
    private CountDownTimer countDownTimer;
    public TextView countadown_Text;
    Puntuacion scr;
    MainActivity mainWin;


    public Temporizador(MainActivity w, long tlm, long cDI, boolean tr,int t,Puntuacion sc){
        super(tlm,cDI);
        mainWin=w;
        this.timeleftinMilliseconds=tlm;
        this.countDownInterval=cDI;
        this.timeRunning=tr;
        this.tiempo=t;
        this.scr=scr;



    }
    @Override
    public void onTick(long millisUntilFinished) {
        timeleftinMilliseconds=millisUntilFinished;
        updateTimer();

    }

    @Override
    public void onFinish() {
        timeRunning=false;
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(mainWin, "time finished", time);
        msg.show();
        scr.getScore();

    }



    public void restartTime(){
        timeleftinMilliseconds=tiempo;
        if(timeRunning){
            timeRunning=false;
            countDownTimer.cancel();;
            updateTimer();
        }

    }
    public void updateTimer(){
        int minutes=(int) timeleftinMilliseconds/60000;
        int seconds=(int) timeleftinMilliseconds % 60000/1000;
        String timelefText;
        timelefText = "" + minutes;
        timelefText+=":";
        if (seconds<2) timelefText +="0";
        timelefText+=seconds;
        countadown_Text.setText(timelefText);
    }


}

