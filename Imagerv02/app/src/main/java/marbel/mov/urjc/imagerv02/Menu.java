package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    private ImageButton btn_Dibujar,btn_Puzzzle;
    private Change_Activity changeDibujar,changePuzzle;
    private static final String TAG = "namesr";

    private TextView welcomeUser;

    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_Dibujar=(ImageButton) findViewById(R.id.BTN_Dibujar);
        btn_Puzzzle=(ImageButton) findViewById(R.id.BTN_PuzZle);



        welcomeUser=(TextView) findViewById(R.id.tv_welcomeName);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            String nameUser=bundle.getString("nombre"," ");
            String User=bundle.getString("user"," ");
            Log.v(TAG,"usuario: " + User);
            welcomeUser.setText(nameUser);

            changeDibujar= new Change_Activity(this,User,"Dibujar",0);
            changePuzzle= new Change_Activity(this,User,"Puzzle",0);
            btn_Dibujar.setOnClickListener(changeDibujar);
            btn_Puzzzle.setOnClickListener(changePuzzle);
        }

    }

}
