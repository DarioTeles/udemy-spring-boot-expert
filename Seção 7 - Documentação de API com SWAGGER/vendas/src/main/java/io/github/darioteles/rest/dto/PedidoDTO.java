package io.github.darioteles.rest.dto;

import io.github.darioteles.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Define um modelo DTO no qual especifica as informações de atualização do Pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{Pedido não pode ser realizado sem itens.}")//Annotation de validação customizada @NotEmptyList definida na interface validation.NotEmptyList
    private List<ItemPedidoDTO> items;

}
