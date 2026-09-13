package tp4.tp4.exemption;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(Long id) {
        super("No se encontró un paciente con id: " + id);
    }
}