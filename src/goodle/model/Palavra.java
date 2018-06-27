package goodle.model;

import goodle.util.Ilist;
import goodle.util.LinkedList;

public class Palavra implements Comparable {

    //pensando em coloca para receber a pagina no controller
    private String palavra;
    private int quantidade, buscas;
    private Pagina pagina;
    private Ilist lPagina;
  
    /**
     * Controller da classe
     *
     * @param palavra
     * @param pagina
     */
    public Palavra(String palavra, Pagina pagina) {
        this.palavra = palavra;
        this.pagina = pagina;
        this.quantidade = 1;
        this.buscas = 0;
        this.lPagina = new LinkedList();
        setPagina();
    }
    
    public void  setPagina(){
        this.pagina.quantDaPalavra();
        lPagina.addLast(this.pagina);
    }
    
    public Pagina getPagina(){
        return this.pagina;
    }
    
    public String getPalavra() {
        return palavra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getBuscas() {
        return buscas;
    }

    public Ilist getlPagina() {
        return lPagina;
    }

    public void quantidade(){
        this.quantidade = this.quantidade + 1;
    }
 
    /**
     * Cria uma lista para salvar os arquivos em que essa palavra aparece
     *
     * @param pagina pagina em que a palavra aparece
     */
    public void addPagina(Pagina pagina) {
        pagina.quantDaPalavra();
        this.lPagina.addLast(pagina);    
    }

    /**
     * Contador para a quantidade de vezes que essa palavra foi buscada
     */
    public void buscas() {
        this.buscas = this.buscas + 1;
    }

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

    @Override
    public String toString() {
        return palavra;
    }
}
