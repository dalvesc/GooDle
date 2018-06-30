package goodle.view;

import goodle.controller.Controller;
import goodle.util.*;
import goodle.model.*;
import java.util.Scanner;

public class View {

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
     * destacado com uma citação para o autor e a fonte do código, e estou
     * ciente que estes trechos não serão considerados para fins de avaliação.
     *
     **************************************************************************
     */
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
                    + "[0] - Sair\n");

            opcao = scan.next();
            switch (opcao) {

                case "1":
                    try {
                        Pagina pagina = new Pagina("Pagina");
                        System.out.println("Digite palavra");
                        String palavra = scan.next();
                        Palavra pal = new Palavra(palavra, pagina);
                        Ilist ocorrencias = controller.buscar(pal);
                        System.out.println("\nPara a palavra '" + pal +"': \n"); 
                                
                        Iterator iterator = ocorrencias.iterator();
                        while (iterator.hasNext()) {
                            pagina = (Pagina) iterator.next(); 
                            System.out.println("*Página: " + pagina.getArq() + ", ocorrências: " + pagina.getQuantDaPalavra() + "*");
                        }
                        System.out.println("");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    sair = 0;
                    break;

                default:
                    System.out.println("Opção inválida! Digite novamente: ");
            }
        } while (sair == 1);
    }
}
