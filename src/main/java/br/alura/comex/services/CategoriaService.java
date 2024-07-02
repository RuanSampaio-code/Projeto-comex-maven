package br.alura.comex.services;

import br.alura.comex.dao.CategoriaDao;
import br.alura.comex.models.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {


    private CategoriaDao categoriaDao;

    public CategoriaService(EntityManager entityManager){
        this.categoriaDao =  new CategoriaDao(entityManager );
    }

    public void efetuaCadastroDeCategoria(Categoria categoria) {
        this.categoriaDao.cadastra(categoria);

    }
    public List<Categoria> listarCadastroDeCategorias() {
        return this.categoriaDao.listarTodas();
    }


    public Categoria buscarID(Long id) {
        Categoria busca = categoriaDao.buscarID(id);
        return busca;
    }


    public void removerCategoria(Long id) {
        categoriaDao.remover(id);
    }

    public void alteracaoDeCategoria(Long id, Categoria categoriaAlterada) {
        this.categoriaDao.alterar(id, categoriaAlterada);
    }

    public Categoria BuscaPorNomeCategoria(String nome){

        Categoria categoria = categoriaDao.buscarPorNome(nome);
        if (categoria == null){
            System.out.println("Categoria nao encontrada");
            return categoria;
        }

        return categoria;
    }


}
