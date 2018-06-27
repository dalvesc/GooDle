package goodle.controller;

import goodle.model.*;
import goodle.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    Arvore listaPalavras;
    boolean size;

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

        try {
            diretorio = new File("hehe").getCanonicalPath(); //PROCURA NO DIRETÓRIO ATUAL PELA PASTA 
            File arq = new File(diretorio);
            arquivos = arq.list(); //ESSE MÉTODO DEVOLVE UM ARRAY COM TODOS OS ARQUIVOS QUE ESTÃO NESSA PASTA
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String nomeArquivo : arquivos) {//OS ARQUIVOS DE TEXTO SÃO VISITADOS            

            File file = new File(diretorio, nomeArquivo);

            try {

                String[] palavras = formataTexto(file); //AQUI EU CHAMO O MÉTODO QUE "LIMPA" O TEXTO E DEVOLVE UM ARRAY COM TODAS AS PALAVRAS

                for (String word : palavras) {
                    Palavra novaPalavra = new Palavra(word);
                    Pagina novaPagina = new Pagina(nomeArquivo);

                    listaPalavras.inserir(novaPalavra, novaPagina); //CRIO OS OBJETOS E CHAMO O MÉTODO DE INSERIR NA ÁRVORE                  
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
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

    /**
     * Formata o texto do arquivo, criando um array somente com as palavras
     * contidas nele
     *
     * @param file
     * @return String[] palavras
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private String[] formataTexto(File file) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(new FileInputStream(file), "UTF-8"); // TENTEI USAR ISSO QUE TU DISSE MAS NÃO DEU CERTO

        String texto = null;
        String textoFormatado = "";
        String[] palavras = null;

        while (scan.hasNext()) {
            texto = scan.nextLine(); // O TEXTO É FORMATADO UMA LINHA DE CADA VEZ

            Pattern pattern = Pattern.compile("[\\p{L}0-9]+{1,}"); //ESSA EXPRESSÃO É RESPONSÁVEL POR PROCURAR UM DETERMINADO PADRÃO NO TEXTO, O PADRÃO NESSE CASO É APENAS LETRAS, NÚMEROS E ESPAÇOS EM BRANCO
            Matcher matcher = pattern.matcher(texto);

            while (matcher.find()) {
                textoFormatado += (matcher.group() + " "); //E AQUI O "MATCHER" DEVOLVE PRA STRING TUDO O QUE ELE ACHOU NO TEXTO QUE SATISFAZ ESSE PADRÃO               
            }
        }
        scan.close();

        palavras = textoFormatado.split(" |\n"); //DEVOLVE UM ARRAY COM TODAS AS PALAVRAS
        return palavras;
    }

    //Falta imprimir os hankings
}
