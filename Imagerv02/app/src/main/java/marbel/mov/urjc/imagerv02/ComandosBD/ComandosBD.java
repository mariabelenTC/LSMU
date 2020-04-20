package marbel.mov.urjc.imagerv02.ComandosBD;

public class ComandosBD {
    //Constantes campos tabla usuario

    public static final String TABLA_USUARIO="usuario";

    public static final String CAMPO_NICK="nick";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_PASSWORD="password";
    public static final String CAMPO_EDAD="edad";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_NICK+" TEXT, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_PASSWORD+" TEXT,"+CAMPO_EDAD+" INTEGER)";
}


