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

    @SuppressLint("WrongViewCast")
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
        ConnexionSQLiteHelper conn =new ConnexionSQLiteHelper(this, "bd usuarios",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(ComandosBD.CAMPO_NICK,campoUsuario.getText().toString());
        values.put(ComandosBD.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(ComandosBD.CAMPO_PASSWORD,campoPassword.getText().toString());
        values.put(ComandosBD.CAMPO_EDAD,campoEdad.getText().toString());

        Long id_Resultado=db.insert(ComandosBD.TABLA_USUARIO,ComandosBD.CAMPO_NICK,values);
        Toast.makeText(getApplicationContext(),"usuario " + id_Resultado,Toast.LENGTH_SHORT).show();
        db.close();
    }
}
