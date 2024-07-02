package br.alura.comex.services;

import br.alura.comex.dao.PedidoDao;
import br.alura.comex.models.Pedido;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoService {

    private PedidoDao pedidoDao;
    public PedidoService(EntityManager manager){
        this.pedidoDao = new PedidoDao(manager);
    }

    public void efetuaCadastroDePedidos(Pedido pedido) {
        this.pedidoDao.cadastra(pedido);

    }
    public List<Pedido> listarCadastroDePedidos() {
        return this.pedidoDao.listarTodas();
    }


    public Pedido buscarID(Long id) {
        Pedido busca = pedidoDao.buscarID(id);
        return busca;
    }


    public void removerPedido(Long id) {
        pedidoDao.remover(id);
    }

    public void alteracaoDePedido(Long id, Pedido pedidoAlterado) {
        this.pedidoDao.alterar(id, pedidoAlterado);
    }




}
