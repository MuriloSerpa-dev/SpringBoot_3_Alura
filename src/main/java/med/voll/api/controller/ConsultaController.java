package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")

public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;
    @PostMapping

    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
     var detalhamentoConsulta =  agendaDeConsultas.agendar(dados);

        return ResponseEntity.ok(detalhamentoConsulta);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agendaDeConsultas.cancelar(dados);

        return ResponseEntity.noContent().build();
    }
}
