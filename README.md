# SpringBoot_3_Alura
Curso de Spring Boot Alura

Anotaçãoes
@RestController -> Usamos essa anotação para uma classe que iremos usar como um controlador, quando usamos essa anotação dizemos para o
framework que iremos usar essa classe voltada para o desenvolvimento web RestFul que lida com os metodos(POST,GET,PUT, etc).

@ResquestMapping -> É usada para colocarmos o mapeamento da url que esse controller ira responder Ex( /ex) no caso de cair uma
requisição localhost:8080/ex ira cair nesse controller.

@RequestBody -> A anotação @RequestBody indica que o valor do objeto virá do corpo da requisição.

@Valid -> indica que os dados recebidos devem ser validados.

#Metodo do protocolo Http
@GetMapping é um verbo do metodo protocolo Http que trata de leitura

@PostMapping é um verbo que trata

@Table A anotação é usada para especificar a tabela principal da entidade atualmente anotada. e espicificamos o nome dela entre 
parenteses ex: @Table( name = 'nomeTabela')

@Entity é usada para especificar que a classe anotada atualmente representa um tipo de entidade. @Entity define que uma classe 
pode ser mapeada para uma tabela. E é isso, é apenas um marcador, como por exemplo Interface Serializável

@Enumerated A anotação @Enumerated é usada para especificar que um atributo de entidade representa um tipo enumerado,  é usado
para podermos instruir um provedor JPA a converter uma enumeração em seu valor ordinal ou String.

@Embedded(embutido) faz de um objeto um componente, um componente por si só é dependente da classe que o contém no case Cliente
tem Endereço endereço eh um forte candidato a embutido, visto que nao existe endereço sem ter um Cliente. Ai você poderá aplicar a notação @Cascade.

LOMBOK
Usando a dependencia Lombok eu tenho uma facilidade, de usar anotações para comprimir o meu projeto ex
usando lombok eu posso usar a anotação
@Getter que gera os Getters sem a necessidade de digitar todo o codigo apenas com a anotacao eu consigo passar para o Spring todos os Getters

@NoArgsConstructor que gera os construtor Default sem argumentos que a JPA exige em todas as entidades
@AllArgsConstructor que gera um construtor com todas as entidades todos os campos

@EqualsAndHashcode gera o equal e hashcode e nele eu posso especificar onde ele deve trabalhar EX: (of = "id")
