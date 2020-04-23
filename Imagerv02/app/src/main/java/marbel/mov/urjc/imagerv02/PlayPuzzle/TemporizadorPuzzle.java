package marbel.mov.urjc.imagerv02.PlayPuzzle;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.TopScore;

public class TemporizadorPuzzle extends CountDownTimer {
    private Intent intchange;
    private ScorePuzzle puntuacion;

    private Puzzle m;
    private String name;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TemporizadorPuzzle(Puzzle main,long millisInFuture, long countDownInterval,String nam) {
        super(millisInFuture, countDownInterval);
        m=main;
        this.name=nam;
    }


    @Override
    public void onFinish() {
        m.timeRunning=false;
        int time = Toast.LENGTH_SHORT;
        Toast msg = Toast.makeText(m, "time finished", time);
        msg.show();
        puntuacion=new ScorePuzzle(m.botonera,m.fila,m.columna);
        int score= puntuacion.getScore();

        intchange =new Intent(m, TopScore.class);
        String juego="puzzle";
        intchange.putExtra("juego",juego);
        intchange.putExtra("nombre", name);
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
        m.puzzleCountadownText.setText(timelefText);
    }

}
