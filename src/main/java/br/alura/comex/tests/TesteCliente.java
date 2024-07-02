package br.alura.comex.tests;


import br.alura.comex.exception.EntidadeNaoEncontradaException;
import br.alura.comex.models.Cliente;
import br.alura.comex.services.ClienteService;
import br.alura.comex.util.JPAUUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class TesteCliente {

    //Atributos da classe de chamada
    private static ClienteService clienteService;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        //OPCAO NO TECLADO
        EntityManager em = JPAUUtil.getEntityManager();
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CLIENTES");
        var opc = exibirMenu();
        clienteService = new ClienteService(em);

        while (opc != 6){

            try{
                switch (opc) {
                    case 1:
                        listarTodosClientes();
                        break;
                    case 2:
                        criarCliente();
                        break;
                    case 3:
                        deletarCliente();
                        break;
                    case 4:
                        atualizarCliente();
                        break;
                    case 5:
                        buscarCliente();
                        break;
                }

            }catch (EntidadeNaoEncontradaException e){
                throw  new EntidadeNaoEncontradaException("Error");
            }

            opc = exibirMenu();


        }

    }


    private static int exibirMenu(){
        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos os clientes
                2 - Criar um cliente 
                3 - Deletar um cliente
                4 - Atualizar um cliente
                5 - Listar um cliente
                6 - Finalizar operacoes
       
                """);
        return teclado.nextInt();

    }

    private static void criarCliente() {
        System.out.println("CRIANDO NOVO CLIENTE");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");

        System.out.println("Digite o CPF:");
        String cpf = teclado.nextLine();

        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o Email: ");
        String email = teclado.nextLine();

        System.out.println("Digite o Telefone: ");
        String telefone = teclado.nextLine();

        System.out.println("Digite o Logradouro: ");
        String logradouro = teclado.nextLine();

        System.out.println("Digite o Bairro: ");
        String bairro = teclado.nextLine();

        System.out.println("Digite a Cidade: ");
        String cidade = teclado.nextLine();

        System.out.println("Digite o Estado: ");
        String estado = teclado.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = teclado.nextLine();

        Cliente cliente = new Cliente(cpf, nome, email, telefone, logradouro, bairro, cidade, estado, cep);

        clienteService.efetuaCadastroDeCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso");
    }



    private static void atualizarCliente() {
        //clienteService.alteracaoDeCliente();

        //Busca pelo ID
        System.out.printf("Digite o ID do cliente que voce deseja alterar as infromações:");
        Long id = teclado.nextLong();

        //Retorna se existe cliente com este numero
        clienteService.buscarID(id);

        teclado.nextLine();

        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = teclado.nextLine();

        System.out.println("Digite o Email: ");
        String email = teclado.nextLine();

        System.out.println("Digite o Telefone: ");
        String telefone = teclado.nextLine();

        System.out.println("Digite o Logradouro: ");
        String logradouro = teclado.nextLine();

        System.out.println("Digite o Bairro: ");
        String bairro = teclado.nextLine();

        System.out.println("Digite a Cidade: ");
        String cidade = teclado.nextLine();

        System.out.println("Digite o Estado: ");
        String estado = teclado.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = teclado.nextLine();

        Cliente novoCliente = new Cliente(cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep);

        //clienteService.efetuaCadastroDeCliente( new Cliente( null,cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep));
        clienteService.alteracaoDeCliente(id,novoCliente);
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void deletarCliente() {

        System.out.printf("Exclusão de cadastro");
        System.out.println("Digite o id do cliente que deseja excluir: ");
        Long id = teclado.nextLong();

        clienteService.removerCliente(id);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void listarTodosClientes() {

        var clientes = clienteService.listarCadastroDeClientes();
        clientes.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void buscarCliente() {

        System.out.println("Digite o id do cliente que voce procura:");
        Long id = teclado.nextLong();
        //1 Cliente cliente = clienteService.buscaID(id);
        Cliente cliente = clienteService.buscarID(id);

        System.out.println("ID: " + cliente.getId());
        System.out.println("NOME: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("EMAIL: " + cliente.getEmail());
        System.out.println("TELEFONE: " + cliente.getTelefone());
        System.out.println("LOGRADOURO: " + cliente.getLogradouro());
        System.out.println("BAIRRO: " + cliente.getBairro());
        System.out.println("CIDADE: " + cliente.getCidade());
        System.out.println("ESTADO: " + cliente.getEstado());
        System.out.println("CEP: " + cliente.getCep());
        System.out.println("========================================");
        System.out.println();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


}
