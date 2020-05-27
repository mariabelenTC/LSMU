package marbel.mov.urjc.imagerv10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import marbel.mov.urjc.imagerv10.fragments.InicioFragment;
import marbel.mov.urjc.imagerv10.interfaces.ReportFragments;


public class MainActivity extends AppCompatActivity  implements ReportFragments, InicioFragment.OnFragmentInteractionListener {

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
    public void startGame() {
        Toast.makeText(getApplicationContext(),"inicia Juego desde el activity", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getApplicationContext(),"cosult instrucciones desde el activity", Toast.LENGTH_SHORT).show();

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
