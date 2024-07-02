package br.alura.comex.services;

import br.alura.comex.dao.ClienteDao;
import br.alura.comex.dao.ProdutoDao;
import br.alura.comex.models.Cliente;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

    private ClienteDao clienteDao;


    public ClienteService(EntityManager manager){
        this.clienteDao = new ClienteDao(manager);
    }

    public void efetuaCadastroDeCliente(Cliente cliente) {
        this.clienteDao.cadastra(cliente);

    }
    public List<Cliente> listarCadastroDeClientes() {
        return this.clienteDao.listarTodas();
    }


    public Cliente buscarID(Long id) {
        Cliente busca = clienteDao.buscarID(id);
        return busca;
    }


    public void removerCliente(Long id) {
        clienteDao.remover(id);
    }

    public void alteracaoDeCliente(Long id, Cliente clienteAlterada) {
        this.clienteDao.alterar(id, clienteAlterada);
    }


}
