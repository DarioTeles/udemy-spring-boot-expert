package io.github.darioteles.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Define um modelo DTO no qual especifica as informações do Pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String nomeCliente;
    private String cpfCliente;
    private BigDecimal total;
    private String dataPedido;
    private String statusPedido;
    private List<InformacoesItemPedidoDTO> itens;
}
