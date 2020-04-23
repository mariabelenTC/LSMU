package marbel.mov.urjc.imagerv02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class Dificultad extends AppCompatActivity {
    private static final String TAG = "condicion";
    Bundle bundle;
    LinearLayout contenedor;
    private Intent intchange;
    private ImageButton next;
    private String opcion,nombreUser;
    private Change_Activity change,change2;
    private Integer nivel=0;

    List<Integer> mImagesIds= Arrays.asList(R.drawable.cuatrocoloresboton,R.drawable.marioboton,R.drawable.pikachuboton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultad);

        contenedor=findViewById(R.id.ContenedorDificultad);
        next=findViewById(R.id.BTN_next);
        bundle=getIntent().getExtras();

        if(bundle!=null){
            opcion=bundle.getString("opcion"," ");
            nombreUser=bundle.getString("nombre"," ");
            Log.v(TAG, ("opcion: "+ opcion));
            switch (opcion){
                case "Dibujar":
                    Log.v(TAG, ("hoallal"));
                    agregarImageButtons(mImagesIds,contenedor);
                    break;
                case "Puzzle":
                    agregarImageButtons(mImagesIds,contenedor);
                    break;
            }
        }
    }



    private void agregarImageButtons(List<Integer>  list,LinearLayout contenedor){
        int elementos = list.size();

        for (int i= 0; i<elementos; i++){
            //creando un objeto de la clase Imagebutton
            ImageButton imboton = new ImageButton(this);

            //Personalizando botones
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(
                    /*width*/ ViewGroup.LayoutParams.WRAP_CONTENT,
                    /*height*/ ViewGroup.LayoutParams.WRAP_CONTENT
            );
            parametros.setMargins(0,0,0,0);
            imboton.setLayoutParams(parametros);
            imboton.setId(i);
            imboton.setImageResource(list.get(i));
            imboton.setOnClickListener(misEventosImageButton);
            contenedor.addView(imboton);
        }
        setContentView(contenedor);
    }

    private View.OnClickListener misEventosImageButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //v (View)  es un  ImamgeButton
            ImageButton objBoton = (ImageButton) v;
            nivel=v.getId();
            Log.v(TAG, ("nivel al pulsar: "+ Integer.toString(nivel) +" opcion: " + opcion));
            change=new Change_Activity(Dificultad.this,opcion,nivel,nombreUser);

            String text="nivel "+Integer.toString(nivel);
            Toast.makeText(Dificultad.this, text, Toast.LENGTH_SHORT).show();

            next.setOnClickListener(change);
        }

    };



}
