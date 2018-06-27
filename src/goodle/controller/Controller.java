package goodle.controller;

import goodle.model.*;
import goodle.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    Ilist paginas;
    String diretorio;

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
        File arq = null;
        try {
            diretorio = new File("hehe").getCanonicalPath(); //PROCURA NO DIRETÓRIO ATUAL PELA PASTA 
            arq = new File(diretorio);
            arquivos = arq.list(); //ESSE MÉTODO DEVOLVE UM ARRAY COM TODOS OS ARQUIVOS QUE ESTÃO NESSA PASTA
            System.out.println(arquivos[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        

        for (String nomeArquivo : arquivos) {//OS ARQUIVOS DE TEXTO SÃO VISITADOS            

            File file = new File(diretorio, nomeArquivo);

            try {

                String[] palavras = formataTexto(file); //AQUI EU CHAMO O MÉTODO QUE "LIMPA" O TEXTO E DEVOLVE UM ARRAY COM TODAS AS PALAVRAS

                for (String word : palavras) {
                    Pagina novaPagina = new Pagina(nomeArquivo);
                    Palavra novaPalavra = new Palavra(word, novaPagina);

                    listaPalavras.inserir(novaPalavra); //CRIO OS OBJETOS E CHAMO O MÉTODO DE INSERIR NA ÁRVORE                  
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
