package marbel.mov.urjc.imagerv02;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ListenerNivel implements View.OnClickListener {
    private static final String TAG = "condicion";
    private Integer nivel;
    private Change_Activity change;
    private String opcion;
    private Activity main;
    private String name;

    ListenerNivel(Activity m, String op, String user){
        this.main=m;
        this.opcion=op;
        this.name=user;

    }


    @Override
    public void onClick(View v){
        //v (View)  es un  ImageButton
        ImageButton objBoton = (ImageButton) v;
        nivel=v.getId();
        change=new Change_Activity(main,opcion,nivel,name);
        Log.v(TAG, ("nivel al pulsar: "+ Integer.toString(nivel)));
        String text="nivel "+Integer.toString(nivel);
        Toast.makeText(main, text, Toast.LENGTH_SHORT).show();

        //next.setOnClickListener(change);

    }
}
