package goodle.util;

import goodle.model.*;

/**
 * Classe contendo o método de ordenação "Merge Sort". Adaptação do código
 * https://gist.github.com/louisbros/8514819
 *
 * @author Daniel Alves, Gabriela Nunes e "louisbros".
 */
public class MergeSort {

    private boolean crescente;
    private int dif = 0;
    private Ilist retorno;

    /**
     * O método é chamado pela classe "controller", que informa a lista que
     * deseja ordenar juntamente com uma variável booleana, indicando em que
     * ordem a lista deverá ser ordenada. Este método faz a chamada de um outro
     * método privado, que por vez chama também outro privado.
     *
     * @param lista coleção que será ordenada.
     * @param crescente se for "true", apresenta os resultados de maneira
     * decrescente. Se for "false", de maneira crescente.
     * @return lista ordenada.
     */
    public Ilist sort(Ilist lista, boolean crescente) {

        if (lista.size() == 1) {
            return lista;
        }

        this.crescente = crescente;

        Ilist aux = new LinkedList();
        Iterator iterador = lista.iterator();

        while (iterador.hasNext()) {
            aux.addLast(iterador.next());
        }

        mergeSort(0, lista.size() - 1, lista, aux);
        return retorno;
    }

    /**
     * O método é chamado pela classe "controller", que informa a lista que
     * deseja ordenar juntamente com uma variável booleana, indicando em que
     * ordem a lista deverá ser ordenada. Este método faz a chamada de um outro
     * método privado, que por vez chama também outro privado.
     *
     * @param lista coleção que será ordenada.
     * @param crescente se for "true", apresenta os resultados de maneira
     * decrescente. Se for "false", de maneira crescente.
     * @param dif responsável por identificar se as páginas serão ordenadas
     * pelas ocorrências da palavra ou pela quantidade de acessos.
     * @return lista ordenada.
     */
    public Ilist sort(Ilist lista, boolean crescente, int dif) {

        if (lista.size() == 1) {
            return lista;
        }

        this.crescente = crescente;
        this.dif = dif;

        Ilist aux = new LinkedList();
        Iterator iterador = lista.iterator();

        while (iterador.hasNext()) {
            aux.addLast(iterador.next());
        }

        mergeSort(0, lista.size() - 1, lista, aux);
        return retorno;
    }

    private void mergeSort(int low, int high, Ilist lista, Ilist aux) {

        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(low, mid, lista, aux);
            mergeSort(mid + 1, high, lista, aux);
            merge(low, mid, high, lista, aux);
        }
    }

    private void merge(int low, int mid, int high, Ilist lista, Ilist aux) {

        int left = low;
        int right = mid + 1;

        for (int i = low; i <= high; i++) {
            aux.set(i, lista.get(i));
        }

        while (left <= mid && right <= high) {

            if (crescente) {
                if (verifica(aux.get(left)) == 1) {
                    Palavra auxLeft = (Palavra) aux.get(left);
                    Palavra auxRight = (Palavra) aux.get(right);
                    lista.set(low++, auxLeft.compareTo(auxRight, "s") < 0 ? aux.get(left++) : aux.get(right++));
                } else if (verifica(aux.get(left)) == 2) {

                    if (dif == 1) {
                        Pagina auxLeft = (Pagina) aux.get(left);
                        Pagina auxRight = (Pagina) aux.get(right);
                        lista.set(low++, auxLeft.compareTo(auxRight, 1) < 0 ? aux.get(left++) : aux.get(right++));
                    } else {
                        Pagina auxLeft = (Pagina) aux.get(left);
                        Pagina auxRight = (Pagina) aux.get(right);
                        lista.set(low++, auxLeft.compareTo(auxRight) < 0 ? aux.get(left++) : aux.get(right++));
                    }
                }
            } else {
                if (verifica(aux.get(left)) == 1) {
                    Palavra auxLeft = (Palavra) aux.get(left);
                    Palavra auxRight = (Palavra) aux.get(right);
                    lista.set(low++, auxLeft.compareTo(auxRight, "s") > 0 ? aux.get(left++) : aux.get(right++));
                } else if (verifica(aux.get(left)) == 2) {
                    Pagina auxLeft = (Pagina) aux.get(left);
                    Pagina auxRight = (Pagina) aux.get(right);
                    lista.set(low++, auxLeft.compareTo(auxRight) > 0 ? aux.get(left++) : aux.get(right++));
                }
            }
        }

        while (left <= mid) {
            lista.set(low++, aux.get(left++));
        }
      
        dif = 0;
        retorno = lista;
    }

    private int verifica(Object compara) {

        if (compara == null) {
            return 3;
        }
        if (compara instanceof Palavra) {
            return 1;
        }
        return 2;
    }
}
