package goodle.model;

public class Pagina {

    private int quantDaPalavra, acesso;
    String arq;

    /**
     * Controller da classe
     *
     * @param arq nome do arquivo
     */
    public Pagina(String arq) {
        this.arq = arq;
        this.quantDaPalavra = 0;
        this.acesso = 0;
    }

    public int getQuantDaPalavra() {
        return quantDaPalavra;
    }

    public int getAcesso() {
        return acesso;
    }

    public String getArq() {
        return arq;
    }

    /**
     * Contador para a quantidade de vezes que determinada palavra aparece acho
     * que vai precisar mudar isso
     */
    public void quantidade() {
        this.quantDaPalavra = quantDaPalavra + 1;
    }

    /**
     * Contador para a quantidade de acessos na página
     */
    public void acesso() {
        this.acesso = acesso + 1;
    }
}
