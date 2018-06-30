package goodle.model;

import goodle.util.Ilist;
import goodle.util.LinkedList;

/**
 * 
 * @author Daniel Alves e Gabriela Nunes
 */
public class Palavra implements Comparable {

    private String palavra;
    private int quantidade, buscas;
    private Pagina pagina;
    private Ilist lPagina;
 
    /**
     * Construtor da classe
     * @param palavra palavra que foi lida da página
     * @param pagina pagina em que a palavra foi lida pela primeira vez
     */
    public Palavra(String palavra, Pagina pagina) {
        this.palavra = palavra;
        this.pagina = pagina;
        this.quantidade = 1;
        this.buscas = 0;
        this.lPagina = new LinkedList();
        setPagina();
    }
    
    /**
     *Método para adicionar a primeira página na lista
     */
    public void  setPagina(){
        this.pagina.setQuantDaPalavra();
        lPagina.addLast(this.pagina);
    }
    
    /**
     *
     * @return primeira página em que a palavra aparece
     */
    public Pagina getPagina(){
        return this.pagina;
    }
    
    /**
     *
     * @return palavra lida
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     *
     * @return quantidade total da palavra
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     *
     * @return quantidade de vezes que a palavra foi buscada
     */
    public int getBuscas() {
        return buscas;
    }

    /**
     *
     * @return lista com as páginas em que a palavra aparece
     */
    public Ilist getlPagina() {
        return lPagina;
    }

    /**
     *Contador para atualizar a quantidade de vezes que essa palavra foi lida
     */
    public void quantidade(){
        this.quantidade = this.quantidade + 1;
    }
 
    /**
     * Cria uma lista para salvar os arquivos em que essa palavra aparece
     *
     * @param pagina pagina em que a palavra aparece
     */
    public void addPagina(Pagina pagina) {
        this.lPagina.addLast(pagina);    
    }

    /**
     * Contador para a quantidade de vezes que essa palavra foi buscada
     */
    public void buscas() {
        this.buscas = this.buscas + 1;
    }

    /**
     * Compara dois objetos do tipo "palavra" de acordo com seu nome
     * 
     * @param p
     * @return "1" se essa palavra for maior do que a dada por parâmetro, "-1" se for maior e "0" se forem iguais
     */
    @Override
    public int compareTo(Object p) {
        String palavra = (String) p;
        if (this.palavra.compareToIgnoreCase(palavra) > 0) {
            return 1;
        } else if (this.palavra.compareToIgnoreCase(palavra) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 
     * @return o nome da palavra
     */
    @Override
    public String toString() {
        return palavra;
    }
}
