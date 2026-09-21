import java.util.ArrayList;
import java.util.List;

public class MemoriaPagoRepositorio implements PagoRepositorio {
    private List pagos = new ArrayList<>();

    @Override
    public void guardar(Pagos pago) {
        pagos.add(pago);
    }

    @Override
    public List obtenerTodos() {
        return new ArrayList<>(pagos);
    }

    @Override
    public int obtenerSiguienteId() {
        if (pagos.isEmpty()) return 1;
        int maxId = pagos.stream().mapToInt(Pagos::getId).max().orElse(0);
        return maxId + 1;
    }
}