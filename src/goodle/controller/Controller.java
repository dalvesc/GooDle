package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Ilist;
import goodle.util.LinkedList;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author danco
 */
public class Controller {

    Ilist listaPalavras = new LinkedList();

    /**
     * Ler os arquivos de texto e salva dentro de uma lista
     */
    public void AdicionarPalavras() {

        Pagina pagina;
        Palavra palavras;
        String palavra;

        //Só ta lendo 1 arquivo
        try {
            File arq = new File("jogadoresCadastrados.txt");

            pagina = new Pagina(arq);
            Scanner scan = new Scanner(new FileReader(arq)).useDelimiter(" |\n");

            //Falta a condição caso a palavra já tenha aparecido ateriormente
            while (scan.hasNext()) {
                palavra = scan.next();

                palavras = new Palavra(palavra);
                palavras.Pagina(pagina);

                listaPalavras.addLast(palavras);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo!");
        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja
     * @param palavra palavra que o usuário deseja buscar
     */
    public void Buscar(Palavra palavra) {
    }

}
