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
 * @author Daniel Alves e Gabriela Nunes.
 */
public class Controller {
    
    Ilist paginas, palavrasBuscadas;
    String diretorio;
    Arvore listaPalavras;    

    /**
     * Construtor da classe
     *
     */
    public Controller() {
        diretorio = null;
        paginas = new LinkedList();
        palavrasBuscadas = new LinkedList();
        listaPalavras = new Arvore();
        adicionarPalavras();
    }

    /**
     * Lê os arquivos de texto de determindo diretório e salva suas palavras dentro de uma
     * árvore AVL.     
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
     * @param crescente se for "true", apresenta os resultados de maneira decrescente. Se for "false", de maneira crescente.
     * @return lista de páginas ordenada da palavra buscada.
     */
    public Ilist buscar(Palavra palavra, boolean crescente) {
        palavrasBuscadas.addLast(palavra);
        
        Palavra temp = (Palavra) listaPalavras.busca(palavra);
        paginas = temp.getlPagina();
        
        Ilist teste = ordenar(paginas, crescente);
        return teste;
    }

    /**
     * Formata o texto do arquivo, criando um array somente com as palavras e números
     * contidos nele, excluindo qualquer tipo de caractere fora desse limite - como pontuação e símbolos - através do uso da biblioteca "Regex".
     *
     * @param file arquivo de texto que seár lido.
     * @return String[] palavras array com as palavras.
     * @throws FileNotFoundException exceção lançada caso a leitura do arquivo não seja possível.
     * @throws UnsupportedEncodingException exceção lançada caso determinado caractere não seja lido corretamente.
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
     * @throws FileNotFoundException exceção caso a leitura do arquivo não seja efetuada.
     * @throws IOException exceção caso ocorra erro com entrada ou saída de dados.
     */
    public void imprimirPagina(String pagina) throws FileNotFoundException, IOException {
               
        FileReader arq;
        BufferedReader lerArq;
        String linha;
        String nomePagina = diretorio + "//" + (String) pagina + ".txt";
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
     * Deleta a página que o usuário deseja.
     *
     * @param pagina página para deletar.
     * @return boolean para saber se deletou.
     * @throws FileNotFoundException exceção para caso não consiga ler o arquivo.
     */
    public boolean deletarPagina(Object pagina) throws FileNotFoundException {
        String nomePagina = diretorio + "\\" + (String) pagina + ".txt";
        File arq = new File(nomePagina);
        return arq.delete();
    }
    
    public void ranking(String lista, int quant){
        
        
        
        
        
        
        
        
    }
    
    /**
     * Método responsável por requisitar a ordenação de uma lista encadeada, que será efetuada através do método de ordenação "Merge Sort". 
     * 
     * @param lista coleção que será ordenada.
     * @param crescente se for "true", apresenta os resultados de maneira decrescente. Se for "false", de maneira crescente.
     * @return a lista ordenada.
     */
    public Ilist ordenar(Ilist lista, boolean crescente){
        MergeSort merge = new MergeSort();
        Ilist teste = merge.sort(paginas, crescente);
        
        return teste;
    }
        
        
        
        
        
        
}

    

