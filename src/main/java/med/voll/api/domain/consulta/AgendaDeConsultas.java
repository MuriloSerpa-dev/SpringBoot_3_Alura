package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validaçoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validaçoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class

AgendaDeConsultas {
    @Autowired
    private ConsultaRespository consultaRespository;
    @Autowired
    private MedicoRepository medicoRespository;
    @Autowired
    private PacienteRespository pacienteRespository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadorCancelamentoDeConsultas;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if (!pacienteRespository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente é inexistente!!");
        }
        if(dados.idMedico() != null && !medicoRespository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico é inexistente!");
        }
        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRespository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe medico disponivel nessa data");
        }

        var consulta = new Consulta(null,  medico,  paciente, dados.data(), null);
        consultaRespository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRespository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade obrigatoria no caso de não escolha do medico!");
        }
        return medicoRespository.escolherMedicoAleatoriaLivreNaData(dados.especialidade(), dados.data());
 }
    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRespository.existsById(dados.idConsulta())){
            throw new ValidacaoException("ID de consulta não existe ");
        }
        validadorCancelamentoDeConsultas.forEach(v -> v.validar(dados));

        var consulta = consultaRespository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());



    }
}
