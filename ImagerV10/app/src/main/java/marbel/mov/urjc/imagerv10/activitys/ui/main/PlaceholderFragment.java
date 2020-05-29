package marbel.mov.urjc.imagerv10.activitys.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import marbel.mov.urjc.imagerv10.R;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesAjustesFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesAyudaFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesJuegoDrawFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesJuegoPuzzleFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesRankingFragment;
import marbel.mov.urjc.imagerv10.fragments.InstruccionesUsuarioFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {
        Fragment fragment=null;
        switch (index){
            case 1: fragment=new InstruccionesAyudaFragment();break;
            case 2: fragment= new InstruccionesJuegoPuzzleFragment();break;
            case 3: fragment=new InstruccionesJuegoDrawFragment();break;
            case 4: fragment=new InstruccionesRankingFragment();break;
            case 5: fragment=new InstruccionesUsuarioFragment();break;
            case 6: fragment=new InstruccionesAjustesFragment();break;

        }
        


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_instrucciones, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}