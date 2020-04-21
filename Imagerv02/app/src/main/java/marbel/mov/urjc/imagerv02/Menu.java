package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    ImageButton btn_Dibujar,btn_Puzzzle;
    Change_Activity changeDibujar,changePuzzle;

    TextView welcomeUser;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_Dibujar=(ImageButton) findViewById(R.id.BTN_Dibujar);
        btn_Puzzzle=(ImageButton) findViewById(R.id.BTN_PuzZle);

        changeDibujar= new Change_Activity(this);
        changePuzzle= new Change_Activity(this);
        btn_Dibujar.setOnClickListener(changeDibujar);
        btn_Puzzzle.setOnClickListener(changePuzzle);

        welcomeUser=(TextView) findViewById(R.id.tv_welcomeName);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            String User=bundle.getString("nombre"," ");
            welcomeUser.setText(User);
        }

    }

}
