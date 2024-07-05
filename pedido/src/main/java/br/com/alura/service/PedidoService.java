package br.com.alura.service;


import br.com.alura.dao.PedidoDao;
import br.com.alura.model.Pedido;

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
