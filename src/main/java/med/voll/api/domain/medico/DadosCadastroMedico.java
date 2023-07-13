package med.voll.api.domain.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
         @NotBlank(message = "Email é orbigatorio!")
         @Email(message = "Formato de email invalido")
         String email,
         @NotBlank(message = "Telefone é obrigatorio")
        @Pattern(regexp = "\\d{9}")
          String telefone,
         @NotBlank(message = "CRM é obrigatorio ")
        @Pattern(regexp = "\\d{4,6}")
         String crm,
         @NotNull
         Especialidade especialidade,
         @NotNull
        @Valid
        DadosEndereco endereco) {
}
