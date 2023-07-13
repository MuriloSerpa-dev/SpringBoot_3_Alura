package med.voll.api.domain.endereco;

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

    public Endereco(DadosEndereco dadosendereco) {
        this.logradouro = dadosendereco.logradouro();
        this.bairro = dadosendereco.bairro();
        this.cidade = dadosendereco.cidade();
        this.cep = dadosendereco.cep();
        this.numero = dadosendereco.numero();
        this.complemento = dadosendereco.complemnto();
        this.uf = dadosendereco.uf();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if (this.logradouro != null){
            this.logradouro = dados.logradouro();
        }
        if (this.bairro != null){
            this.bairro = dados.bairro();
        }
        if (this.cidade != null){
            this.cidade = dados.cidade();
        }
        if (this.cep != null){
            this.cep = dados.cep();
        }
        if (this.numero != null){
            this.numero = dados.numero();
        }
        if (this.complemento != null){
            this.complemento = dados.complemnto();
        }
        if (this.uf != null){
            this.uf = dados.uf();
        }

    }
}
