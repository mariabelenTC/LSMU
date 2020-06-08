package marbel.mov.urjc.imagerv10;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import marbel.mov.urjc.imagerv10.activitys.InstruccionesActivity;
import marbel.mov.urjc.imagerv10.fragments.InicioFragment;
import marbel.mov.urjc.imagerv10.interfaces.ReportFragments;

public class MainActivity extends AppCompatActivity implements ReportFragments, InicioFragment.OnFragmentInteractionListener{

    Fragment fragmetInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmetInicio= new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmetInicio).commit();


    }

    public AlertDialog dialogEditUser(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Editar Usuarios");
        builder.setMessage("Indica si quieres registrarte o iniciar sesión.\n\n Puedes editar un jugador desde la opción EDITAR");
        builder.setNegativeButton("REGISTRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"registrar jugador,", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"editar jugador,", Toast.LENGTH_SHORT).show();


            }
        });

        return builder.create();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void startGamePuzzle() {
        Toast.makeText(getApplicationContext(),"llamar juego puzzle desde el activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void starGameDraw() {
        Toast.makeText(getApplicationContext(),"llamar juego dibujar desde el activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void callSettings() {
        Toast.makeText(getApplicationContext(),"llamar ajustes desde el activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void consultRanking() {
        Toast.makeText(getApplicationContext(),"consultar ranking desde el activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void consultInstruccions() {
        Intent miIntent = new Intent(MainActivity.this, InstruccionesActivity.class);
        startActivity(miIntent);
    }

    @Override
    public void editUsers() {
        dialogEditUser().show();

    }

    @Override
    public void consultInformation() {
        Toast.makeText(getApplicationContext(),"consult info desde el activity", Toast.LENGTH_SHORT).show();

    }
}
