package med.voll.api.domain.consulta.validaçoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    public void validar(DadosCancelamentoConsulta dados);
}
