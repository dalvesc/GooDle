package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Arvore;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Controller {

    Arvore listaPalavras = new Arvore();

    /**
     * Ler os arquivos de texto e salva dentro de uma árvore
     */
    public void AdicionarPalavras() {

        Pagina pagina;
        Palavra palavras;
        String palavra;

        //Só ta lendo 1 arquivo
        try {
            File arq = new File("jogadoresCadastrados.txt");

            pagina = new Pagina(arq.getName());
            Scanner scan = new Scanner(new FileReader(arq)).useDelimiter(" |\n");

            while (scan.hasNext()) {
                palavra = scan.next(); //salvando as palavras lidas em uma string
                palavras = new Palavra(palavra); //criando o objeto palavra
                listaPalavras.inserir(palavras, pagina); //mandando o objeto palavra e pagina para a arvore
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo!");
        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja
     *
     * @param palavra palavra que o usuário deseja buscar
     */
    public Object Buscar(Palavra palavra) {
        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        return temp;
    }

}
