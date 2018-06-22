package goodle.controller;

import goodle.util.Arvore;

public class ArvoreAVL07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Arvore novo = new Arvore();
        novo.inserir("Casa");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Rua");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("José");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Rafa");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Mica");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Gineco");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Zebra");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("Chato");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("me");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("salva");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("vocalia");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir("abacaxi");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");

        System.out.println("REMOVER Mica");
        novo.remove("Mica");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        System.out.println("REMOVER Salva");
        novo.remove("Salva");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        System.out.println("REMOVER me");
        novo.remove("me");
        novo.percorrerPreOrdem(novo.raiz);
        novo.remove("josé");
        System.out.println("Pre ordem");
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("Raiz");
        System.out.println(novo.raiz.chave);
        System.out.println("Direita");
        System.out.println(novo.raiz.dir.chave);
        System.out.println("Esquerda");
        System.out.println(novo.raiz.esq.chave);
    }

}
