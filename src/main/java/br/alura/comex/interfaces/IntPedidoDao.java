package br.alura.comex.interfaces;

import br.alura.comex.models.Categoria;
import br.alura.comex.models.Pedido;

import java.util.List;

public interface IntPedidoDao {
    void cadastra(Pedido pedido);

    List<Pedido> listarTodas();

    Pedido buscarID(Long id);

    void alterar(Long id, Pedido pedido);

    void remover(Long id);
}
