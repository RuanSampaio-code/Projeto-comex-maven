package br.alura.comex.interfaces;

import br.alura.comex.models.Categoria;

import java.util.List;

public interface IntCategoriaDao {

    void cadastra(Categoria categoria);

    List<Categoria> listarTodas();

    Categoria buscarID(Long id);

    void alterar(Long id, Categoria categoriaAlterar);

    void remover(Long id);

    Categoria buscarPorNome(String nome);


}
