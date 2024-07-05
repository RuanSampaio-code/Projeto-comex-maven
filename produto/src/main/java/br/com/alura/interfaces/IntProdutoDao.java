package br.com.alura.interfaces;

import br.com.alura.models.Produto;

import java.util.List;

public interface IntProdutoDao {

    void cadastra(Produto produto);

    List<Produto> listarTodas();

    Produto buscarID(Long id);

    void alterar(Long id, Produto produto);

    void remover(Long id);
}
