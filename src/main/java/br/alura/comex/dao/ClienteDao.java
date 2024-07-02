package br.alura.comex.dao;

import br.alura.comex.interfaces.IntClienteDao;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;
import br.alura.comex.models.Pedido;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao implements IntClienteDao {
    private EntityManager manager;

    public ClienteDao(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void cadastra(Cliente cliente) {
        // Inicia uma transação no EntityManager
        manager.getTransaction().begin();

        // Persiste a entidade Categoria no banco de dados
        manager.persist(cliente);

        // Confirma a transação, aplicando todas as operações de persistência
        manager.getTransaction().commit();

        System.out.println("Cadastro realizado com sucesso");

    }

    @Override
    public List<Cliente> listarTodas() {
        // Define a consulta JPQL para selecionar todas as categorias
        String jpql = "SELECT cli FROM Cliente cli";

        // Cria uma consulta do tipo TypedQuery usando a string JPQL e a classe Categoria
        List<Cliente> clientes = manager.createQuery(jpql, Cliente.class).getResultList();

        // Retorna a lista de todas as categorias
        return clientes;
        //return null;
    }

    @Override
    public Cliente buscarID(Long id) {
        var clienteEncontrada = manager.find(Cliente.class, id);

//        if (categoriaEncontrada==null){
//            System.out.println("Categoria nao encontradas");
//
//        }

        //System.out.println(categoriaEncontrada);
        return clienteEncontrada;

    }

    @Override
    public void alterar(Long id, Cliente cliente) {
        try {
            manager.getTransaction().begin();

            // Buscar a categoria pelo ID antes de tentar mesclar
            Cliente produtoEncontrado = buscarID(id);

            if (produtoEncontrado == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                // Mesclar (merge) a entidade atualizada com a existente no contexto persistente
                // Isso vai atualizar a categoria no banco de dados
                produtoEncontrado.setNome(cliente.getNome());
                // Atualizando o campo CPF
                produtoEncontrado.setCpf(cliente.getCpf());

                // Atualizando o campo Email
                produtoEncontrado.setEmail(cliente.getEmail());

                // Atualizando o campo Telefone
                produtoEncontrado.setTelefone(cliente.getTelefone());

                // Atualizando o campo Logradouro
                produtoEncontrado.setLogradouro(cliente.getLogradouro());

                // Atualizando o campo Bairro
                produtoEncontrado.setBairro(cliente.getBairro());

                // Atualizando o campo Cidade
                produtoEncontrado.setCidade(cliente.getCidade());

                // Atualizando o campo Estado
                produtoEncontrado.setEstado(cliente.getEstado());

                // Atualizando o campo CEP
                produtoEncontrado.setCep(cliente.getCep());


                manager.merge(produtoEncontrado);
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar atualizar o clinete: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }

    }

    @Override
    public void remover(Long id) {
        try {
            manager.getTransaction().begin();

            Cliente clienteDelete = buscarID(id);

            if (clienteDelete == null) {
                System.out.println("Cliente não encontrada com o ID informado.");
            } else {
                manager.remove(clienteDelete);
                manager.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }

    }
}
