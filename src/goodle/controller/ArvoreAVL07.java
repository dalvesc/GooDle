package goodle.controller;

import goodle.util.Arvore;
import goodle.model.*;
import java.util.Scanner;

public class ArvoreAVL07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Arvore listaPalavras = new Arvore();
        Scanner scan = new Scanner(System.in);
        Controller controller = new Controller(listaPalavras);
        listaPalavras.percorrerPreOrdem(listaPalavras.raiz);
        System.out.println("Digite palavra");
        String palavra = scan.next();
        Palavra pal = new Palavra(palavra);
        Palavra temp = (Palavra) controller.buscar(listaPalavras, pal);
        if (temp == null) {
            System.out.println("palavra n encontrada");
            return;
        }
        System.out.println(temp);
        System.out.println(temp.getQuantidade());
    }
}
