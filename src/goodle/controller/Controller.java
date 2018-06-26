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
        String diretorio = null;
        
        try{
            diretorio = new File("arquivos").getCanonicalPath(); //PROCURA NO DIRETÓRIO ATUAL PELA PASTA 
            File arq = new File(diretorio);
            arquivos = arq.list(); //ESSE MÉTODO DEVOLVE UM ARRAY COM TODOS OS ARQUIVOS QUE ESTÃO NESSA PASTA
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
        
        
        for (String nome : arquivos) {//OS ARQUIVOS DE TEXTO SÃO VISITADOS            

            File file = new File(diretorio, nome);

            try {
                Scanner scan = new Scanner(new FileReader(file));
                String linha;
                String[] palavras;
                
                System.out.println("ENTROU NO 'TRY'");

                while (scan.hasNext()) {
                    linha = scan.nextLine();
                    System.out.println(linha);
                    palavras = linha.split(" |\n|,|.|:");
                    int i = 0;

                    while (i < palavras.length) { //CADA LINHA É QUEBRADA EM PALAVRAS, ESSAS PALAVRAS VÃO PARA UM ARRAY
                        Palavra palavra = new Palavra(palavras[i]);
                        Pagina pagina = new Pagina(nome);

                        listaPalavras.inserir(palavra, pagina);
                        System.out.println("INSERIU NA ARVORE");

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
