package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Arvore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    File subpasta;

    public Controller(Arvore listaPalavras) {
        subpasta = null;
        adicionarPalavras(listaPalavras);
    }

    /**
     * Lê os arquivos de texto e salva dentro de uma árvore
     *
     * @param listaPalavras
     */
    public void adicionarPalavras(Arvore listaPalavras) {

        String diretorio = (new File(".").getAbsolutePath());//"CRIA" UM ARQUIVO ARQUIVO QUALQUER E PEGA SEU DIRETÓRIO, QUE É O MESMO DIRETÓRIO DO PROGRAMA
        File arq = new File(diretorio);
        String[] aux = arq.list();

        for (String s : aux) { //ACESSA O VETOR DE ARQUIVOS DO DIRETÓRIO PADRÃO ATÉ ACHAR A PASTA "ARQUIVOS" 
            if (s.compareTo("arquivos") == 0) {
                subpasta = new File(diretorio, s);//QUANDO ACHA A PASTA, CRIA UM NOVO ARQUIVO PARA ARMAZENÁ-LA
            }
        }

        for (String nome : subpasta.list()) {//JÁ DENTRO DA SUBPASTA "ARQUIVOS", OS ARQUIVOS DE TEXTO SÃO VISITADOS

            File file = new File(subpasta.getAbsoluteFile() + "\\" + nome);

            try {
                Scanner scan = new Scanner(new FileReader(file));
                String linha;
                String[] palavras;
                while (scan.hasNext()) {
                    linha = scan.nextLine();
                    palavras = linha.split(" |,|!|\\|.|;|:|@|%|&|\n|#");
                    int i = 0;

                    while (i < palavras.length) { //CADA LINHA É QUEBRADA EM PALAVRAS, ESSAS PALAVRAS VÃO PARA UM ARRAY
                        Palavra palavra = new Palavra(palavras[i]);
                        Pagina pagina = new Pagina(nome);

                        listaPalavras.inserir(palavra, pagina);

                        i++;
                    }
                }
                scan.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja
     *
     * @param listaPalavras
     * @param palavra palavra que o usuário deseja buscar
     * @return
     */
    public Object buscar(Arvore listaPalavras, Palavra palavra) {
        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        return temp;
    }

    public void imprimirPagina(Object pagina) throws FileNotFoundException, IOException {
        FileReader arq;
        BufferedReader lerArq;
        String linha;
        String nomePagina = subpasta.getAbsoluteFile() + "\\" + (String) pagina;
        try {
            arq = new FileReader(nomePagina);
            lerArq = new BufferedReader(arq);
            linha = lerArq.readLine();

            while (linha != null) {
                System.out.println(linha);
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
