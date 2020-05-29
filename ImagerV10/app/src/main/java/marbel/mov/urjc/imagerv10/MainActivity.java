package marbel.mov.urjc.imagerv10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

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
        Toast.makeText(getApplicationContext(),"manejar usuarios desde el activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void consultInformation() {
        Toast.makeText(getApplicationContext(),"consult info desde el activity", Toast.LENGTH_SHORT).show();

    }
}
