package goodle.util;

/**
 * Adaptado do código https://github.com/douglasrz/ArvoreAVL
 *
 * @author Douglas
 */
public class NodeArvore {

    private Object chave;
    private int bal;
    private NodeArvore esq;
    private NodeArvore dir;

    public NodeArvore(Object chave) {
        this.chave = chave;
        this.bal = 0;
        this.dir = null;
        this.esq = null;
    }

    public Object getChave() {
        return chave;
    }

    public int getBal() {
        return bal;
    }

    public NodeArvore getEsq() {
        return esq;
    }

    public NodeArvore getDir() {
        return dir;
    }

    public void setChave(Object chave) {
        this.chave = chave;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    public void setEsq(NodeArvore esq) {
        this.esq = esq;
    }

    public void setDir(NodeArvore dir) {
        this.dir = dir;
    }

}
