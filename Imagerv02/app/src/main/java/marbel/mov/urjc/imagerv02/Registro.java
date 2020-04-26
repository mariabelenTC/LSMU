package marbel.mov.urjc.imagerv02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import marbel.mov.urjc.imagerv02.ComandosBD.ComandosBD;

public class Registro extends AppCompatActivity {

    EditText campoNombre,campoUsuario,campoPassword,campoEdad;
    Button aceptRegistro;
    Intent intchange;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        aceptRegistro = (Button) findViewById(R.id.btn_aceptar);



        campoNombre = (EditText) findViewById(R.id.EditT_nombre);
        campoUsuario = (EditText) findViewById(R.id.EditT_usuario);
        campoPassword = (EditText) findViewById(R.id.EditT_password);
        campoEdad = (EditText) findViewById(R.id.EditT_edad);

    }

    public void onClick(View view){
        registrarUsuarios();

        intchange =new Intent(this,MainActivity.class);

        this.startActivity(intchange);

    }

    private void registrarUsuarios(){
        ConnexionSQLiteHelper conn =new ConnexionSQLiteHelper(this);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(ComandosBD.FeedEntry.CAMPO_NICK,campoUsuario.getText().toString());
        values.put(ComandosBD.FeedEntry.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(ComandosBD.FeedEntry.CAMPO_PASSWORD,campoPassword.getText().toString());
        values.put(ComandosBD.FeedEntry.CAMPO_EDAD,campoEdad.getText().toString());
        values.put(ComandosBD.FeedEntry.CAMPO_DRAW,0);
        values.put(ComandosBD.FeedEntry.CAMPO_ORDER,0);

        Long id_Resultado=db.insert(ComandosBD.FeedEntry.TABLA_USUARIO,ComandosBD.FeedEntry.CAMPO_NICK,values);
        Toast.makeText(getApplicationContext(),"usuario " + id_Resultado,Toast.LENGTH_SHORT).show();
        db.close();
    }
}
