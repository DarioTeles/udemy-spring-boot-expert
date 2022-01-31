package io.github.darioteles.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Define um modelo DTO no qual especifica as informações de atualização do Pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

}
