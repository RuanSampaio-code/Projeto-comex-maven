package br.com.alura.service;

import br.alura.comex.dao.ProdutoDao;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoService {
    private ProdutoDao produtoDao;


    public ProdutoService(EntityManager manager){
        this.produtoDao = new ProdutoDao(manager);
    }

    public void efetuaCadastroDeProdutos(Produto produto) {
        this.produtoDao.cadastra(produto);

    }
    public List<Produto> listarCadastroDeProdutos() {
        return this.produtoDao.listarTodas();
    }


    public Produto buscarID(Long id) {
        Produto busca = produtoDao.buscarID(id);
        return busca;
    }


    public void removerProduto(Long id) {
        produtoDao.remover(id);
    }

    public void alteracaoDeProduto(Long id, Produto produtAlterada) {
        this.produtoDao.alterar(id, produtAlterada);
    }

    public List<Produto> listarPelaCategoria(String categoria){

        return  this.produtoDao.listarPelaCategoria(categoria);
    }


}
