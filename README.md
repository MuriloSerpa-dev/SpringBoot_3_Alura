# SpringBoot_3_Alura
Curso de Spring Boot 3 Alura

Anotaçãoes:

@RestController -> Usamos essa anotação para uma classe que iremos usar como um controlador, quando usamos essa anotação dizemos para o
framework que iremos usar essa classe voltada para o desenvolvimento web RestFul que lida com os metodos(POST,GET,PUT, etc).

@ResquestMapping -> É usada para colocarmos o mapeamento da url que esse controller ira responder Ex( /ex) no caso de cair uma
requisição localhost:8080/ex ira cair nesse controller.

@RequestBody -> A anotação @RequestBody indica que o valor do objeto virá do corpo da requisição.

@Valid -> indica que os dados recebidos devem ser validados.

@Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realizar a sua injeção de dependência em outras classes.

@GeneratedValue faz parte do pacote javax. persistence. Neste caso, o identificador único será gerado pela coluna de auto incremento
do banco de dados, ou seja ele fala que o campo mapeado será gerado automaticamente pelo banco de dados, dai temos algumas estrategia como
Identity que falamos que deve pegar o ultimo registro daquela tabela e seguir com base naquele

#Metodo do protocolo Http
@GetMapping é um verbo do metodo protocolo Http que trata de leitura

@PostMapping é um verbo que é usado quando o cliente deseja enviar dados para processamento ao servidor, como os dados de um formulário,
por exemplo.

@PutMapping é usada para a substituição completa de um recurso OSLC. O método PUT atualiza as propriedades literais e as propriedades 
de recurso local, além de excluir quaisquer propriedades de recurso local que não estão incluídas na solicitação.

@Table A anotação é usada para especificar a tabela principal da entidade atualmente anotadaTable possui quatro atributos que possibilitam
ao desenvolver sobrescrever o nome da tabela, como dito anteriormente, sobrescrever seu catálogo, seu esquema e assegurar restrições de unicidade
nas colunas da tabela. e espicificamos o nome dela entre 
parenteses ex: @Table( name = 'nomeTabela')

@Entity é usada para especificar que a classe anotada atualmente representa um tipo de entidade @Entity é utilizada para informar 
que uma classe também é uma entidade a partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no
banco de dados, onde os dados de objetos desse tipo poderão ser persistidos.

@Enumerated A anotação @Enumerated é usada para especificar que um atributo de entidade representa um tipo enumerado,  é usado
para podermos instruir um provedor JPA a converter uma enumeração em seu valor ordinal ou String.

@Embedded(embutido) faz de um objeto um componente, por si só é dependente da classe que o contém no case Cliente
tem Endereço endereço eh um forte candidato a embutido, visto que nao existe endereço sem ter um Cliente. Ai você poderá aplicar a notação @Cascade.

@Transactional essa anotação ela é utilizada para controlar transações em métodos de um serviço. Ela garante que, caso ocorra algum erro durante
a execução do método, a transação será desfeita e o estado anterior do banco de dados será restaurado.
LOMBOK
Usando a dependencia Lombok eu tenho a facilidade, de usar anotações para comprimir o meu projeto ex
usando lombok eu posso usar a anotação
@Getter que gera os Getters sem a necessidade de digitar todo o codigo apenas com a anotacao eu consigo passar para o Spring todos os Getters

@NoArgsConstructor que gera os construtor Default sem argumentos que a JPA exige em todas as entidades
@AllArgsConstructor que gera um construtor com todas as entidades todos os campos

@EqualsAndHashcode gera o equal e hashcode e nele eu posso especificar onde ele deve trabalhar EX: (of = "id")

@PathVariable  essa anotação vincula o espaço reservado do URI a um parâmetro de método dentro do método manipulador

@RestControllerAdvice  é uma especialização da anotação @Component, que permite manipular exceções em todo o aplicativo em um componente global
é uma anotação para se usar em classes para tratamento de erros para que o Spring reconheça essa classe com ese intuito de tratar exceptions


BeanValidation usamos ele na classe record que é onde o metodo cadastrar da classe controller recebe os parametros
Bean Validation, a partir de anotações ele vai verificar, no caso, se as informações que chegam estão de acordo com as 
anotações Bean valitadation nada mais é que Anotações que são usadas para validar os parametros, essa anotações vem do pacote 
jakarta.validation, é nessecessario a anotação @Valid para validar as anotacoes ou seja o Bean validatiom
ex
@NotNull é para informar que o parametro não pode ser nulo.
@NotBlank verificar se o parametro não esta nule e nem vazio.
@Pattern recebe uma expressao regular, que pode ser digitos, letras e etc
@Valid para que as validações inseridas sejam reconhecidas pelo spring e executadas, ex de como usamos no nosso projeto, para validar 
um DTO que dentro dele vai ter outras validacoes que quero que sejam executadas. 

Classses

Application.properties -> é uma classe, um arquivo que é usada para configurações do nossso ambiente Spring, conectar ao banco de dados e etc

Pom.xml -> Contém os metadados do projeto e também é responsável por gerenciar dependências e configurar plug-ins que nos ajudam a automatizar muitas tarefas repetitivas

Controller classes com assinatura controller são objetos de classes que estendem de yii\base\Controller e são responsáveis pelo processamento das requisições e por 
gerar respostas onde especificamos o comportamento dos verbos http

Repository classes repository provê uma interface para as regras de negócio onde os objetos são acessados como em uma coleção,
ele usa a camada de persistência para gravar e recuperar os dados necessários para persistir e recuperar os objetos de negócio,
ela extend de JPA respository, ela necessita de generics que são dois tipos de objetos, que são os tipos de entidade que esse respository vai trabalhar
ex: public interface MedicoRepository extends JpaRepository<Medico, Long> {

@Autowired
Para usar, persistir o REPOSITORY o Objeto no banco de dados, criamos um atributo na classe Controller, porem pra instanciar
como ela é uma classe de interface respository precisamos passar a anotação @Autowired, que faz a injeção de dependencias ele instancia o respository
dentro da nossa classe controller
ex
public class MedicoController {
@Autowired
private MedicoRepository repository;
public void cadastrar(@RequestBody DadosCadastroMedico dados){
repository.save(new Medico(dados));
}

Para salvar os parametros recebidos pelo Json na aplicação usamos o atributo criado respository, no exemplo acima usamos no metodo cadastrar
como o repository erda de JPAREPOSITORY la ja tem um metodo save, que salva que ja faz o insert no banco de dados, porem necessita receber um objeto do tipo medico
e no nosso exemplo passamos DadosCadastrais do tipo javarecord, então é necessario convertelo para o tipo medico, então vamos usar o construtor para
instanciar um objeto Medico que vai receber os parametros do javarecord DadosCadastrais, criamos um contrutor dentro da classe medico 
ja passando os dados cadastroMedico para que fique mais simples a implementacao.
Precisamos fazer o mesmo com a classe de endereço, criar um construtor, so que passando os dados endereco

Construtor medico
public Medico(DadosCadastroMedico dados) {
this.nome = dados.nome();
this.email = dados.email();
this.crm = dados.crm();
this.especialidade = dados.especialidade();
this.endereco = new Endereco(dados.dadosendereco());

Construtor endereco
public Endereco(Dadosendereco dadosendereco) {
this.logradouro = dadosendereco.logradouro();
this.bairro = dadosendereco.bairro();
this.cep = dadosendereco.cep();
this.numero = dadosendereco.numero();
this.complemento = dadosendereco.complemnto();
this.uf = dadosendereco.uf();
}


Usar ferramentas de migrações, nesse curso vamos usar o FLYWAY que  é uma ferramenta de controle de versão para bancos 
de dados, que permite aos desenvolvedores gerenciar a evolução de seus esquemas de banco de dados de forma automatizada
e controlada, Para usar devemos adicionar a dependencia no pom.xml,  para cada mudança que fizermos no banco de dados devemos 
criar um arquivo .SQL e la por os comandos SQL, porem deve-se ser salvo em um arquivo especifico, devemos criar um diretorio 
dentro da pasta resources, db e dentro dele migration e dentro dessa pasta ciramos os arquivos SQL.

Nova migração, para fazer uma mudança na API como no caso vamos adicionar o parametro telefone ao medico, precisamos adicionar
um um parametro na migração no arquivo sql porem não podemos modificar oque ja foi feito, para isso devemos criar um nova migration
uma nova migração com as modificações feitas.

ResponseEntity significa representar toda a resposta HTTP. Você pode controlar qualquer coisa que aconteça: código de status,
cabeçalhos e corpo. Trabalhando com microservice, ResponseEntity para enviar resposta completa, com status, com cabeçalho e corpo

UriBuilder classe fornece uma maneira conveniente de modificar o conteúdo de uma Uri instância sem criar uma nova Uri instância
para cada modificação.
        ex :
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

toUri() cria o objeto uri



