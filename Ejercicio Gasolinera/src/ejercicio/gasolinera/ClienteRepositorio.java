import java.util.List;

public interface ClienteRepositorio {
    void guardar(Cliente cliente);
    List obtenerTodos();
    Cliente obtenerPorId(int id);
    Cliente obtenerPorMatricula(String matricula);
    int obtenerSiguienteId();
}