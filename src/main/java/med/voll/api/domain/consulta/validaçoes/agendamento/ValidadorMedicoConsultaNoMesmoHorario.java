package med.voll.api.domain.consulta.validaçoes.agendamento;


import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRespository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validaçoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRespository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsulta = repository.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsulta){
            throw new ValidacaoException("Medico com consulta ja agendada para o Horario requerido!");
        }
    }
}
