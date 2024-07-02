package br.alura.comex.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate data = LocalDate.now();
    @Column
    private BigDecimal desconto = BigDecimal.ZERO;
    @Column(name = "tipo_desconto")
    @Enumerated(EnumType.STRING)
    private TipoDeDescontoEnum tipoDesconto;
    private BigDecimal valorTotal = BigDecimal.ZERO;


    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ItemDePedido> itens = new ArrayList<>();
    @ManyToOne
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDeDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDeDescontoEnum tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDePedido> itens) {
        this.itens = itens;
    }


    //    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
//    @Column(nullable = false)
//    private List<ItemDePedido> itens = new ArrayList<>();

//    public Pedido(Cliente cliente) {
//        this.cliente = cliente;
//        this.tipoDesconto = TipoDeDescontoEnum.NENHUM;
//    }

    public void adicionaItemAoPedido(ItemDePedido item){
        this.itens.add(item);
        item.setPedido(this);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", desconto=" + desconto +
                ", tipoDesconto=" + tipoDesconto +
                ", valorTotal=" + valorTotal +
                ", itens=" + listaItens()  +
                ", cliente=" + cliente +
                '}';
    }


        private String listaItens() {
            if (itens == null) {
                return "[]";
            }
            return itens.stream()
                    .map(item -> "ItemDePedido{id=" + item.getPrecoUnitario() +
                            ", produtoNome='" + item.getProduto().getNome() +
                            "', quantidade=" + item.getQuantidade() +
                            "', desconto=" + item.getDesconto() + "}")
                    .collect(Collectors.joining(", ", "[", "]"));
        }
}