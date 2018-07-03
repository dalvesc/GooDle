package goodle.util;

import goodle.model.Pagina;
import goodle.model.Palavra;

public class Arvore implements AVL {

    public boolean h;
    public NodeArvore raiz;
    private Object encontrei;

    public Arvore() {
        this.raiz = null;
        this.h = false;
        encontrei = null;
    }

    @Override
    public void inserir(Object palavra) {
        this.raiz = insAVL(palavra, this.raiz);
    }

    private NodeArvore insAVL(Object palavra, NodeArvore pt) {

        Palavra chave = (Palavra) palavra;

        if (pt == null) {
            h = true;
            return new NodeArvore(chave);
        } else {

            Palavra raiz = (Palavra) pt.getChave();

            if (chave.compareTo(raiz.getPalavra()) == 0) {
                raiz.quantidade();
                Iterator iterator = raiz.getlPagina().iterator();
                while (iterator.hasNext()) {
                    Pagina pagina = (Pagina) iterator.next();
                    if (chave.getPagina().equals(pagina)) {
                        pagina.setQuantDaPalavra();
                        return pt;
                    }
                }
                raiz.addPagina(chave.getPagina());
                return pt;
            }

            if (chave.compareTo(raiz.getPalavra()) < 0) {
                pt.setEsq(insAVL(chave, pt.getEsq()));

                if (h) {
                    switch (pt.getBal()) {
                        case 1:
                            pt.setBal(0);
                            h = false;
                            break;
                        case 0:
                            pt.setBal(-1);
                            break;
                        case -1:
                            h = false;
                            return caso1(pt);
                        default:
                            break;
                    }
                }
            } else {
                pt.setDir(insAVL(chave, pt.getDir()));

                if (h) {
                    switch (pt.getBal()) {
                        case -1:
                            pt.setBal(0);
                            h = false;
                            break;
                        case 0:
                            pt.setBal(1);
                            break;
                        case 1:
                            h = false;
                            return caso2(pt);
                        default:
                            break;
                    }
                }
            }
        }
        return pt;
    }

    public NodeArvore caso1(NodeArvore pt) {
        NodeArvore ptu = pt.getEsq();
        Palavra palavra = (Palavra) pt.getChave();

        if (ptu.getBal() == -1) {

            pt.setEsq(ptu.getDir());
            ptu.setDir(pt);
            pt.setBal(0);
            pt = ptu;
        } else {

            NodeArvore ptv = ptu.getDir();
            ptu.setDir(ptv.getEsq());
            ptv.setEsq(ptu);
            pt.setEsq(ptv.getDir());
            ptv.setDir(pt);
            if (ptv.getBal() == -1) {
                pt.setBal(1);
            } else {
                pt.setBal(0);
            }
            if (ptv.getBal() == 1) {
                ptu.setBal(-1);
            } else {
                ptu.setBal(0);
            }
            pt = ptv;
        }
        pt.setBal(0);
        return pt;
    }

    public NodeArvore caso2(NodeArvore pt) {
        NodeArvore ptu = pt.getDir();
        Palavra palavra = (Palavra) pt.getChave();

        if (ptu.getBal() == 1) {

            pt.setDir(ptu.getEsq());
            ptu.setEsq(pt);
            pt.setBal(0);
            pt = ptu;
        } else {
            NodeArvore ptv = ptu.getEsq();
            ptu.setEsq(ptv.getDir());
            ptv.setDir(ptu);
            pt.setDir(ptv.getEsq());
            ptv.setEsq(pt);
            if (ptv.getBal() == 1) {
                pt.setBal(-1);
            } else {
                pt.setBal(0);
            }
            if (ptv.getBal() == -1) {
                ptu.setBal(1);
            } else {
                ptu.setBal(0);
            }
            pt = ptv;
        }
        pt.setBal(0);
        return pt;
    }

    @Override
    public void remove(Object palavra) {
        this.raiz = remover(palavra, this.raiz);
    }

    private NodeArvore remover(Object palavra, NodeArvore pt) {
        Palavra chave = (Palavra) palavra;
        Palavra chaveRaiz = (Palavra) pt.getChave();

        if (pt == null) {
            h = false;
            return pt;
        } else {
            if (chave.compareTo(chaveRaiz.getPalavra()) < 0) {
                pt.setEsq(remover(chave, pt.getEsq()));
                if (h) {
                    switch (pt.getBal()) {
                        case 1:
                            return caso2(pt);
                        case 0:
                            pt.setBal(1);
                            h = false;
                            break;
                        case -1:
                            pt.setBal(0);
                            break;
                        default:
                            break;
                    }
                }
            }
            if (chave.compareTo(chaveRaiz.getPalavra()) > 0) {
                pt.setDir(remover(chave, pt.getDir()));
                if (h) {
                    switch (pt.getBal()) {
                        case -1:
                            return caso1(pt);
                        case 0:
                            pt.setBal(-1);
                            h = false;
                            break;
                        case 1:
                            pt.setBal(0);
                            break;
                        default:
                            break;
                    }
                }
            }
            if (chave.compareTo(chaveRaiz.getPalavra()) == 0) {
                if ((pt.getDir() == null) && (pt.getEsq() == null)) {
                    return null;
                } else {
                    if (pt.getBal() <= 0) {
                        NodeArvore aux = max(pt.getEsq());
                        pt.setChave(aux.getChave());
                        aux.setChave(chave);
                        pt.setEsq(remover(chave, pt.getEsq()));
                        if (h) {
                            switch (pt.getBal()) {
                                case 0:
                                    pt.setBal(1);
                                    h = false;
                                    break;
                                case -1:
                                    pt.setBal(0);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        if (pt.getBal() == 1) {
                            NodeArvore aux = min(pt.getDir());
                            pt.setChave(aux.getChave());
                            aux.setChave(chave);
                            pt.setDir(remover(chave, pt.getDir()));
                            if (h) {
                                pt.setBal(0);
                            }
                        }
                    }

                }
            }
        }
        return pt;
    }

    private NodeArvore min(NodeArvore pt) {
        if (pt.getEsq() == null) {
            return pt;
        } else {
            return min(pt.getEsq());
        }
    }

    private NodeArvore max(NodeArvore pt) {
        if (pt.getDir() == null) {
            return pt;
        } else {
            return max(pt.getDir());
        }
    }

    @Override
    public Object busca(Object palavra) {
        buscAVL(palavra, this.raiz);
        return encontrei;
    }

    private NodeArvore buscAVL(Object palavra, NodeArvore pt) {
        Palavra chave = (Palavra) palavra;
        Palavra chaveRaiz = (Palavra) pt.getChave();

        if (pt == null) {
            return null;
        } else {
            if (chave.compareTo(chaveRaiz.getPalavra()) < 0) {
                buscAVL(chave, pt.getEsq());
            } else if (chave.compareTo(chaveRaiz.getPalavra()) > 0) {
                buscAVL(chave, pt.getDir());
            } else if (chave.compareTo(chaveRaiz.getPalavra()) == 0) {
                encontrei = chaveRaiz;
                return pt;
            }
        }
        return null;
    }
}
