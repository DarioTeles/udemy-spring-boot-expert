package io.github.darioteles.rest.controller;

import io.github.darioteles.domain.entity.ItemPedido;
import io.github.darioteles.domain.entity.Pedido;
import io.github.darioteles.domain.enums.StatusPedido;
import io.github.darioteles.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.darioteles.rest.dto.InformacoesItemPedidoDTO;
import io.github.darioteles.rest.dto.InformacoesPedidoDTO;
import io.github.darioteles.rest.dto.PedidoDTO;
import io.github.darioteles.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // Injeção de dependência do objeto service do tipo PedidoService.
    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map(pedido -> converter(pedido) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    /**
     * Converte um objeto do tipo Pedido em um objeto modelo DTO do tipo InformacoesPedidoDTO.
     * @param pedido
     * @param pedido
     * @return dto
     */
    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .nomeCliente(pedido.getCliente().getNome())
                .cpfCliente(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .statusPedido(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();
    }

    /**
     * Converte uma lista de objetos do tipo ItemPedido em uma lista de objetos de modelo DTO do tipo
     * InformacoesItemPedidoDTO.
     * @param itens
     * @return lista de dto
     */
    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                itemPedido -> InformacoesItemPedidoDTO
                                .builder()
                                .descricaoProduto(itemPedido.getProduto().getDescricao())
                                .precoUnitario(itemPedido.getProduto().getPreco())
                                .quantidade(itemPedido.getQuantidade())
                                .build()
        ).collect(Collectors.toList());
    }

}
