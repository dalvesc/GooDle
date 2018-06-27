package goodle.controller;

import goodle.util.*;
import goodle.model.*;
import java.util.Scanner;

public class ArvoreAVL07 {

    /**
     * ************************************************************************
     *
     * Autor: Daniel Alves Costa e Gabriela dos Santos Costa Nunes
     *
     * Componente Curricular: Programação      *
     * Concluido em: 29/06/2018
     *
     * Declaro que este código foi elaborado por mim e minha dupla e não contém
     * nenhum trecho de código de outro colega ou de outro autor, tais como 
     * provindos de livros e apostilas, e páginas ou documentos eletrônicos 
     * da Internet. Qualquer trecho de código de outra autoria que não a minha 
     * está destacado com uma citação para o autor e a fonte do código, 
     * e estou ciente que estes trechos não serão considerados para fins 
     * de avaliação.
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

        Arvore listaPalavras = new Arvore();
        Pagina pagina = new Pagina("Pagina");
        Scanner scan = new Scanner(System.in);
        Palavra temp = null;
        Controller controller = new Controller(listaPalavras);
        listaPalavras.percorrerPreOrdem(listaPalavras.raiz);
        System.out.println("Digite palavra");
        String palavra = scan.next();
        Palavra pal = new Palavra(palavra, pagina);
        try {
            temp = (Palavra) controller.buscar(listaPalavras, pal);
            System.out.println(temp);
            System.out.println(temp.getQuantidade());
            Iterator iterator = temp.getlPagina().iterator();
            while (iterator.hasNext()) {
                pagina = (Pagina) iterator.next();
                System.out.println(pagina);
            }
        } catch (NullPointerException e) {
            System.out.println("palavra n encontrada");
        }

    }
}
