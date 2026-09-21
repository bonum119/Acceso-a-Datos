import java.util.ArrayList;
import java.util.List;

public class MemoriaRepositorio implements ClienteRepositorio {
    private List clientes = new ArrayList<>();

    @Override
    public void guardar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List obtenerTodos() {
        return new ArrayList<>(clientes); // Devolvemos una copia
    }

    @Override
    public Cliente obtenerPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Cliente obtenerPorMatricula(String matricula) {
        return clientes.stream()
                .filter(c -> c.getMatricula().equalsIgnoreCase(matricula))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int obtenerSiguienteId() {
        if (clientes.isEmpty()) return 1;
        int maxId = clientes.stream().mapToInt(Cliente::getId).max().orElse(0);
        return maxId + 1;
    }
}