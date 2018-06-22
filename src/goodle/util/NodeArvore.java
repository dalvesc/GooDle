package goodle.util;

/**
 * Adaptado do código https://github.com/douglasrz/ArvoreAVL
 *
 * @author Douglas
 */
public class NodeArvore {

    public Object chave;
    public int bal;
    public NodeArvore esq;
    public NodeArvore dir;

    public NodeArvore(String chave) {
        this.chave = chave;
        this.bal = 0;
        this.dir = null;
        this.esq = null;
    }

    public NodeArvore() {
    }

}
