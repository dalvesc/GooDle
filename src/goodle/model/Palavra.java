package goodle.model;

import goodle.util.Ilist;

/**
 *
 * @author danco
 */
public class Palavra implements Comparable {

    private String palavra;
    private int quantidade, buscas;
    private Ilist lPagina;

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getBuscas() {
        return buscas;
    }

    public void setBuscas(int buscas) {
        this.buscas = buscas;
    }

    public Ilist getlPagina() {
        return lPagina;
    }

    public void setlPagina(Ilist lPagina) {
        this.lPagina = lPagina;
    }

    /**
     * Controller da classe
     *
     * @param palavra
     */
    public Palavra(String palavra) {
        this.palavra = palavra;
        this.quantidade = 1;
        this.buscas = 0;
        this.lPagina = null;
    }

    /**
     * Cria uma lista para salvar os arquivos em que essa palavra aparece
     *
     * @param pagina pagina em que a palavra aparece
     */
    public void Pagina(Pagina pagina) {
        pagina.Quantidade();
        this.lPagina.addLast(pagina);
    }

    /**
     * Contador para a quantidade de vezes que essa palavra foi buscada
     */
    public void Busca() {
        this.buscas = this.buscas + 1;
    }

    /**
     * Atualiza a quantidade de vezes que a palavra aparece
     *
     * @param quant
     * @return
     */
    public int Quantidade(int quant) {
        this.quantidade = quant;
        return quantidade;
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
