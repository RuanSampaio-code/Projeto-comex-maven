package br.alura.comex.tests;

import br.alura.comex.models.Categoria;
import br.alura.comex.services.CategoriaService;
import br.alura.comex.util.JPAUUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class TesteCategoria {
    private  static CategoriaService categoriaService;
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        EntityManager em = JPAUUtil.getEntityManager();
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CATEGORIAS");

        categoriaService = new CategoriaService(em);
        var opc = exibirMenu();

        while ( opc != 7){
            switch (opc) {
                case 1:
                    listarTodasCategorias();
                    break;
                case 2:
                    criarCategoria();
                    break;
                case 3:
                    deletarCategoria();
                    break;
                case 4:
                    atualizarCategoria();
                    break;
                case 5:
                    buscarCategoria();
                    break;
                case 6:
                    buscaPeloNome();
                    break;
            }

            opc = exibirMenu();
        }

    }
    private static int exibirMenu(){

        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos as Categorias
                2 - Criar uma Categorias
                3 - Deletar uma Categoria
                4 - Atualizar uma Categoria
                5 - Listar uma Categoria
                6 - Buscar catgoria por nome
                7 - Finalizar operacoes
       
                """);

        return teclado.nextInt();

    }

    private static void atualizarCategoria() {
        System.out.println("Digite o id do cliente que voce procura:");
        Long id = teclado.nextLong();

        categoriaService.buscarID(id);
        teclado.nextLine();

        System.out.println("Digite o novo nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a nova descrisao: ");
        String descr = teclado.nextLine();

        Categoria categoriaAlterada = new Categoria( nome,descr);

        //clienteService.efetuaCadastroDeCliente( new Cliente( null,cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep));
        categoriaService.alteracaoDeCategoria(id,categoriaAlterada);
        System.out.printf("Cliente altrado com sucesso");


    }

    private static void deletarCategoria() {
        System.out.printf("Exclusão de cadastro");
        System.out.println("Digite o id da categoria que deseja excluir: ");
        Long id = teclado.nextLong();

        categoriaService.removerCategoria(id);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void criarCategoria() {
        System.out.println("CRIANDO NOVA CATEGORIA");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a descrição: ");
        String desc = teclado.nextLine();

        categoriaService.efetuaCadastroDeCategoria(new Categoria( nome, desc));
    }

    private static void listarTodasCategorias() {
        List<Categoria> listaDeCategorias = categoriaService.listarCadastroDeCategorias();

        listaDeCategorias.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void buscarCategoria() {
        System.out.println("Digite o id da categoria que voce procura:");
        Long id = teclado.nextLong();
        //1 Cliente cliente = clienteService.buscaID(id);
        Categoria categoria = categoriaService.buscarID(id);

        System.out.println("ID: " + categoria.getId());
        System.out.println("NOME: " + categoria.getNome());

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    public static void buscaPeloNome(){
        teclado.nextLine();
        System.out.println("Digite o nome da categoria que voce procura:");
        String nome = teclado.nextLine();

        Categoria categoria = categoriaService.BuscaPorNomeCategoria(nome);
        System.out.println(categoria);
    }

}
