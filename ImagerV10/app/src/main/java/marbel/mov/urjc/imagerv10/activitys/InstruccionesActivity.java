package marbel.mov.urjc.imagerv10.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import marbel.mov.urjc.imagerv10.R;
import marbel.mov.urjc.imagerv10.activitys.ui.main.SectionsPagerAdapter;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesAjustesFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesAyudaFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesJuegoDrawFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesJuegoPuzzleFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesRankingFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesUsuarioFragment;

public class InstruccionesActivity extends AppCompatActivity implements InstruccionesAyudaFragment.OnFragmentInteractionListener, InstruccionesJuegoPuzzleFragment.OnFragmentInteractionListener,
        InstruccionesJuegoDrawFragment.OnFragmentInteractionListener,
        InstruccionesRankingFragment.OnFragmentInteractionListener,
        InstruccionesUsuarioFragment.OnFragmentInteractionListener,
        InstruccionesAjustesFragment.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private LinearLayout lPuntos;
    private TextView[] pSlide;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        lPuntos=findViewById(R.id.idLinearPuntos);
        setIndicadorPuntos(0);
        viewPager.addOnPageChangeListener(viewListener);

    }

    private void setIndicadorPuntos(int pos) {
        pSlide=new TextView[6];
        lPuntos.removeAllViews();

        for(int i=0; i<pSlide.length;i++){
            pSlide[i]=new TextView(this);
            pSlide[i].setText(Html.fromHtml("&#8226"));
            pSlide[i].setTextSize(35);
            pSlide[i].setTextColor(getResources().getColor((R.color.colorBlancoTransparente)));
            lPuntos.addView(pSlide[i]);

        }
        if(pSlide.length>0){
            pSlide[pos].setTextColor(getResources().getColor((R.color.colorBlanco)));

        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setIndicadorPuntos(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}