package marbel.mov.urjc.imagerv02.BD;


public class Usuario {
    private String nombre;
    private String usario;
    private String password;
    private Integer edad;
    private Integer score1;
    private String juego1;
    private Integer score2;
    private String juego2;

    public Usuario(String usario, String nombre, String password, Integer edad, Integer score, String juego1, Integer score2, String juego2) {

        this.usario = usario;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
        this.score1=score;
        this.juego1 = juego1;
        this.score2 = score2;
        this.juego2 = juego2;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsario() {
        return usario;
    }
    public void setUsario(String usario) {
        this.usario = usario;
    }
    public String getPassword() {
        return password;
    }
    public void setContraseña(String password) {
        this.password = password;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public Integer getScore1() {
        return score1;
    }
    public void setScore1(Integer score) {
        this.score1 = score;
    }
    public Integer getScore2() {
        return score2;
    }
    public void setScore2(Integer score2) {
        this.score2 = score2;
    }
    public String getJuego2() {
        return juego2;
    }
    public void setJuego2(String juego2) {
        this.juego2 = juego2;
    }
}
