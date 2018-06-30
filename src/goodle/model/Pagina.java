package goodle.model;

import java.util.Objects;

/**
 *
 * @author Daniel Alves e Gabriela Nunes
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
     * Atualiza a quantidade da palavra 
     */
    public void setQuantDaPalavra() {
        this.quantDaPalavra = quantDaPalavra + 1;
    }

    /**
     * Contador para a quantidade de acessos na página
     */
    public void quantAcesso() {
        this.acesso = acesso + 1;
    }

    /**
     * Compara dois objetos entre si
     * 
     * @param obj
     * @return "true" se os objetos forem iguais e "false" se forem diferentes
     */
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

    /**
     *
     * @return texto informando o nome da página e as ocorrências da palavra
     */
    @Override
    public String toString() {
        return "na Página " + arq + " aparece " + quantDaPalavra + " vez(es)";
    }

    
}
