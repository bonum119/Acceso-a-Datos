import java.util.Locale;

public class Cliente {
    int id;
    String nombre;
    String telf;
    String matricula;

    public Cliente(int id, String nombre, String telf, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.telf = telf;
        this.matricula = matricula.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
