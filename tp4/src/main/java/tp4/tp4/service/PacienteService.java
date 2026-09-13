package tp4.tp4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp4.tp4.dto.PacienteRequestDTO;
import tp4.tp4.dto.PacienteResponseDTO;
import tp4.tp4.exemption.PacienteNotFoundException;
import tp4.tp4.model.Paciente;
import tp4.tp4.repository.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponseDTO crearPaciente(PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.guardar(
                request.getNombre(), request.getEdad(), request.getEmail()
        );
        return convertirAResponseDTO(paciente);
    }

    public List<PacienteResponseDTO> listarPacientes() {
        List<PacienteResponseDTO> resultado = new ArrayList<>();
        for (Paciente p : pacienteRepository.listarTodos()) {
            resultado.add(convertirAResponseDTO(p));
        }
        return resultado;
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.buscarPorId(id);
        if (paciente == null) {
            throw new PacienteNotFoundException(id);
        }
        return convertirAResponseDTO(paciente);
    }

    private PacienteResponseDTO convertirAResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getEdad()
        );
    }
}