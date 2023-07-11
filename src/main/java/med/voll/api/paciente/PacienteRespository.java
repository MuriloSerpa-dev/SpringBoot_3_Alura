package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRespository extends JpaRepository<Paciente, Long> {
}
