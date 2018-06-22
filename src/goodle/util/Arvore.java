package goodle.util;

import goodle.model.Pagina;
import goodle.model.Palavra;

/**
 * Adaptado do código https://github.com/douglasrz/ArvoreAVL
 *
 * @author Douglas
 */
public class Arvore {

    public boolean h;
    public NodeArvore raiz;

    public Arvore() {
        this.raiz = null;
        this.h = false;
    }

    public void inserir(Object palavra, Object pagina) {//MÉTODO PUBLIC QUE VOU CHAMAR NO MAIN, PARA CHAMAR O PRIVADO ABAIXO
        this.raiz = insAVL(palavra, pagina, this.raiz);
    }

    private NodeArvore insAVL(Object palavra, Object pagina, NodeArvore pt) {

        Palavra chave = (Palavra) palavra;
        Pagina pag = (Pagina) pagina;
        if (pt == null) {
            h = true;
            return new NodeArvore(chave.getPalavra());
        } else {

            if (chave.compareTo(pt.chave) == 0) { //fiz isso para caso as palavras sejam iguais
                Iterator iterator = chave.getPagina().iterator(); //pego a lista de pagina da palavra
                while (iterator.hasNext()) {
                    Pagina temp = (Pagina) iterator.next();
                    if (pag.getArq().equals(temp.getArq())) {//comparo se a pagina que está sendo lida é a mesma da que já foi cadastrada na lista
                        temp.Quantidade();//caso seja igual adiciona +1 na quantidade daquela palavra na pagina
                    } else {
                        chave.Pagina(pag);//caso não seja igual adiciona a pagina na lista
                    }
                }
                return pt;
            }

            if (chave.compareTo(pt.chave) < 0) {//PERCORRO PARA O RAMO ESQUERDO
                pt.esq = insAVL(chave, pagina, pt.esq);
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
                pt.dir = insAVL(chave, pagina, pt.dir);//PERCORRO PARA A DIREITA
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

    public void remove(Object palavra) {
        this.raiz = remover(palavra, this.raiz);
    }

    private NodeArvore remover(Object palavra, NodeArvore pt) {
        Palavra chave = (Palavra) palavra;
        if (pt == null) {
            System.out.println("ELEMENTO NÃO ENCONTRADO");//QUANDO PERCORRE TODA A ARVORE E NAO ENCONTRO O ELEMENTO
            h = false;//PARA REMOVER ELEMENTOS Q NAO EXISTE, POIS TAVA DANDO ERRO
            return pt;
        } else {
            if (chave.compareTo(pt.chave) < 0) {//PERCORRO O PARA ESQUERDA
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
            if (chave.compareTo(pt.chave) > 0) {//PERCORRO PARA A DIREITA    
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
            if (chave.compareTo(pt.chave) == 0) {//APOS PERCORRER ATÉ QUE A CHAVE NEM SEJA MAIOR OU MENOR QUE OS NO,EU VERIFICO SE É IGUAL
                if ((pt.dir == null) && (pt.esq == null)) {
                    return null;//NO SEM FILHOS ENTÃO BASTA APAGAR
                } else {//SENÃO EU PEGO O MAIOR E COLOCO NO LUGAR NO REMOVIDO
                    if (pt.bal <= 0) {
                        //MÉTODO ALTERADO PELA MINHA LÓGICA, POIS ESTAVA DANDO ERRO
                        NodeArvore aux = max(pt.esq);
                        pt.chave = aux.chave;
                        aux.chave = chave.getPalavra();
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
                            aux.chave = chave.getPalavra();
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

    //desnecessario
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

    //desnecessario
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

    //desnecessario
    //coloquei esse metódo para rodar a árvore e caso ele encontre uma palavra
    //repetida ele retorna true para poder saber se uma palavra já se encontra na árvore
    public boolean buscaPalavraRepetida(NodeArvore pt, Object palavra) {
        Palavra chave = (Palavra) palavra;
        if (pt == null || pt.chave.equals(chave.getPalavra())) {
            return true;
        }
        if (pt.esq != null) {
            buscaPalavraRepetida(pt.esq, palavra);
        }
        if (pt.dir != null) {//PERCORRO TUDO, POR ULTIMO PERCORRO A SUBARVORE DIREITA A PARTIR DO ULTIMO NÓ DA DIREITA É 
            buscaPalavraRepetida(pt.dir, palavra);//QUE VENHO IMPRIMINDO E ASSIM VOLTANDO RECURSIVAMENTE
        }
        return false;
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

    public Object busca(Object palavra) {
        NodeArvore temp = buscAVL(palavra, this.raiz);
        return temp.chave;
    }

    private NodeArvore buscAVL(Object palavra, NodeArvore pt) {
        Palavra chave = (Palavra) palavra;

        if (pt == null) {
            return null;
        } else {
            if (chave.compareTo(pt.chave) < 0) {
                pt.esq = buscAVL(chave, pt.esq);
            } else if (chave.compareTo(pt.chave) > 0) {
                pt.dir = buscAVL(chave, pt.dir);
            } else {
                return pt;
            }
        }
        return null;
    }
}
