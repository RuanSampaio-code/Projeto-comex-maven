package br.com.alura.interfaces;


import br.com.alura.model.Pedido;

import java.util.List;

public interface IntPedidoDao {
    void cadastra(Pedido pedido);

    List<Pedido> listarTodas();

    Pedido buscarID(Long id);

    void alterar(Long id, Pedido pedido);

    void remover(Long id);
}
