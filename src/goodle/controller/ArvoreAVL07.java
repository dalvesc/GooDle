package goodle.controller;

import goodle.util.Arvore;
import goodle.model.*;
import goodle.util.Iterator;
import java.util.Scanner;

public class ArvoreAVL07 {

    /**
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
