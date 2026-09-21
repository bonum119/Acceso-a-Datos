import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GasolineraApp {
    private ClienteRepositorio clienteRepo;
    private PagoRepositorio pagoRepo;
    private ConsolaUI ui;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GasolineraApp(ClienteRepositorio clienteRepo, PagoRepositorio pagoRepo) {
        this.clienteRepo = clienteRepo;
        this.pagoRepo = pagoRepo;
        this.ui = new ConsolaUI();
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTIÓN DE GASOLINERA ===");
            System.out.println("1. Dar de alta un cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar clientes");
            System.out.println("4. Procesar un pago de repostaje");
            System.out.println("5. Consultar pagos");
            System.out.println("0. Salir");
            
            String opcion = ui.leerTexto("Opción: ");

            switch (opcion) {
                case "1": altaCliente(); break;
                case "2": listarClientes(); break;
                case "3": buscarClientes(); break;
                case "4": procesarPago(); break;
                case "5": consultarPagos(); break;
                case "0":
                    System.out.println("Hasta pronto.");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción desconocida. Por favor, elige de nuevo.");
            }
        }
    }

    private void altaCliente() {
        String nombre = ui.leerTextoObligatorio("Nombre: ");
        String telefono = ui.leerTextoObligatorio("Teléfono: ");
        String matricula = ui.leerTextoObligatorio("Matrícula: ").toUpperCase();

        if (clienteRepo.obtenerPorMatricula(matricula) != null) {
            System.out.println("Esa matrícula ya está registrada. No se ha creado el cliente.");
            return;
        }

        int id = clienteRepo.obtenerSiguienteId();
        Cliente nuevo = new Cliente(id, nombre, telefono, matricula);
        clienteRepo.guardar(nuevo);
        System.out.println("Cliente registrado con ID " + id + ".");
    }

    private void listarClientes() {
        List clientes = clienteRepo.obtenerTodos();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        clientes.sort(Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER)
                                .thenComparing(Cliente::getId));

        mostrarTablaClientes(clientes);
    }

    private void buscarClientes() {
        String texto = ui.leerTextoObligatorio("Texto que buscar: ").toLowerCase();
        List clientes = clienteRepo.obtenerTodos();
        
        List filtrados = clientes.stream()
            .filter(c -> c.getNombre().toLowerCase().contains(texto) ||
                         c.getTelefono().toLowerCase().contains(texto) ||
                         c.getMatricula().toLowerCase().contains(texto))
            .sorted(Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER)
                              .thenComparing(Cliente::getId))
            .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("No se han encontrado clientes.");
        } else {
            mostrarTablaClientes(filtrados);
        }
    }

    private void mostrarTablaClientes(List clientes) {
        System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "NOMBRE", "TELÉFONO", "MATRÍCULA");
        for (Cliente c : clientes) {
            System.out.printf("%-5d %-20s %-15s %-10s%n", c.getId(), c.getNombre(), c.getTelefono(), c.getMatricula());
        }
    }

    private void procesarPago() {
        List clientes = clienteRepo.obtenerTodos();
        if (clientes.isEmpty()) {
            System.out.println("Primero debe darse de alta un cliente.");
            return;
        }

        listarClientes();
        int idCliente = ui.leerEnteroPositivo("ID del cliente: ");
        Cliente cliente = clienteRepo.obtenerPorId(idCliente);
        
        if (cliente == null) {
            System.out.println("No existe un cliente con ese identificador. No se ha registrado el pago.");
            return;
        }

        LocalDate fecha = ui.leerFecha("Fecha (dd/MM/aaaa; vacío para hoy): ");
        double importe = ui.leerCantidad("Importe (€): ");
        double litros = ui.leerCantidad("Litros: ");
        String combustible = ui.leerTextoObligatorio("Combustible: ");

        int idPago = pagoRepo.obtenerSiguienteId();
        Pagos pago = new Pagos(idPago, idCliente, fecha, importe, litros, combustible);
        pagoRepo.guardar(pago);

        System.out.printf("Pago %d registrado para %s: %.2f €.%n", idPago, cliente.getNombre(), importe);
    }

    private void consultarPagos() {
        List pagos = pagoRepo.obtenerTodos();
        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados.");
            return;
        }

        pagos.sort(Comparator.comparing(Pagos::getFecha).reversed()
                             .thenComparing(Comparator.comparing(Pagos::getId).reversed()));

        System.out.printf("%-5s %-20s %-15s %-10s %-10s %-15s%n", "ID", "CLIENTE", "FECHA", "IMPORTE", "LITROS", "COMBUSTIBLE");
        for (Pagos p : pagos) {
            Cliente c = clienteRepo.obtenerPorId(p.getIdCliente());
            String nombreCliente = (c != null) ? c.getNombre() : "Desconocido";
            System.out.printf("%-5d %-20s %-15s %-8.2f € %-10.2f %-15s%n",
                    p.getId(), nombreCliente, p.getFecha().format(dateFormatter), p.getImporte(), p.getLitros(), p.getCombustible());
        }
    }
}