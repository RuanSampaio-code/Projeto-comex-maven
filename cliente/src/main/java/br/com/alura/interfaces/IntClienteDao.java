package br.com.alura.interfaces;


import br.com.alura.models.Cliente;

import java.util.List;

public interface IntClienteDao {
    void cadastra(Cliente cliente);

    List<Cliente> listarTodas();

    Cliente buscarID(Long id);

    void alterar(Long id, Cliente clienteAlterar);

    void remover(Long id);
}
