package br.com.alura.service;

import br.com.alura.dao.ClienteDao;
import br.com.alura.models.Cliente;

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
