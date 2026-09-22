import java.util.List;

public interface PagoRepositorio {
    void guardar(Pagos pago);
    List obtenerTodos();
    int obtenerSiguienteId();
}