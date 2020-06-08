package marbel.mov.urjc.imagerv10.interfaces;

import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesAjustesFragment;
import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesAyudaFragment;
import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesJuegoDrawFragment;
import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesJuegoPuzzleFragment;
import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesRankingFragment;
import marbel.mov.urjc.imagerv10.fragments.instrucciones.InstruccionesUsuarioFragment;

public interface IFragmentsInstructions extends InstruccionesAyudaFragment.OnFragmentInteractionListener, InstruccionesJuegoPuzzleFragment.OnFragmentInteractionListener,
        InstruccionesJuegoDrawFragment.OnFragmentInteractionListener,
        InstruccionesRankingFragment.OnFragmentInteractionListener,
        InstruccionesUsuarioFragment.OnFragmentInteractionListener,
        InstruccionesAjustesFragment.OnFragmentInteractionListener {
}
