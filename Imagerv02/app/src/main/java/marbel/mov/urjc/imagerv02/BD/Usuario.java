package marbel.mov.urjc.imagerv02.BD;


public class Usuario {
    private String nombre;
    private String usario;
    private String password;
    private Integer edad;

    public Usuario(String usario,String nombre, String password, Integer edad) {

        this.usario = usario;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
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
