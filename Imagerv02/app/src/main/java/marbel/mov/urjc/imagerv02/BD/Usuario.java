package marbel.mov.urjc.imagerv02.BD;
import java.util.Arrays;

public class Usuario {
    private String nombre;
    private String usario;
    private String password;
    private Integer edad;

    private Integer draw;

    public Usuario() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    private Integer order;

    public Usuario(String usario, String nombre, String password, Integer edad,  Integer score1, Integer score2) {

        this.usario = usario;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;

        this.draw = score1;
        this.order = score2;
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





}
