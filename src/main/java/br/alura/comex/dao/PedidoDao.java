package br.alura.comex.dao;

import br.alura.comex.interfaces.IntPedidoDao;
import br.alura.comex.models.Cliente;
import br.alura.comex.models.Pedido;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao implements IntPedidoDao {

    private EntityManager manager;

    public PedidoDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastra(Pedido pedido) {
        // Inicia uma transação no EntityManager
        manager.getTransaction().begin();

        // Persiste a entidade Categoria no banco de dados
        manager.persist(pedido);

        // Confirma a transação, aplicando todas as operações de persistência
        manager.getTransaction().commit();

        System.out.println("Cadastro realizado com sucesso");

    }

    @Override
    public List<Pedido> listarTodas() {
        String jpql = "SELECT ped FROM Pedido ped";
        List<Pedido> todosPedidos = manager.createQuery(jpql, Pedido.class).getResultList();
        return todosPedidos;
    }

    @Override
    public Pedido buscarID(Long id) {
        var pedidoEncontrado = manager.find(Pedido.class, id);

//        if (categoriaEncontrada==null){
//            System.out.println("Categoria nao encontradas");
//
//        }

        //System.out.println(categoriaEncontrada);
        return pedidoEncontrado;

    }

    @Override
    public void alterar(Long id, Pedido pedido) {
        try {
            manager.getTransaction().begin();

            // Buscar a categoria pelo ID antes de tentar mesclar
            Pedido pedidoEncontrado = buscarID(id);

            if (pedidoEncontrado == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                // Mesclar (merge) a entidade atualizada com a existente no contexto persistente
                // Isso vai atualizar a categoria no banco de dados
                pedidoEncontrado.setData(pedido.getData());
                // Atualizando o campo CPF
                pedidoEncontrado.setDesconto(pedido.getDesconto());

                // Atualizando o campo Email
                pedidoEncontrado.setDesconto(pedido.getDesconto());

                // Atualizando o campo Telefone
                pedidoEncontrado.setTipoDesconto(pedido.getTipoDesconto());

                // Atualizando o campo Logradouro
                pedidoEncontrado.setValorTotal(pedido.getValorTotal());

                // Atualizando o campo Bairro
                pedidoEncontrado.setItens(pedido.getItens());

                // Atualizando o campo Cidade
                pedidoEncontrado.setCliente(pedido.getCliente());




                manager.merge(pedidoEncontrado);
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

            Pedido pedidoDelete = buscarID(id);

            if (pedidoDelete == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                manager.remove(pedidoDelete);
                manager.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }


    }
}
