package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Serve para testar uma interface repository
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository respository;

    @Autowired
    private TestEntityManager em; // Classe especifica para teste, usar como se fosse um repository

    @Test
    @DisplayName("Deve devolver null quando o medico cadastrado não esta disponivel na data")
    void escolherMedicoAleatoriaLivreNaDataCenario1(){
        //given ou arrange
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // when ou act
        var medico = cadastrarMedico("Murilo", "medicoM@vollmed.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Kamila","8599999999","kamila@k.com");
        cadastrarConsulta(medico,paciente,proximaSegundaAsDez);

        //then ou assert
        var medicoLivre = respository.escolherMedicoAleatoriaLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }
    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatoriaLivreNaDataCenario2(){
        //given ou arrange
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // when ou act
        var medico = cadastrarMedico("Murilo", "medicoM@vollmed.med", "123456", Especialidade.CARDIOLOGIA);

        //then ou assert
        var medicoLivre = respository.escolherMedicoAleatoriaLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isEqualTo(medico);
    }
    @Test
    @DisplayName("Haver medicos disponiveis mas de Outra especialidade")
    void escolherMedicoAleatoriaLivreNaDataCenario3(){
        //given ou arrange
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // when ou act
        var medico = cadastrarMedico("Murilo", "medicoM@vollmed.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Kamila","8599999999","kamila@k.com");
        cadastrarConsulta(medico,paciente,proximaSegundaAsDez);

        //then ou assert
        var medicoLivre = respository.escolherMedicoAleatoriaLivreNaData(Especialidade.DERMATOILOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }
    @Test
    @DisplayName("Deve devolver null pois não a medico cadastrado no banco de dados")
    void escolherMedicoAleatoriaLivreNaDataCenario4(){
        //given ou arrange
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // when ou act
        var paciente = cadastrarPaciente("Kamila","8599999999","kamila@k.com");

        //then ou assert
        var medicoLivre = respository.escolherMedicoAleatoriaLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }


        private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
         em.persist(new Consulta(null, medico, paciente, data, null));
        }

        private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade){
         var medico = new Medico(dadosMedico(nome, email, crm, especialidade, dadosEndereco()));
          em.persist(medico);
          return medico;
        }

    private Paciente cadastrarPaciente(String nome, String telefone, String email) {
        var paciente = new Paciente(dadosPaciente(nome,telefone,email));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String telefone, String email) {
        return new DadosCadastroPaciente(nome,
                telefone,
                email,
                dadosEndereco());
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco dadosEndereco) {
        return new DadosCadastroMedico(nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco());
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}