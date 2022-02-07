package io.github.darioteles.rest.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Define um modelo DTO para atualização do status do pedido.
 */
@Getter
@Setter
public class AtualizacaoStatusPedidoDTO {
    private String novoStatus;
}
