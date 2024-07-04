package br.com.alura.interfaces;


import br.com.alura.models.Categoria;

import java.util.List;

public interface IntCategoriaDao {

    void cadastra(Categoria categoria);

    List<Categoria> listarTodas();

    Categoria buscarID(Long id);

    void alterar(Long id, Categoria categoriaAlterar);

   // void alterar(Long id, Categoria categoriaAlterar);

    void remover(Long id);

    Categoria buscarPorNome(String nome);


}
