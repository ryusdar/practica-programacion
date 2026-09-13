package tp4.tp4.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tp4.tp4.model.Paciente;

@Repository
public class PacienteRepository {

    private List<Paciente> pacientes = new ArrayList<>();
    private Long siguienteId = 1L;

    public Paciente guardar(String nombre, int edad, String email) {
        Paciente paciente = new Paciente(siguienteId, nombre, edad, email);
        pacientes.add(paciente);
        siguienteId = siguienteId + 1;
        return paciente;
    }

    public List<Paciente> listarTodos() {
        return pacientes;
    }

    public Paciente buscarPorId(Long id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}