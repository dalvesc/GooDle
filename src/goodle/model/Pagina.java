package goodle.model;

public class Pagina {

    private int quantDaPalavra, acesso;

    /**
     * Controller da classe
     */
    public Pagina() {
        this.quantDaPalavra = 0;
        this.acesso = 0;
    }

    /**
     * Contador para a quantidade de vezes que determinada palavra aparece acho
     * que vai precisar mudar isso
     */
    public void Quantidade() {
        this.quantDaPalavra = quantDaPalavra + 1;
    }

    /**
     * Contador para a quantidade de acessos na página
     */
    public void Acesso() {
        this.acesso = acesso + 1;
    }
}
