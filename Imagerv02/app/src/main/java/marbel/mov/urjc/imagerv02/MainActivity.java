package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.ComandosBD.ComandosBD;

public class MainActivity extends AppCompatActivity {
    private static final String TAG1 = "basedatos";
    TextView tv_registrar;
    Button btn_empezar;
    EditText campoUsuario,campoPassword;


    String nombreUsuario;

    ConnexionSQLiteHelper conn;
    Change_Activity changeRegistro,changeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_registrar=findViewById(R.id.btn_reg);
        btn_empezar=findViewById(R.id.btn_empezar);

        campoUsuario= (EditText) findViewById(R.id.tv_usuario);
        campoPassword= (EditText) findViewById(R.id.tv_password);


        changeRegistro= new Change_Activity(MainActivity.this,"aRegistro");
        tv_registrar.setOnClickListener(changeRegistro);

        //changeMenu= new Change_Activity(MainActivity.this,"aMenu");


        conn =new ConnexionSQLiteHelper(this, "bd usuarios",null,1);

    }
    public void onClick(View view){
        consultar();

    }
    private void  changeActivity(){
        Intent intchange;
        intchange =new Intent(this,Menu.class);
        intchange.putExtra("nombre", nombreUsuario );

        this.startActivity(intchange);



    }
    private void consultar(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] campos={ComandosBD.CAMPO_NOMBRE};
        String[] parametros;



        parametros= new String[]{campoUsuario.getText().toString()};
        String txt = "usuario " + parametros[0] ;
        Log.v(TAG1, txt);

        try {
            /* */

            Cursor cursor =db.query(ComandosBD.TABLA_USUARIO,campos,ComandosBD.CAMPO_NICK+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            nombreUsuario=cursor.getString(0);

            cursor.close();

            changeActivity();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"No existe el usuario",Toast.LENGTH_LONG).show();

        }
    }
}
