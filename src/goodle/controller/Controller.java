package goodle.controller;

import goodle.model.*;
import goodle.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danco
 */
public class Controller {

    Ilist paginas;
    String diretorio;
    Arvore listaPalavras;

    /**
     * Construtor da classe
     *
     */
    public Controller() {
        diretorio = null;
        paginas = new LinkedList();
        listaPalavras = new Arvore();
        adicionarPalavras();
    }

    /**
     * Lê os arquivos de texto de determindo diretorio e salva dentro de uma
     * árvore
     *
     */
    public void adicionarPalavras() {

        String[] arquivos = null;
        File arq = null;
        try {
            diretorio = new File("hehe").getCanonicalPath();
            arq = new File(diretorio);
            arquivos = arq.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String nomeArquivo : arquivos) {

            File file = new File(diretorio, nomeArquivo);

            try {

                String[] palavras = formataTexto(file);

                for (String word : palavras) {
                    Pagina novaPagina = new Pagina(nomeArquivo);
                    Palavra novaPalavra = new Palavra(word, novaPagina);

                    listaPalavras.inserir(novaPalavra);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja
     *
     * @param palavra palavra que o usuário deseja buscar
     * @return palavra que foi buscada
     */
    public Palavra buscar(Palavra palavra) {
        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        temp.buscas();
        return temp;
    }

    /**
     * Formata o texto do arquivo, criando um array somente com as palavras
     * contidas nele
     *
     * @param file arquivo que irá ser lido
     * @return String[] palavras array com as palavras
     * @throws FileNotFoundException exceção para caso não consiga ler o arquivo
     * @throws UnsupportedEncodingException exceção para caso não consiga ler
     * determinado caractere
     */
    private String[] formataTexto(File file) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(new FileInputStream(file), "UTF-8");

        String texto = null;
        String textoFormatado = "";
        String[] palavras = null;

        while (scan.hasNext()) {
            texto = scan.nextLine();

            Pattern pattern = Pattern.compile("[\\p{L}0-9]+{1,}");
            Matcher matcher = pattern.matcher(texto);

            while (matcher.find()) {
                textoFormatado += (matcher.group() + " ");
            }
        }
        scan.close();

        palavras = textoFormatado.split(" |\n");
        return palavras;
    }

    /**
     * Imprime a página que o usuário deseja visualizar
     *
     * @param pagina página que será exibida
     * @throws FileNotFoundException exceção para caso não consiga ler o arquivo
     * @throws IOException exceção para caso ocorra erro com entrada ou saída de
     * dados
     */
    public void imprimirPagina(Object pagina) throws FileNotFoundException, IOException {
        FileReader arq;
        BufferedReader lerArq;
        String linha;
        String nomePagina = diretorio + "\\" + (String) pagina + ".txt";
        Iterator iterator = this.paginas.iterator();

        while (iterator.hasNext()) {
            Pagina comparar = (Pagina) iterator.next();
            if (pagina.equals(comparar.getArq())) {
                comparar.quantAcesso();
            }
        }

        arq = new FileReader(nomePagina);
        lerArq = new BufferedReader(arq);
        linha = lerArq.readLine();
        System.out.println("\n");
        while (linha != null) {
            System.out.println(linha);
            linha = lerArq.readLine();
        }
        arq.close();
    }

    /**
     * Deleta a página que o usuário deseja
     *
     * @param pagina página para deletar
     * @return boolean para saber se deletou
     * @throws FileNotFoundException exceção para caso não consiga ler o arquivo
     */
    public boolean deletarPagina(Object pagina) throws FileNotFoundException {
        String nomePagina = diretorio + "\\" + (String) pagina + ".txt";
        File arq = new File(nomePagina);
        return arq.delete();
    }

    //Falta imprimir os hankings
}
