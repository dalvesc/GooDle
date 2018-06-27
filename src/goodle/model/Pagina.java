package goodle.model;

import java.util.Objects;

/**
 *
 * @author danco
 */
public class Pagina {

    private int quantDaPalavra, acesso;
    String arq;

    /**
     * Construtor da classe
     *
     * @param arq nome do arquivo
     */
    public Pagina(String arq) {
        this.arq = arq;
        this.quantDaPalavra = 0;
        this.acesso = 0;
    }

    /**
     *
     * @return quantidade da palavra que está presente na página
     */
    public int getQuantDaPalavra() {
        return quantDaPalavra;
    }

    /**
     *
     * @return quantidade de vezes que a página foi acessada
     */
    public int getAcesso() {
        return acesso;
    }

    /**
     *
     * @return nome da página
     */
    public String getArq() {
        return arq;
    }

    /**
     * Contador para a quantidade de vezes que determinada palavra aparece 
     * 
     */
    public void quantDaPalavra() {
        this.quantDaPalavra = quantDaPalavra + 1;
    }

    /**
     * Contador para a quantidade de acessos na página
     */
    public void quantAcesso() {
        this.acesso = acesso + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagina other = (Pagina) obj;
        if (!Objects.equals(this.arq, other.arq)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Na Página " +arq + " aparece " +quantDaPalavra +" vezes";
    }
}
