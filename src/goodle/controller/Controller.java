package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Arvore;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        String[] arquivos = null;
        try{
            String diretorio = new File("arquivos").getCanonicalPath(); //"CRIA" UM ARQUIVO ARQUIVO QUALQUER E PEGA SEU DIRETÓRIO, QUE É O MESMO DIRETÓRIO DO PROGRAMA
            File arq = new File(diretorio);
            arquivos = arq.list();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //File subpasta = null;

//        for (String s : aux) { //ACESSA O VETOR DE ARQUIVOS DO DIRETÓRIO PADRÃO ATÉ ACHAR A PASTA "ARQUIVOS" 
//            if (s.compareTo("hehe") == 0) {
//                subpasta = new File(diretorio, s);//QUANDO ACHA A PASTA, CRIA UM NOVO ARQUIVO PARA ARMAZENÁ-LA
//            }
//
//        }

        for (String nome : arquivos) {//JÁ DENTRO DA SUBPASTA "ARQUIVOS", OS ARQUIVOS DE TEXTO SÃO VISITADOS            

            File file = new File(nome);

            try {
                Scanner scan = new Scanner(new FileReader(file));
                String linha;
                String[] palavras;
                
                System.out.println("ENTROU NO 'TRY'");

                while (scan.hasNext()) {
                    linha = scan.nextLine();
                    palavras = linha.split(" |\n|,|.|:");
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

            } catch (IOException e) {                
                System.out.println(e.getMessage());
                System.out.println(file.getAbsolutePath());
                
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
