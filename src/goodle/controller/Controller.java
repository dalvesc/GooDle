package goodle.controller;

import goodle.model.*;
import goodle.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que controla as interações entre a View e o restante do sistema.
 *
 * Base de dados extraída do Twitter pelo Grupo Adam (http:/adam.uefs.br/)
 *
 * @author Daniel Alves e Gabriela Nunes.
 */
public class Controller {

    Ilist paginas, palavrasBuscadas, paginasVisitadas;
    String diretorio;
    Arvore listaPalavras;

    /**
     * Construtor da classe
     *
     */
    public Controller() {
        diretorio = null;
        palavrasBuscadas = new LinkedList();
        paginasVisitadas = new LinkedList();
        adicionarPalavras();
    }

    /**
     *
     * @return diretório em que os arquivos estão presentes
     */
    public String getDiretorio() {
        return this.diretorio;
    }

    /**
     * Lê os arquivos de texto de determindo diretório e salva suas palavras
     * dentro de uma árvore AVL.
     */
    public void adicionarPalavras() {

        listaPalavras = new Arvore();
        paginas = new LinkedList();
        String[] arquivos = null;
        File arq = null;
        try {
            diretorio = new File("arquivos").getCanonicalPath();
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
                    paginas.addLast(novaPagina);
                    Palavra novaPalavra = new Palavra(word, novaPagina);
                    listaPalavras.inserir(novaPalavra);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Faz a busca da palavra que o usuário deseja.
     *
     * @param palavra palavra que o usuário deseja buscar.
     * @param crescente se for "true", apresenta os resultados de maneira
     * decrescente. Se for "false", de maneira crescente.
     * @return lista de páginas ordenada da palavra buscada.
     */
    public Ilist buscar(Palavra palavra, boolean crescente) {

        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        Ilist paginas = new LinkedList();

        if (!palavrasBuscadas.contains(temp)) {
            palavrasBuscadas.addLast(temp);
        }
        temp.buscas();

        paginas = temp.getlPagina();

        Ilist teste = ordenar(paginas, crescente, 0);
        return teste;
    }

    /**
     * Formata o texto do arquivo, criando um array somente com as palavras e
     * números contidos nele, excluindo qualquer tipo de caractere fora desse
     * limite - como pontuação e símbolos - através do uso da biblioteca
     * "Regex".
     *
     * @param file arquivo de texto que será lido.
     * @return String[] palavras array com as palavras.
     * @throws FileNotFoundException exceção lançada caso a leitura do arquivo
     * não seja possível.
     * @throws UnsupportedEncodingException exceção lançada caso determinado
     * caractere não seja lido corretamente.
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
     * Imprime a página - arquivo de texto - que o usuário deseja visualizar.
     *
     * @param pagina página que será exibida.
     * @throws FileNotFoundException exceção caso a leitura do arquivo não seja
     * efetuada.
     * @throws IOException exceção caso ocorra erro com entrada ou saída de
     * dados.
     */
    public void imprimirPagina(String pagina) throws FileNotFoundException, IOException {
        FileReader arq;
        BufferedReader lerArq;

        String linha;
        String nomePagina = pagina + ".txt";
        File file = new File(diretorio, nomePagina);

        Iterator iterator = this.paginas.iterator();

        while (iterator.hasNext()) {
            Pagina comparar = (Pagina) iterator.next();
            if (nomePagina.compareTo(comparar.getArq()) == 0) {
                comparar.setAcessos();
                
                if (!paginasVisitadas.contains(comparar)) {
                    paginasVisitadas.addLast(comparar);
                }
            }
        }
        arq = new FileReader(file);
        lerArq = new BufferedReader(arq);
        linha = lerArq.readLine();

        while (linha != null) {
            System.out.println(linha);
            linha = lerArq.readLine();
        }
        arq.close();
    }

    /**
     * Deleta a página que o usuário deseja.
     *
     * @param pagina página para deletar.
     * @return boolean para saber se deletou.
     * @throws FileNotFoundException exceção para caso não consiga ler o
     * arquivo.
     * @throws java.io.UnsupportedEncodingException exceção lançada caso
     * determinado caractere não seja lido corretamente
     */
    public boolean deletarPagina(String pagina) throws FileNotFoundException, UnsupportedEncodingException {
        String nomePagina = pagina + ".txt";
        File arq = new File(diretorio, nomePagina);

        Iterator iterator = this.paginas.iterator();

        while (iterator.hasNext()) {
            Pagina comparar = (Pagina) iterator.next();

            if (nomePagina.compareTo(comparar.getArq()) == 0) {
                paginas.remove(comparar);
                paginasVisitadas.remove(comparar);
                removerBusca(comparar);
            }
        }
        listaPalavras = null;
        return arq.delete();
    }

    /**
     * O método chama o MergeSort de acordo com o que deseja ordenar. Ele é
     * utilizado quando a opção de exibir um "Top K" de palavras ou páginas é
     * escolhida pelo usuário.
     *
     * @param objeto indica se a ordenação será de páginas ou palavras.
     * @param crescente e for "true", apresenta os resultados de maneira
     * decrescente. Se for "false", de maneira crescente.
     * @return a lista ordenada.
     */
    public Ilist ranking(String objeto, boolean crescente) {

        Ilist lista = null;

        if (objeto.equals("palavra")) {
            lista = ordenar(palavrasBuscadas, crescente, 0);
        } else if (objeto.equals("pagina")) {
            lista = ordenar(paginasVisitadas, crescente, 1);
        }

        return lista;
    }

    /**
     * Método responsável por requisitar a ordenação de uma lista encadeada, que
     * será efetuada através do método de ordenação "Merge Sort".
     *
     * @param lista coleção que será ordenada.
     * @param crescente se for "true", apresenta os resultados de maneira
     * decrescente. Se for "false", de maneira crescente.
     * @param dif responsável por identificar se as páginas serão ordenadas
     * pelas ocorrências da palavra ou pela quantidade de acessos.
     * @return a lista ordenada.
     */
    private Ilist ordenar(Ilist lista, boolean crescente, int dif) {
        MergeSort merge = new MergeSort();
        Ilist teste = merge.sort(lista, crescente, dif);

        return teste;
    }

    /**
     * Ler o arquivo que será deletado e compara se alguma palavra está na lista
     * de palavras que já foram buscadas. Caso esteja ele d.
     *
     * @param remover Pagina que será lida
     * @throws FileNotFoundException exceção para caso não consiga ler o
     * arquivo.
     * @throws UnsupportedEncodingException exceção lançada caso determinado
     * caractere não seja lido corretamente.
     */
    private void removerBusca(Pagina remover) throws FileNotFoundException, UnsupportedEncodingException {
        File arq = new File(diretorio, remover.getArq());
        String[] palavras = formataTexto(arq);
        Ilist palavrasJaApagadas = new LinkedList();

        for (String word : palavras) {

            if (!palavrasJaApagadas.contains(word)) {

                Iterator iterator = palavrasBuscadas.iterator();
                while (iterator.hasNext()) {
                    Palavra comparar = (Palavra) iterator.next();
                    if (comparar.compareTo(word) == 0) {
                        palavrasJaApagadas.addLast(word);
                        comparar.minBuscas();
                        if (comparar.getBuscas() == 0) {
                            if (palavrasBuscadas.size() == 1) {
                                palavrasBuscadas = new LinkedList();
                                return;
                            }
                            palavrasBuscadas.remove(comparar);
                        }
                    }
                }
            }
        }
    }
}
