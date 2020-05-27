package marbel.mov.urjc.imagerv10.uses;

import android.view.View;
import android.widget.Toast;

import marbel.mov.urjc.imagerv10.R;
import marbel.mov.urjc.imagerv10.interfaces.ReportFragments;

public class OnclickListener implements View.OnClickListener{
    private int time = Toast.LENGTH_SHORT;
    ReportFragments interfaceReportFragments;
    public OnclickListener(ReportFragments f){

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
