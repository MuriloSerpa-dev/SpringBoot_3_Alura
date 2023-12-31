package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRespository extends JpaRepository<Consulta, Long > {


    boolean existsByMedicoIdAndDataAndMotivoIsNull(Long idMedico, LocalDateTime data);
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
