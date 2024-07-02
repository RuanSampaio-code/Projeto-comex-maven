package br.com.alura.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    private double preco;

    @ManyToMany
    @JoinColumn(name = "categoria_id")
    @Column
    //private Categoria categoria;
    private List<Categoria> categorias = new ArrayList<>();

    public Produto( String nome, String descricao, double preco, List<Categoria> categorias) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categorias = categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Produto() {

    }

    public Produto(Long id, String nome, String descrisao, Double preco) {
    }

    public Long getId() {
        return id;
    }



    public Produto(String nome, String descricao, double preco) {

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categorias=" + categorias +
                '}';
    }
}
