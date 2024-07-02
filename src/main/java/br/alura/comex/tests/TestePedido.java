package br.alura.comex.tests;

import br.alura.comex.models.*;
import br.alura.comex.services.ClienteService;
import br.alura.comex.services.PedidoService;
import br.alura.comex.services.ProdutoService;
import br.alura.comex.util.JPAUUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TestePedido {
    private static PedidoService pedidoService;
    private static ClienteService clienteService;

    private static ProdutoService produtoService;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManager em = JPAUUtil.getEntityManager();
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE PEDIDOS");

        pedidoService = new PedidoService(em);
        clienteService = new ClienteService(em);
        produtoService = new ProdutoService(em);
        var opc = exibirMenu();

        while ( opc != 6){
            switch (opc) {
                case 1:
                    listarTodasPedidos();
                    break;
                case 2:
                   criarPedido();
                    break;
                case 3:
                    deletarPedido();
                    break;
//                case 4:
//                    atualizarPedido();
//                    break;
//                case 5:
//                    buscarPedido();
//                    break;
            }

            opc = exibirMenu();
        }

    }

    private static int exibirMenu(){

        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos as Pedidos
                2 - Criar uma Pedidos
                3 - Deletar uma Pedido
                4 - Atualizar uma Pedido
                5 - Listar uma Pedido
                6 - Finalizar operacoes
       
                """);

        return teclado.nextInt();

    }

    public static void listarTodasPedidos(){
        List<Pedido> produtos = pedidoService.listarCadastroDePedidos();

        produtos.stream()
                .forEach(System.out::println);
    }

    public static void criarPedido(){

        System.out.println("CRIANDO NOVO PEDIDO");

        System.out.println("Digite o ID do Cliente: ");
        Long clienteId = teclado.nextLong();

        // Obtém o cliente do serviço (presumindo que há um método para isso)
        Cliente cliente = clienteService.buscarID(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }


        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setDesconto(BigDecimal.ZERO);
        pedido.setValorTotal(BigDecimal.ZERO);
        pedido.setCliente(cliente);

        boolean adicionarMaisItens = true;
        while (adicionarMaisItens) {
            System.out.println("Adicionar novo item ao pedido? (s/n): ");
            String resposta = teclado.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                adicionarMaisItens = false;
                continue;
            }

            System.out.println("Digite o ID do Produto: ");
            Long produtoId = Long.parseLong(teclado.nextLine());

            Produto produto = produtoService.buscarID(produtoId);
            if (produto == null) {
                System.out.println("produto não encontrado!");
                return;
            } else {
                System.out.println("produto encontrado");
                System.out.println(produto);
            }


            System.out.println("Digite a Quantidade: ");
            int quantidade = Integer.parseInt(teclado.nextLine());

            System.out.println("Digite o Preço Unitário: ");
            BigDecimal precoUnitario = new BigDecimal(teclado.nextLine());

            ItemDePedido item = new ItemDePedido();
            item.setProduto(produto);  // Assumindo que ItemDePedido tem um campo produtoId
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(precoUnitario);
            item.setPedido(pedido);

            pedido.adicionaItemAoPedido(item);
            pedido.setValorTotal(pedido.getValorTotal().add(precoUnitario.multiply(BigDecimal.valueOf(quantidade))));

            pedidoService.efetuaCadastroDePedidos(pedido);
        }

    }

    public static void deletarPedido(){
        System.out.println("Digite o id do pedido que deseja deletar:");
        Long id = teclado.nextLong();

        pedidoService.removerPedido(id);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();


    }


}
