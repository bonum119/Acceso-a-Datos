public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String matricula;

    public Cliente(int id, String nombre, String telefono, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.matricula = matricula;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getMatricula() { return matricula; }
}