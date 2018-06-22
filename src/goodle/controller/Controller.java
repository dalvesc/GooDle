package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Arvore;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Controller {

    Arvore listaPalavras;

    public Controller() {
        listaPalavras = new Arvore();
        adicionarPalavras();
    }

    /**
     * Lê os arquivos de texto e salva dentro de uma árvore
     */
    public void adicionarPalavras() {

        String diretorio = (new File(".").getAbsolutePath());//"CRIA" UM ARQUIVO ARQUIVO QUALQUER E PEGA SEU DIRETÓRIO, QUE É O MESMO DIRETÓRIO DO PROGRAMA
        File arq = new File(diretorio);
        String[] aux = arq.list();
        File subpasta = null;

        for (String s : aux) { //ACESSA O VETOR DE ARQUIVOS DO DIRETÓRIO PADRÃO ATÉ ACHAR A PASTA "ARQUIVOS" 
            if (s.compareTo("arquivos") == 0) {
                System.out.println(s);
                subpasta = new File(diretorio, s);//QUANDO ACHA A PASTA, CRIA UM NOVO ARQUIVO PARA ARMAZENÁ-LA
            }

        }

        for (String nome : subpasta.list()) {//JÁ DENTRO DA SUBPASTA "ARQUIVOS", OS ARQUIVOS DE TEXTO SÃO VISITADOS

            System.out.println(nome);

            File file = new File(nome);

            try {
                Scanner scan = new Scanner(new FileReader(file));
                String linha;
                String[] palavras;

                while (scan.hasNext()) {
                    linha = scan.nextLine();
                    palavras = linha.split(" |\n|,|.");
                    int i = 0;

                    while (i < palavras.length) { //CADA LINHA É QUEBRADA EM PALAVRAS, ESSAS PALAVRAS VÃO PARA UM ARRAY
                        Palavra palavra = new Palavra(palavras[i]);
                        Pagina pagina = new Pagina(nome);

                        listaPalavras.inserir(palavra, pagina);

                        System.out.println(palavras[i]);

                        i++;
                    }
                }
                scan.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja
     *
     * @param palavra palavra que o usuário deseja buscar
     */
    public Object buscar(Palavra palavra) {
        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        return temp;
    }

}
