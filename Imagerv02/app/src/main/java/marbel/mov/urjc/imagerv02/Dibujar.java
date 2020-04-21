package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Dibujar extends AppCompatActivity {
    private static final String TAG = "condicion2";
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar);



        bundle=getIntent().getExtras();

        if(bundle!=null){
            Integer nivel=bundle.getInt("nivel",0);

            Log.v(TAG, ("nivel: "+ Integer.toString(nivel)));
            switch (nivel){
                case 0:
                    Log.v(TAG, ("nivel 0"));



                    break;
                case 1:
                    Log.v(TAG, ("nivel 1"));

                    break;
                case 2:

                    Log.v(TAG, ("nivel 2"));
                    break;

            }
        }
    }
}
