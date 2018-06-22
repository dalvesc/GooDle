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

    public void setQuantDaPalavra(int quantDaPalavra) {
        this.quantDaPalavra = quantDaPalavra;
    }

    public int getAcesso() {
        return acesso;
    }

    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }

    public String getArq() {
        return arq;
    }

    public void setArq(String arq) {
        this.arq = arq;
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
