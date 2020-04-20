package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView welcomeUser;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        welcomeUser=(TextView) findViewById(R.id.tv_welcomeName);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            String User=bundle.getString("nombre"," ");
            welcomeUser.setText(User);
        }

    }
}
