package med.voll.api.domain.consulta.validaçoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRespository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;
    @Component("ValidadorHorarioAntecedenciaCancelamento")
    public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {
        @Autowired
        private ConsultaRespository repository;

        public void validar(DadosCancelamentoConsulta dados) {
            var dataConsulta = repository.getReferenceById(dados.idConsulta());
            var agora = LocalDateTime.now();
            var diferencaEmMinutos = Duration.between(agora, dataConsulta.getData()).toHours(); // consulta a diferemça de tempo entre uma variavel e outra

            if (diferencaEmMinutos < 24){
                throw new ValidacaoException("Consulta deve ser cancelada com minimo de 24 horas de antecedencia! ");
            }
        }
}


