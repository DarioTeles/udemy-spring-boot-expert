package io.github.darioteles.service.impl;

import io.github.darioteles.domain.entity.Cliente;
import io.github.darioteles.domain.entity.ItemPedido;
import io.github.darioteles.domain.entity.Pedido;
import io.github.darioteles.domain.entity.Produto;
import io.github.darioteles.domain.enums.StatusPedido;
import io.github.darioteles.domain.repository.Clientes;
import io.github.darioteles.domain.repository.ItemPedidos;
import io.github.darioteles.domain.repository.Pedidos;
import io.github.darioteles.domain.repository.Produtos;
import io.github.darioteles.exception.PedidoNaoEncontradoException;
import io.github.darioteles.exception.RegraNegocioException;
import io.github.darioteles.rest.dto.ItemPedidoDTO;
import io.github.darioteles.rest.dto.PedidoDTO;
import io.github.darioteles.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Define um classe Service Spring responsável pelos serviços da entidade Pedido.
 */
@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    // Injeção de dependência do objeto repository do tipo Pedidos, Clientes, Produtos e ItemPedidos.
    private final Pedidos pedidoRepository;
    private final Clientes clienteRepository;
    private final Produtos produtoRepository;
    private final ItemPedidos itemPedidoRepository;

    /**
     * Salva um pedido no banco de dados a partir da classe modelo DTO PedidoDTO.
     * @param dto
     * @return pedido
     */
    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedido = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    /**
     * Consulta no banco de dados um pedido pelo id.
     * @param id
     * @return pedido
     */
    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id){
        return pedidoRepository.findByIdFetchItens(id);
    }

    /**
     * Atualiza no banco de dados o status do pedido a partir de uma classe Enum StatusPedido.
     * @param id
     * @param statusPedido
     */
    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    /**
     * Converte uma lista de objetos de modelo DTO ItemPedidoDTO em uma lista de objetos do tipo ItemPedido.
     * @param pedido
     * @param itens
     * @return lista de ItemPedido
     */
    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RegraNegocioException("Não é possível fazer um pedido sem itens.");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
