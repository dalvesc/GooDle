package goodle.controller;

import goodle.model.*;
import goodle.util.*;
import java.io.*;
import java.util.Scanner;

public class Controller {

    Ilist paginas;
    String diretorio;
    public int size = 0;

    public Controller(Arvore listaPalavras) {
        diretorio = null;
        paginas = new LinkedList();
        adicionarPalavras(listaPalavras);
    }

    /**
     * Lê os arquivos de texto e salva dentro de uma árvore
     *
     * @param listaPalavras
     */
    public void adicionarPalavras(Arvore listaPalavras) {

        String[] arquivos = null;

        try {
            diretorio = new File("arquivos").getCanonicalPath(); //PROCURA NO DIRETÓRIO ATUAL PELA PASTA 
            File arq = new File(diretorio);
            arquivos = arq.list(); //ESSE MÉTODO DEVOLVE UM ARRAY COM TODOS OS ARQUIVOS QUE ESTÃO NESSA PASTA
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String nome : arquivos) {//OS ARQUIVOS DE TEXTO SÃO VISITADOS            

            File file = new File(diretorio, nome);

            try {
                Scanner scan = new Scanner(new FileInputStream(file), "UTF-8");
                String linha;
                String[] palavras;

                Pagina pagina = new Pagina(nome);
                paginas.addLast(pagina);
                //System.out.println("ENTROU NO 'TRY'");
                while (scan.hasNext()) {
                    linha = scan.nextLine();
                    //System.out.println(linha);
                    palavras = linha.split(" |,|!|\\.|\\;|:|@|%|&|\n|#|-|\\?|\\/|\\+|_|=");
                    int i = 0;

                    while (i < palavras.length) { //CADA LINHA É QUEBRADA EM PALAVRAS, ESSAS PALAVRAS VÃO PARA UM ARRAY
                        Palavra palavra = new Palavra(palavras[i]);

                        listaPalavras.inserir(palavra, pagina);
                        //System.out.println("INSERIU NA ARVORE");
                        size++;
                        //System.out.println(palavras[i]);

                        i++;
                    }
                }
                scan.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println(file.getAbsolutePath());

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
        String nomePagina = diretorio + "\\" + (String) pagina;
        Iterator iterator = this.paginas.iterator();
        
        while (iterator.hasNext()) {
            Pagina comparar = (Pagina) iterator.next();
            if (pagina.equals(comparar.getArq())) {
                comparar.quantAcesso();
            }
        }
        
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
    
    //Falta imprimir os hankings
}
