package goodle.model;

import java.io.File;

public class Pagina {

    private int quantDaPalavra, acesso;
    File arq;
    
    /**
     * Controller da classe
     */
    public Pagina(File arq) {
        this.arq = arq;
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
