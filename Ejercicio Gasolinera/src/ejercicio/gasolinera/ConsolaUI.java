import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsolaUI {
    private Scanner scanner;
    private DateTimeFormatter formatter;

    public ConsolaUI() {
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public String leerTextoObligatorio(String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim(); // Eliminar espacios al principio y final
            if (input.isEmpty()) {
                System.out.println("Este campo es obligatorio.");
            }
        } while (input.isEmpty());
        return input;
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(input);
                if (valor > 0) return valor;
                System.out.println("Debe ser un entero positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número entero.");
            }
        }
    }

    public double leerCantidad(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            // Reemplaza coma por punto para aceptar ambos formatos
            String input = scanner.nextLine().trim().replace(",", "."); 
            try {
                double valor = Double.parseDouble(input);
                if (valor > 0) return valor;
                System.out.println("Introduce una cantidad mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Introduce una cantidad numérica válida.");
            }
        }
    }

    public LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return LocalDate.now(); // Fecha actual si se deja en blanco
            }
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha no es válida.");
            }
        }
    }
}