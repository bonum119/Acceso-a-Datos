public class Main {
    public static void main(String[] args) {
        // Inyectamos las dependencias correctamente. 
        ClienteRepositorio clienteRepo = new MemoriaRepositorio();
        PagoRepositorio pagoRepo = new MemoriaPagoRepositorio();

        GasolineraApp app = new GasolineraApp(clienteRepo, pagoRepo);
        app.iniciar();
    }
}