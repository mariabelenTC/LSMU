package marbel.mov.urjc.imagerv10.use;

import android.content.Context;
import android.view.View;

import android.widget.Toast;

import marbel.mov.urjc.imagerv10.R;
import marbel.mov.urjc.imagerv10.interfaces.ReportFragments;

public class OnClickListener implements View.OnClickListener{
    private Context context;
    private int time = Toast.LENGTH_SHORT;
    ReportFragments interfaceReportFragments;


    public OnClickListener(Context c, ReportFragments f){

        this.context=c;
        this.interfaceReportFragments=f;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Play:
                interfaceReportFragments.startGame();
                break;
            case R.id.btn_Ajustes:
                interfaceReportFragments.callSettings();
                break;
            case R.id.btn_Ranking:
                interfaceReportFragments.consultRanking();
                break;
            case R.id.btn_Ayuda:
                interfaceReportFragments.consultInstruccions();
                break;
            case R.id.btn_User:
                interfaceReportFragments.editUsers();
                break;
            case R.id.btn_Info:
                interfaceReportFragments.consultInformation();
                break;
        }

    }

}
