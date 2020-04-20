package marbel.mov.urjc.imagerv02;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import marbel.mov.urjc.imagerv02.ComandosBD.ComandosBD;

public class ConnexionSQLiteHelper extends SQLiteOpenHelper{




    /*crear base de datos*/
    public ConnexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*genera las tablas (crear el script)*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ComandosBD.CREAR_TABLA_USUARIO);
    }

    /*verificacion de versiones existentes (actualizar script)*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

}
