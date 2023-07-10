package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
//LOMBOK
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String cep;
    private String numero;
    private String complemento;
    private String uf;

    public Endereco(Dadosendereco dadosendereco) {
        this.logradouro = dadosendereco.logradouro();
        this.bairro = dadosendereco.bairro();
        this.cidade = dadosendereco.cidade();
        this.cep = dadosendereco.cep();
        this.numero = dadosendereco.numero();
        this.complemento = dadosendereco.complemnto();
        this.uf = dadosendereco.uf();
    }
}
