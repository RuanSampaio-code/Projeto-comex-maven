package br.alura.comex.tests;

import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;
import br.alura.comex.services.CategoriaService;
import br.alura.comex.services.ProdutoService;
import br.alura.comex.util.JPAUUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteProduto {
    private static ProdutoService produtoService;
    private static CategoriaService categoriaService;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManager em = JPAUUtil.getEntityManager();
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CATEGORIAS");

        produtoService = new ProdutoService(em);
        categoriaService = new CategoriaService(em);
        var opc = exibirMenu();

        while ( opc != 7){
            switch (opc) {
                case 1:
                    listarTodasProdutos();
                    break;
                case 2:
                    criarProduto();
                    break;
                case 3:
                    deletarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    buscarProduto();
                    break;

                case 6:
                    listarPelaCategoria();
                    break;
            }

            opc = exibirMenu();
        }

    }

    private static int exibirMenu(){

        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos as Produtos
                2 - Criar uma Produtos
                3 - Deletar uma Produto
                4 - Atualizar uma Produto
                5 - Listar um Produto
                6 - Listar Por Categoria
                7 - Finalizar operacoes
       
                """);

        return teclado.nextInt();

    }

    public static void listarTodasProdutos(){
        List<Produto> produtos = produtoService.listarCadastroDeProdutos();

        produtos.stream()
                .forEach(System.out::println);
    }

    public static void criarProduto(){
        System.out.println("CRIANDO NOVO PRODUTO");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome do produto: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a descrição do produto:");
        String descrisao = teclado.nextLine();

        System.out.println("Digite o Preco: ");
        Double preco = teclado.nextDouble();

        produtoService.efetuaCadastroDeProdutos(new Produto(null, nome,descrisao,preco));
        System.out.println("Produto adicionado com sucesso");

    }
    private static void atualizarProduto() {
        //clienteService.alteracaoDeCliente();


        //Busca pelo ID
        System.out.printf("Digite o ID do produto que voce deseja alterar as infromações:");
        Long id = teclado.nextLong();

        //Retorna se existe cliente com este numero
        produtoService.buscarID(id);

        teclado.nextLine();

        System.out.println("Digite o novo Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a nova descrisão:");
        String descrisao = teclado.nextLine();

        System.out.println("Digite o novo valor do produo: ");
        Double preco= teclado.nextDouble();

        teclado.nextLine();

        System.out.println("Digite a nova Categoria: ");
        String categoria= teclado.nextLine();

        Categoria categoriaNova = categoriaService.BuscaPorNomeCategoria(categoria);

        // Cria uma nova lista de categorias e adiciona a nova categoria
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(categoriaNova);

        Produto produtoNovo = new Produto(nome, descrisao,preco, categorias);

        produtoService.alteracaoDeProduto(id,produtoNovo);
       // System.out.printf("Produto alterado com sucesso");

    }

    private static void deletarProduto() {

        System.out.println("Exclusão de cadastro");
        System.out.println("Digite o id do Produto que deseja excluir: ");
        Long id = teclado.nextLong();

        produtoService.removerProduto(id);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    private static void buscarProduto() {

        System.out.println("Digite o id do produto que voce procura:");
        Long id = teclado.nextLong();
        //1 Cliente cliente = clienteService.buscaID(id);
        Produto produto = produtoService.buscarID(id);

        System.out.println("ID: " + produto.getId());
        System.out.println("NOME: " + produto.getNome());
        System.out.println("DESCRISAO: " + produto.getDescricao());
        System.out.println("PREÇO: " + produto.getPreco());

        System.out.println("========================================");
        System.out.println();
    }

    private static void listarPelaCategoria(){
        System.out.println("Vamos listar pela categoria, digite o nome de UMA cagtegoria");
        String categoria = teclado.nextLine();
        teclado.nextLine();
        List<Produto> produtos = produtoService.listarPelaCategoria(categoria);

        produtos.stream()
                .forEach(System.out::println);
    }


}
