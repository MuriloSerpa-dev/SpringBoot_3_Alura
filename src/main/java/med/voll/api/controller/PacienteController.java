package med.voll.api.controller;


import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRespository pacienteRespository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dados){
        pacienteRespository.save(new Paciente(dados));
    }
    @GetMapping
    public Page<DadosListagemPaciente> listager(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return pacienteRespository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
