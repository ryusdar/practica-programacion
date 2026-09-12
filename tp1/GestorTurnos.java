import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorTurnos {
    private List<Paciente> pacientes;

    public GestorTurnos() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Error: no se puede agregar un paciente nulo.");
            return;
        }
        pacientes.add(paciente);
        System.out.println("Paciente agregado: " + paciente.getNombre() + " " + paciente.getApellido());
    }

    public void asignarTurno(Paciente paciente, LocalDate fecha) {
        try {
            validarTurno(fecha);
            paciente.setFechaTurno(fecha);
            System.out.println("Turno asignado a " + paciente.getNombre() 
                    + " para el día " + fecha);
        } catch (TurnoInvalidoException e) {
            System.out.println("No se pudo asignar el turno: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: el paciente no puede ser nulo.");
        }
    }

    private void validarTurno(LocalDate fecha) throws TurnoInvalidoException {
        if (fecha == null) {
            throw new TurnoInvalidoException("La fecha no puede ser nula.");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new TurnoInvalidoException("La fecha del turno (" + fecha + ") ya pasó.");
        }
    }

    public void atenderPacientes() {
        for (Paciente p : pacientes) {
            p.atender();
        }
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}