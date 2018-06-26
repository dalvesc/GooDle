package goodle.util;

import goodle.model.Pagina;
import goodle.model.Palavra;

public class Arvore implements AVL {

    public boolean h;
    public NodeArvore raiz;

    public Arvore() {
        this.raiz = null;
        this.h = false;
    }

    @Override
    public void inserir(Object palavra, Object pagina) {//MÉTODO PUBLIC QUE VOU CHAMAR NO MAIN, PARA CHAMAR O PRIVADO ABAIXO
        this.raiz = insAVL(palavra, pagina, this.raiz);
    }

    private NodeArvore insAVL(Object palavra, Object pagina, NodeArvore pt) {

        Palavra chave = (Palavra) palavra;
        Pagina pag = (Pagina) pagina;   
        
        if (pt == null) {
            h = true;
            return new NodeArvore(chave);
        } else {
            
            Palavra raiz = (Palavra) pt.getChave();            
            

            if (chave.getPalavra().compareTo(raiz.getPalavra()) == 0) { //fiz isso para caso as palavras sejam iguais
                Iterator iterator = chave.getlPagina().iterator(); //pego a lista de pagina da palavra
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

            if (chave.getPalavra().compareTo(raiz.getPalavra()) < 0) {//PERCORRO PARA O RAMO ESQUERDO
                pt.setEsq(insAVL(chave, pagina, pt.getEsq())); 
                
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
                pt.setDir(insAVL(chave, pagina, pt.getDir())); //PERCORRO PARA A DIREITA
                
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
        
        
        if (ptu.getBal() == -1) {//ROTAÇÃO SIMPLES PARA A DIREITA

            System.out.println("ROTACAO SIMPLES DIREITA COM " + palavra.getPalavra() + "\n ***ANTES***");
            percorrerPreOrdem(this.raiz);

            pt.setEsq(ptu.getDir());
            ptu.setDir(pt);
            pt.setBal(0);
            pt = ptu;
        } else {//ROTAÇÃO DUPLA PARA A DIREITA

            System.out.println("ROTACAO DUPLA A DIREITA \n ***ANTES***");
            percorrerPreOrdem(this.raiz);

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
        System.out.println("******************");
        return pt;
    }

    public NodeArvore caso2(NodeArvore pt) {
        NodeArvore ptu = pt.getDir();
        Palavra palavra = (Palavra) pt.getChave();
        
        if (ptu.getBal() == 1) {//ROTAÇÃO SIMPLES PARA A ESQUERDA

            System.out.println("ROTACAO SIMPLES A ESQUERDA COM " + palavra.getPalavra() + "\n ***ANTES");
            percorrerPreOrdem(this.raiz);

            pt.setDir(ptu.getEsq());
            ptu.setEsq(pt);
            pt.setBal(0);
            pt = ptu;
        } else {//DUPLA ROTAÇÃO PARA A ESQUERDA
            System.out.println("ROTACAO DUPLA A ESQUERDA \n ***ANTES***");
            percorrerPreOrdem(this.raiz);
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
        System.out.println("******************");
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
            System.out.println("ELEMENTO NÃO ENCONTRADO");//QUANDO PERCORRE TODA A ARVORE E NAO ENCONTRO O ELEMENTO
            h = false;//PARA REMOVER ELEMENTOS Q NAO EXISTE, POIS TAVA DANDO ERRO
            return pt;
        } else {
            if (chave.compareTo(chaveRaiz.getPalavra()) < 0) {//PERCORRO O PARA ESQUERDA
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
            if (chave.compareTo(chaveRaiz.getPalavra()) > 0) {//PERCORRO PARA A DIREITA    
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
            if (chave.compareTo(chaveRaiz.getPalavra()) == 0) {//APOS PERCORRER ATÉ QUE A CHAVE NEM SEJA MAIOR OU MENOR QUE OS NO,EU VERIFICO SE É IGUAL
                if ((pt.getDir() == null) && (pt.getEsq() == null)) {
                    return null;//NO SEM FILHOS ENTÃO BASTA APAGAR
                } else {//SENÃO EU PEGO O MAIOR E COLOCO NO LUGAR NO REMOVIDO
                    if (pt.getBal() <= 0) {
                        //MÉTODO ALTERADO PELA MINHA LÓGICA, POIS ESTAVA DANDO ERRO
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
                            //MÉTODO ALTERADO PELA MINHA LÓGICA, POIS ESTAVA DANDO ERRO
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

    //desnecessario
    public void percorrerInOrdem(NodeArvore pt) {
        Palavra palavra = (Palavra) pt.getChave();
        
        
        if (pt == null) {
            return;
        }
        if (pt.getEsq() != null) {//primeiro percorro a subarvore esquerda, OU SEJA, visitando os menores
            percorrerInOrdem(pt.getEsq());
        }//QUANDO CHEGO NO ULTIMO A ESQ É QUE IMPRIMO ELE E VOU VOLTANDO POIS É RECURSIVO
        System.out.println("Chave: " + palavra.getPalavra());
        if (pt.getDir() != null) {
            percorrerInOrdem(pt.getDir());//por fim a direita, após a raiz
        }
    }

    //desnecessario
    public void percorrerPreOrdem(NodeArvore pt) {
        Palavra palavra = (Palavra) pt.getChave();
        
        if (pt == null) {
            return;
        }
        System.out.println("Chave: " + palavra.getPalavra());//PRIMEIRO VISITO A RAIZ
        if (pt.getEsq() != null) {
            percorrerPreOrdem(pt.getEsq());//CHAMO ESTE MÉTODO AGORA COM O ESQ COMO RAIZ E JÁ VOU IMPRIMINDO
        }
        if (pt.getDir() != null) {
            percorrerPreOrdem(pt.getDir());//CHAMO ESTE MÉTODO AGORA COM O DIR COMO RAIZ
        }
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
        NodeArvore temp = buscAVL(palavra, this.raiz);
        return temp.getChave();
    }

    private NodeArvore buscAVL(Object palavra, NodeArvore pt) {
        Palavra chave = (Palavra) palavra;
        Palavra chaveRaiz = (Palavra) pt.getChave();

        if (pt == null) {
            return null;
        } else {
            if (chave.getPalavra().compareTo(chaveRaiz.getPalavra()) < 0) {
                pt.setEsq(buscAVL(chave, pt.getEsq()));
            } else if (chave.compareTo(chaveRaiz.getPalavra()) > 0) {
                pt.setDir(buscAVL(chave, pt.getDir()));
            } else {
                return pt;
            }
        }
        return null;
    }
}
