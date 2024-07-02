package br.alura.comex.dao;

import br.alura.comex.interfaces.IntProdutoDao;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao implements IntProdutoDao {

    private EntityManager manager;

    public ProdutoDao(EntityManager manager) {
        this.manager = manager ;
    }


    @Override
    public void cadastra(Produto produto) {
        // Inicia uma transação no EntityManager
        manager.getTransaction().begin();

        // Persiste a entidade Categoria no banco de dados
        manager.persist(produto);

        // Confirma a transação, aplicando todas as operações de persistência
        manager.getTransaction().commit();

        System.out.println("Cadastro realizado com sucesso");

    }

    @Override
    public List<Produto> listarTodas() {
        // Define a consulta JPQL para selecionar todas as categorias
        String jpql = "SELECT p FROM Produto p";

        // Cria uma consulta do tipo TypedQuery usando a string JPQL e a classe Categoria
        List<Produto> produtos = manager.createQuery(jpql, Produto.class).getResultList();

        // Retorna a lista de todas as categorias
        return produtos;
    }

    @Override
    public Produto buscarID(Long id) {
        var produtoaEncontrado = manager.find(Produto.class, id);

//        if (categoriaEncontrada==null){
//            System.out.println("Categoria nao encontradas");
//
//        }

        //System.out.println(categoriaEncontrada);
        return produtoaEncontrado;
    }

    @Override
    public void alterar(Long id, Produto produtoAlterar) {
        try {
            manager.getTransaction().begin();

            // Buscar a categoria pelo ID antes de tentar mesclar
            Produto produtoExistente = buscarID(id);

            if (produtoExistente == null) {
                System.out.println("produto não encontrada com o ID informado.");
            } else {
                // Mesclar (merge) a entidade atualizada com a existente no contexto persistente
                // Isso vai atualizar a categoria no banco de dados
                produtoExistente.setNome(produtoAlterar.getNome());
                produtoExistente.setDescricao(produtoAlterar.getDescricao());
                produtoExistente.setPreco(produtoAlterar.getPreco());
                produtoExistente.setCategorias(produtoAlterar.getCategorias());

                manager.merge(produtoExistente);
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar atualizar o produto: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }

    }

    @Override
    public void remover(Long id) {
        try {
            manager.getTransaction().begin();

            Produto produtoDelete = buscarID(id);

            if (produtoDelete == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                manager.remove(produtoDelete);
                manager.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }

    }

    public List<Produto> listarPelaCategoria(String categoria){
        String jpql = "SELECT p FROM Produto p " +
                "JOIN p.categorias c " +
                "WHERE c.nome LIKE :categoria ORDER BY p.nome ASC";

        return manager.createQuery(jpql, Produto.class)
                .setParameter("categoria", "%"+categoria+"%")
                .getResultList();

    }
}
