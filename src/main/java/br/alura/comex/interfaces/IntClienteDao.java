package br.alura.comex.interfaces;

import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;

import java.util.List;

public interface IntClienteDao {
    void cadastra(Cliente cliente);

    List<Cliente> listarTodas();

    Cliente buscarID(Long id);

    void alterar(Long id, Cliente clienteAlterar);

    void remover(Long id);
}
