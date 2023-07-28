package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
