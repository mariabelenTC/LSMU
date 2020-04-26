package marbel.mov.urjc.imagerv02.ComandosBD;

import android.provider.BaseColumns;

public class ComandosBD {


    private ComandosBD() {}


    public static class FeedEntry implements BaseColumns{


        public static final String TABLA_USUARIO="usuario";
        public static final String CAMPO_NICK="nick";
        public static final String CAMPO_NOMBRE="nombre";
        public static final String CAMPO_PASSWORD="password";
        public static final String CAMPO_EDAD="edad";
        public static final String CAMPO_DRAW="dibujar";
        public static final String CAMPO_ORDER="ordenar";


    }


    //Constantes campos tabla usuario

    public static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + FeedEntry.TABLA_USUARIO + "("+
                    FeedEntry.CAMPO_NICK + " TEXT PRIMARY KEY, " +
                    FeedEntry.CAMPO_NOMBRE + " TEXT, " +
                    FeedEntry.CAMPO_PASSWORD + " TEXT, " +
                    FeedEntry.CAMPO_EDAD + " INTEGER, " +
                    FeedEntry.CAMPO_DRAW + " INTEGER, " +
                    FeedEntry.CAMPO_ORDER + " INTEGER)";



    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLA_USUARIO;
}


