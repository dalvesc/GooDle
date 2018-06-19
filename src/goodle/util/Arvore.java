package goodle.util;

/**
  *Adaptado do código https://github.com/douglasrz/ArvoreAVL
 * @author Douglas
 */
public class Arvore {

    public boolean h;
    public NodeArvore raiz;

    public Arvore() {
        this.raiz = null;
        this.h = false;
    }

    public void inserir(String chave) {//MÉTODO PUBLIC QUE VOU CHAMAR NO MAIN, PARA CHAMAR O PRIVADO ABAIXO
        this.raiz = insAVL(chave, this.raiz);
    }

    private NodeArvore insAVL(String chave, NodeArvore pt) {
        if (pt == null) {
            h = true;
            return new NodeArvore(chave);
        } else {
            if (chave.equals(pt.chave)) {
                return pt;
            }
            if (chave.compareToIgnoreCase(pt.chave) < 0) {//PERCORRO PARA O RAMO ESQUERDO
                pt.esq = insAVL(chave, pt.esq);
                if (h) {
                    switch (pt.bal) {
                        case 1:
                            pt.bal = 0;
                            h = false;
                            break;
                        case 0:
                            pt.bal = -1;
                            break;
                        case -1:
                            h = false;
                            return caso1(pt);
                        default:
                            break;
                    }
                }
            } else {
                pt.dir = insAVL(chave, pt.dir);//PERCORRO PARA A DIREITA
                if (h) {
                    switch (pt.bal) {
                        case -1:
                            pt.bal = 0;
                            h = false;
                            break;
                        case 0:
                            pt.bal = 1;
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
        NodeArvore ptu = pt.esq;
        if (ptu.bal == -1) {//ROTAÇÃO SIMPLES PARA A DIREITA

            System.out.println("ROTACAO SIMPLES DIREITA COM " + pt.chave + "\n ***ANTES***");
            percorrerPreOrdem(this.raiz);

            pt.esq = ptu.dir;
            ptu.dir = pt;
            pt.bal = 0;
            pt = ptu;
        } else {//ROTAÇÃO DUPLA PARA A DIREITA

            System.out.println("ROTACAO DUPLA A DIREITA \n ***ANTES***");
            percorrerPreOrdem(this.raiz);

            NodeArvore ptv = ptu.dir;
            ptu.dir = ptv.esq;
            ptv.esq = ptu;
            pt.esq = ptv.dir;
            ptv.dir = pt;
            if (ptv.bal == -1) {
                pt.bal = 1;
            } else {
                pt.bal = 0;
            }
            if (ptv.bal == 1) {
                ptu.bal = -1;
            } else {
                ptu.bal = 0;
            }
            pt = ptv;
        }
        pt.bal = 0;
        System.out.println("******************");
        return pt;
    }

    public NodeArvore caso2(NodeArvore pt) {
        NodeArvore ptu = pt.dir;
        if (ptu.bal == 1) {//ROTAÇÃO SIMPLES PARA A ESQUERDA

            System.out.println("ROTACAO SIMPLES A ESQUERDA COM " + pt.chave + "\n ***ANTES");
            percorrerPreOrdem(this.raiz);

            pt.dir = ptu.esq;
            ptu.esq = pt;
            pt.bal = 0;
            pt = ptu;
        } else {//DUPLA ROTAÇÃO PARA A ESQUERDA
            System.out.println("ROTACAO DUPLA A ESQUERDA \n ***ANTES***");
            percorrerPreOrdem(this.raiz);
            NodeArvore ptv = ptu.esq;
            ptu.esq = ptv.dir;
            ptv.dir = ptu;
            pt.dir = ptv.esq;
            ptv.esq = pt;
            if (ptv.bal == 1) {
                pt.bal = -1;
            } else {
                pt.bal = 0;
            }
            if (ptv.bal == -1) {
                ptu.bal = 1;
            } else {
                ptu.bal = 0;
            }
            pt = ptv;
        }
        pt.bal = 0;
        System.out.println("******************");
        return pt;
    }

    public void remove(String chave) {
        this.raiz = remover(chave, this.raiz);
    }

    private NodeArvore remover(String chave, NodeArvore pt) {
        if (pt == null) {
            System.out.println("ELEMENTO NÃO ENCONTRADO");//QUANDO PERCORRE TODA A ARVORE E NAO ENCONTRO O ELEMENTO
            h = false;//PARA REMOVER ELEMENTOS Q NAO EXISTE, POIS TAVA DANDO ERRO
            return pt;
        } else {
            if (chave.compareToIgnoreCase(pt.chave) < 0) {//PERCORRO O PARA ESQUERDA
                pt.esq = remover(chave, pt.esq);
                if (h) {
                    switch (pt.bal) {
                        case 1:
                            return caso2(pt);
                        case 0:
                            pt.bal = 1;
                            h = false;
                            break;
                        case -1:
                            pt.bal = 0;
                            break;
                        default:
                            break;
                    }
                }
            }
            if (chave.compareToIgnoreCase(pt.chave) > 0) {//PERCORRO PARA A DIREITA    
                pt.dir = remover(chave, pt.dir);
                if (h) {
                    switch (pt.bal) {
                        case -1:
                            return caso1(pt);
                        case 0:
                            pt.bal = -1;
                            h = false;
                            break;
                        case 1:
                            pt.bal = 0;
                            break;
                        default:
                            break;
                    }
                }
            }
            if (chave.compareToIgnoreCase(pt.chave) == 0) {//APOS PERCORRER ATÉ QUE A CHAVE NEM SEJA MAIOR OU MENOR QUE OS NO,EU VERIFICO SE É IGUAL
                if ((pt.dir == null) && (pt.esq == null)) {
                    return null;//NO SEM FILHOS ENTÃO BASTA APAGAR
                } else {//SENÃO EU PEGO O MAIOR E COLOCO NO LUGAR NO REMOVIDO
                    if (pt.bal <= 0) {
                        //MÉTODO ALTERADO PELA MINHA LÓGICA, POIS ESTAVA DANDO ERRO
                        NodeArvore aux = max(pt.esq);
                        pt.chave = aux.chave;
                        aux.chave = chave;
                        pt.esq = remover(chave, pt.esq);
                        if (h) {
                            switch (pt.bal) {
                                case 0:
                                    pt.bal = 1;
                                    h = false;
                                    break;
                                case -1:
                                    pt.bal = 0;
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        if (pt.bal == 1) {
                            //MÉTODO ALTERADO PELA MINHA LÓGICA, POIS ESTAVA DANDO ERRO
                            NodeArvore aux = min(pt.dir);
                            pt.chave = aux.chave;
                            aux.chave = chave;
                            pt.dir = remover(chave, pt.dir);
                            if (h) {
                                pt.bal = 0;
                            }
                        }
                    }

                }
            }
        }
        return pt;
    }

    public void percorrerInOrdem(NodeArvore pt) {
        if (pt == null) {
            return;
        }
        if (pt.esq != null) {//primeiro percorro a subarvore esquerda, OU SEJA, visitando os menores
            percorrerInOrdem(pt.esq);
        }//QUANDO CHEGO NO ULTIMO A ESQ É QUE IMPRIMO ELE E VOU VOLTANDO POIS É RECURSIVO
        System.out.println("Chave: " + pt.chave);
        if (pt.dir != null) {
            percorrerInOrdem(pt.dir);//por fim a direita, após a raiz
        }
    }

    public void percorrerPreOrdem(NodeArvore pt) {
        if (pt == null) {
            return;
        }
        System.out.println("Chave: " + pt.chave);//PRIMEIRO VISITO A RAIZ
        if (pt.esq != null) {
            percorrerPreOrdem(pt.esq);//CHAMO ESTE MÉTODO AGORA COM O ESQ COMO RAIZ E JÁ VOU IMPRIMINDO
        }
        if (pt.dir != null) {
            percorrerPreOrdem(pt.dir);//CHAMO ESTE MÉTODO AGORA COM O DIR COMO RAIZ
        }
    }

    public void percorrerPosOrdem(NodeArvore pt) {
        if (pt == null) {
            return;
        }
        if (pt.esq != null) {
            percorrerPosOrdem(pt.esq);
        }
        if (pt.dir != null) {//PERCORRO TUDO, POR ULTIMO PERCORRO A SUBARVORE DIREITA A PARTIR DO ULTIMO NÓ DA DIREITA É 
            percorrerPosOrdem(pt.dir);//QUE VENHO IMPRIMINDO E ASSIM VOLTANDO RECURSIVAMENTE
        }
        System.out.println("Chave: " + pt.chave);
    }

    private NodeArvore min(NodeArvore pt) {
        if (pt.esq == null) {
            return pt;
        } else {
            return min(pt.esq);
        }
    }

    private NodeArvore max(NodeArvore pt) {
        if (pt.dir == null) {
            return pt;
        } else {
            return max(pt.dir);
        }
    }
}
