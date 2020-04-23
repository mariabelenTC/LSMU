package marbel.mov.urjc.imagerv02.PlayPuzzle;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.Change_Activity;
import marbel.mov.urjc.imagerv02.R;
import marbel.mov.urjc.imagerv02.TopScore;

class OnClickLisnerPuzzle implements View.OnClickListener {
    private int time = Toast.LENGTH_SHORT;
    private static final String TAG1 = "puzleBotones";
    Puzzle p;
    private int vecesPulsado=0;
    private Intent intchange;
    private  PiezaBoton b2;
    private int x1=0,y1=0,x2,y2;
    private int color1=0,color2=0;
    private Change_Activity changeTopscore;
    private String nameUser;

    private  ScorePuzzle score;
    public OnClickLisnerPuzzle(Puzzle puzzle, String nameUsuario) {
        p=puzzle;
        nameUser=nameUsuario;

    }
    private void put_toasts(View v){
        PiezaBoton b= (PiezaBoton) v;

        vecesPulsado+=1;

        int time = Toast.LENGTH_SHORT;

        if (vecesPulsado%2==0){

            x2=b.getPos_x();
            y2=b.getPos_y();
            color2=p.botonera[x2][y2].getColorPlay();

            p.botonera[x2][y2].setColorPlay(color1);
            p.botonera[x1][y1].setColorPlay(color2);

            p.botonera[x2][y2].setBackground(new ColorDrawable(color1));
            p.botonera[x1][y1].setBackground(new ColorDrawable(color2));

            Log.v(TAG1, "boton1: (" + Integer.toString(x1) +","+ Integer.toString(y1) +") y color: " + Integer.toString(color1));

            Log.v(TAG1,"boton2: (" + Integer.toString(x2) +","+ Integer.toString(y2) +") y color: " + Integer.toString(color2));

            Toast msg3 = Toast.makeText(p, "boton2", time);
            msg3.show();

        }else{
            x1=b.getPos_x();
            y1=b.getPos_y();
            b2=b;
            color1=p.botonera[x1][y1].getColorPlay();

            Log.v(TAG1, "boton1: (" + Integer.toString(x1) +","+ Integer.toString(y1) +") y color: " + Integer.toString(color1));

            Toast msg = Toast.makeText(p, "boton1", time);
            msg.show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.puzzleStart:
                //Log.v(TAG1,("pulsado start"));

                p.timeRunning=true;
                p.temporizador.start();

                break;
            case R.id.puzzleFinish:
                score=new ScorePuzzle(p.botonera,p.fila,p.columna);

                if (p.timeRunning) {
                    //consultar();
                    //actualizarScore();

                    int scor= score.getScore();
                    changeTopscore=new Change_Activity(p,nameUser,scor);
                    p.timeRunning = false;
                    p.temporizador.cancel();
                    intchange =new Intent(p, TopScore.class);
                    intchange.putExtra("nombre", nameUser);
                    intchange.putExtra("score", scor);
                    p.startActivity(intchange);

                } else {
                    Toast msg = Toast.makeText(p, "press START to play the game", time);
                    msg.show();

                }
                break;

            default:
                put_toasts(v);
                break;

        }
    }


}
