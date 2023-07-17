package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutentiacacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutentiacacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // UsernameP.A.Token é como se fosse um DTO do proprio spring
       var authentication =  manager.authenticate(token); // Este método devolve o objeto que representa o usuário autenticado no sistema.

        return ResponseEntity.ok().build();
    }

}
