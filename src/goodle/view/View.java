package goodle.view;

import goodle.controller.Controller;
import goodle.util.*;
import goodle.model.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ************************************************************************
 *
 * Autor: Daniel Alves Costa e Gabriela dos Santos Costa Nunes
 *
 * Componente Curricular: Programação * Concluido em: 29/06/2018
 *
 * Declaro que este código foi elaborado por mim e minha dupla e não contém
 * nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que não a minha está
 * destacado com uma citação para o autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 *
 **************************************************************************
 */
public class View {

    /**
     * Método principal do programa
     *
     * @author Daniel Alves e Gabriela dos Santos
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Palavra temp = null;

        System.out.println("\t\tGooDle");
        System.out.println("\nCarregando repositório de arquivos...\n");

        Controller controller = new Controller();

        String opcao;
        int sair = 1;

        do {
            System.out.println("O que você deseja?\n"
                    + "[1] - Pesquisar palavra\n"
                    + "[2] - Mostrar ranking\n"
                    + "[0] - Sair");

            opcao = scan.next();
            switch (opcao) {

                case "1":

                    try {
                        buscador(controller);
                    } catch (IOException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "2":
                    ranking(controller);
                    break;
                    
                case "0":
                    sair = 0;
                    break;

                default:
                    System.out.println("Opção inválida! Digite novamente: ");
            }
        } while (sair == 1);
    }

    public static void buscador(Controller controller) throws IOException {
        try {
            Scanner scan = new Scanner(System.in);
            String opcao, abrirPagina;

            System.out.println("Digite palavra");
            String palavra = scan.next();

            Pagina pagina = new Pagina("Pagina");
            Palavra pal = new Palavra(palavra, pagina);

            Ilist ocorrencias = controller.buscar(pal, false);
            System.out.println("\nPara a palavra '" + pal + "': \n");

            Iterator iterator = ocorrencias.iterator();
            while (iterator.hasNext()) {
                pagina = (Pagina) iterator.next();
                System.out.println("*Página: " + pagina.getArq() + ", ocorrências: " + pagina.getQuantDaPalavra() + "*");
            }
            System.out.println("");

            System.out.println("Deseja inverter a ordem  dos resultados?\n"
                    + "[1] - SIM\n"
                    + "[2] - NÃO");
            int ordem = scan.nextInt();

            switch (ordem) {
                case 1:
                    ocorrencias = controller.buscar(pal, true);
                    System.out.println("\nPara a palavra '" + pal + "': \n");

                    iterator = ocorrencias.iterator();
                    while (iterator.hasNext()) {
                        pagina = (Pagina) iterator.next();
                        System.out.println("*Página: " + pagina.getArq() + ", ocorrências: " + pagina.getQuantDaPalavra() + "*");
                    }
                    System.out.println("");

                case 2:
                    break;

                default:
                    System.out.println("Opção inválida, digite novamente: ");
                    ordem = scan.nextInt();
            }

            System.out.println("\nDeseja abrir algum arquivo?\n"
                    + "[1] - SIM\n"
                    + "[2] - NÃO");
            opcao = scan.next();
            switch (opcao) {

                case "1":
                    System.out.println("Digite o nome do arquivo");
                    abrirPagina = scan.next();
                    controller.imprimirPagina(abrirPagina);
                    System.out.println("\nDeseja deletar a página?\n\n"
                            + "[1] - SIM\n"
                            + "[2] - NÃO\n");
                    opcao = scan.next();
                    System.out.println("");
                    switch (opcao) {
                        case "1":
                            if (controller.deletarPagina(abrirPagina)) {
                                System.out.println("\nPágina deletada\n");
                                System.out.println("Atualizando repositório de arquivos...");
                                controller.adicionarPalavras();
                            }
                            break;
                        case "2":
                            System.out.println("\n");
                            break;
                        default:
                            System.out.println("Opção inválida! Digite novamente: ");
                            opcao = scan.next();
                    }
                    break;

                case "2":
                    break;

                default:
                    System.out.println("Opção inválida! Digite novamente: ");
                    opcao = scan.next();
            }
        } catch (NullPointerException e) {
            System.out.println("A palavra buscada não foi encontrada");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo buscado não encontrado");
        }
    }

    public static void ranking(Controller controller) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Listar Top X:\n"
                + "[0] - Palavras mais buscadas\n"
                + "[1] - Palavras menos buscadas\n"
                + "[2] - Páginas mais visitadas\n"
                + "[3] - Páginas menos visitadas\n");
        int listar = scan.nextInt();

        switch (listar) {
            case 0:
                System.out.println("Quantos itens deseja listar?");
                int quant = scan.nextInt();
                Ilist lista = controller.ranking("palavra", true);
                
                if(lista.size() == 0){
                    System.out.println("Não há dados para exibir.");
                    break;
                }
                
                int i = 0;
                Iterator iterador = lista.iterator();
                
                while(iterador.hasNext() && i < quant){
                    Palavra p = (Palavra)iterador.next();
                    System.out.println("\n*Palavra: " + p.getPalavra() + ", buscas: " + p.getBuscas() + "*");
                    i++;
                }
                System.out.println(" ");
                break;
                
            case 1:
                System.out.println("Quantos itens deseja listar?");
                quant = scan.nextInt();
                lista = controller.ranking("palavra", false);
                
                if(lista.size() == 0){
                    System.out.println("Não há dados para exibir.");
                    break;
                }
                
                i = 0;
                iterador = lista.iterator();
                
                while(iterador.hasNext() && i < quant){
                    Palavra p = (Palavra)iterador.next();
                    System.out.println("\n*Palavra: " + p.getPalavra() + ", buscas: " + p.getBuscas() + "*");
                    i++;
                }
                System.out.println(" ");
                break;
            
            case 2:
                System.out.println("Quantos itens deseja listar?");
                quant = scan.nextInt();
                lista = controller.ranking("pagina", true);
                
                if(lista.size() == 0){
                    System.out.println("Não há dados para exibir.\n");
                    break;
                }
                
                i = 0;
                iterador = lista.iterator();
                
                while(iterador.hasNext() && i < quant){
                    Pagina p = (Pagina)iterador.next();
                    System.out.println("\n*Página: " + p.getArq() + ", acessos: " + p.getAcesso() + "*");
                    i++;
                }
                System.out.println(" ");
                break;
                
            case 3:
                System.out.println("Quantos itens deseja listar?");
                quant = scan.nextInt();
                lista = controller.ranking("pagina", false);
                
                if(lista.size() == 0){
                    System.out.println("Não há dados para exibir.\n");
                    break;
                }
                
                i = 0;
                iterador = lista.iterator();
                
                while(iterador.hasNext() && i < quant){
                    Pagina p = (Pagina)iterador.next();
                    System.out.println("\n*Página: " + p.getArq() + ", acessos: " + p.getAcesso() + "*");
                    i++;
                }
                System.out.println(" ");
                break;
                
            default:
                System.out.println("Opção inválida, digite novamente: ");
                listar = scan.nextInt();            
        }
    }
}
