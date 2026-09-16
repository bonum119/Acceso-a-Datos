import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;
        do{
            mostrarMenu();
            opcion = sc.nextInt();
        }while(opcion < 0);
    }

    private static void mostrarMenu(){
        System.out.println("=== GESTIÓN DE GASOLINERA ===\n" +
                "1. Dar de alta un cliente\n" +
                "2. Listar clientes\n" +
                "3. Buscar clientes\n" +
                "4. Procesar un pago de repostaje\n" +
                "5. Consultar pagos\n" +
                "0. Salir\n" +
                "Opción:");
    }
}