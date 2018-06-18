package goodle.model;

import goodle.util.Ilist;

/**
 *
 * @author danco
 */
public class Palavra {

    private String palavra;
    private int quantidade, buscas;
    private Ilist lPagina;

    /**
     * Controller da classe
     *
     * @param palavra
     */
    public Palavra(String palavra) {
        this.palavra = palavra;
        this.quantidade = 1;
        this.buscas = 0;
    }

    /**
     * Cria uma lista para salvar os arquivos em que essa palavra aparece
     *
     * @param pagina pagina em que a palavra aparece
     * @return retorna a lista
     */
    public Ilist Pagina(Pagina pagina) {
        pagina.Quantidade();
        this.lPagina.addLast(pagina);
        return this.lPagina;
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
}
