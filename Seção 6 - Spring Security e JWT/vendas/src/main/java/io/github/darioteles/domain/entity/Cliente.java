package io.github.darioteles.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Define um cliente.
 */
@Data //Anotação Lombok que determina os @Getter, @Setter, @RequiredArgsConstructor e @ToString.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}") //Especifica que o atributo nome faz parte da validação.
    private String nome;

    @NotEmpty(message = "{campo.cpf.obrigatorio}") //"{}" corresponde aos parâmetros definidos em messages.properties.
    @CPF(message = "{campo.cpf.invalido}")
    @Column(length = 11)
    private String cpf;

    @JsonIgnore //Ele diz para o transformador de objetos em Json que deve ignorar esse parâmetro.
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    /**
     * Constrói um cliente com somente parâmetros de id e nome.
     * @param id
     * @param nome
     */
    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
