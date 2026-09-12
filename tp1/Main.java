import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static GestorTurnos gestor = new GestorTurnos();
    static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarPaciente();
                    break;
                case 2:
                    asignarTurno();
                    break;
                case 3:
                    listarPacientes();
                    break;
                case 4:
                    gestor.atenderPacientes();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();

        } while (opcion != 0);

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("===== MENÚ GESTOR DE TURNOS =====");
        System.out.println("1. Agregar paciente");
        System.out.println("2. Asignar turno a un paciente");
        System.out.println("3. Listar pacientes");
        System.out.println("4. Atender pacientes");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    static int leerOpcion() {
        try {
            int op = Integer.parseInt(scanner.nextLine());
            return op;
        } catch (NumberFormatException e) {
            return -1; 
        }
    }

    static void agregarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre, apellido);
        gestor.agregarPaciente(paciente);
    }

    static void asignarTurno() {
        if (gestor.getPacientes().isEmpty()) {
            System.out.println("No hay pacientes cargados. Agregue uno primero.");
            return;
        }

        listarPacientes();

        System.out.print("Ingrese el ID del paciente para asignar turno: ");
        String id = scanner.nextLine();

        Paciente paciente = buscarPacientePorId(id);

        if (paciente == null) {
            System.out.println("No se encontró ningún paciente con ese ID.");
            return;
        }

        System.out.print("Ingrese la fecha del turno (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();

        try {
            LocalDate fecha = LocalDate.parse(fechaStr, formato);
            gestor.asignarTurno(paciente, fecha);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use dd/MM/yyyy (ej: 25/12/2026).");
        }
    }

    static Paciente buscarPacientePorId(String id) {
        for (Paciente p : gestor.getPacientes()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    static void listarPacientes() {
        if (gestor.getPacientes().isEmpty()) {
            System.out.println("No hay pacientes cargados.");
            return;
        }
        System.out.println("--- Lista de pacientes ---");
        for (Paciente p : gestor.getPacientes()) {
            System.out.println(p);
        }
    }
}