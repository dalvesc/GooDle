package goodle.model;

import java.util.Objects;

/**
 * A classe implementa a interface "Comparable" para facilitar a comparação
 * entre dois objetos pertencentes a ela.
 *
 * @author Daniel Alves e Gabriela Nunes
 */
public class Pagina implements Comparable {

    private int quantDaPalavra, acesso;
    String arq;

    /**
     * Construtor da classe.
     *
     * @param arq nome do arquivo.
     */
    public Pagina(String arq) {
        this.arq = arq;
        this.quantDaPalavra = 0;
        this.acesso = 0;
    }

    /**
     *
     * @return quantidade da palavra que está presente na página.
     */
    public int getQuantDaPalavra() {
        return quantDaPalavra;
    }

    /**
     *
     * @return quantidade de vezes que a página foi acessada.
     */
    public int getAcesso() {
        return acesso;
    }

    /**
     *
     * @return nome da página.
     */
    public String getArq() {
        return arq;
    }

    /**
     * Atualiza a quantidade da palavra.
     */
    public void setQuantDaPalavra() {
        this.quantDaPalavra = quantDaPalavra + 1;
    }

    /**
     * Contador para a quantidade de acessos na página.
     */
    public void setAcessos() {
        this.acesso = this.acesso + 1;
    }

    /**
     * Compara dois objetos entre si.
     *
     * @param obj objeto a ser comparado.
     * @return "true" se os objetos forem iguais e "false" se forem diferentes.
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

    /**
     * Compara dois objetos do tipo "pagina" através de seu atributo
     * "quantDaPalavra".
     *
     * @param t
     * @return "1" se esse objeto for maior que o recebido por parâmetro, "-1"
     * se for menor e "0" se forem iguais.
     */
    @Override
    public int compareTo(Object t) {
        Pagina pagina = (Pagina) t;

        if (quantDaPalavra > pagina.getQuantDaPalavra()) {
            return 1;
        } else if (quantDaPalavra < pagina.getQuantDaPalavra()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Compara dois objetos do tipo "pagina" através de seu atributo "acesso".
     *
     * @param t objeto a ser comparado.
     * @param s identifica se o objeto do tipo "Pagina" sera comparado pelo seu
     * atributo "quantDaPalavra" ou "acesso".
     * @return "1" se esse objeto for maior que o recebido por parâmetro, "-1"
     * se for menor e "0" se forem iguais.
     */
    public int compareTo(Object t, int s) {
        Pagina pagina = (Pagina) t;

        if (acesso > pagina.getAcesso()) {
            return 1;
        } else if (acesso < pagina.getAcesso()) {
            return -1;
        } else {
            return 0;
        }
    }
}
