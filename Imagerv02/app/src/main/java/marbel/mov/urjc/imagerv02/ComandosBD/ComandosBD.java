package marbel.mov.urjc.imagerv02.ComandosBD;

public class ComandosBD {
    //Constantes campos tabla usuario

    public static final String TABLA_USUARIO="usuario";

    public static final String CAMPO_NICK="nick";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_PASSWORD="password";
    public static final String CAMPO_EDAD="edad";
    public static final String CAMPO_JUEGO1="juego1";
    public static final String CAMPO_SCORE1="score1";
    public static final String CAMPO_JUEGO2="juego2";
    public static final String CAMPO_SCORE2="score2";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_NICK+" TEXT, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_PASSWORD+" TEXT," +
            ""+CAMPO_EDAD+" INTEGER,"+CAMPO_JUEGO1+" TEXT,"+CAMPO_SCORE1+" INTEGER," +
            ""+CAMPO_JUEGO2+"TEXT,"+CAMPO_SCORE2+" INTEGER)";
}


