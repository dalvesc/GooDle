package goodle.util;

import goodle.model.Pagina;

/**
 * Classe contendo o método de ordenação "Merge Sort". Adaptação do código
 * https://gist.github.com/louisbros/8514819
 *
 * @author Daniel Alves, Gabriela Nunes e "louisbros".
 */
public class MergeSort {

    /**
     * O método é chamado pela classe "controller", que informa a lista que
     * deseja ordenar. A ordenação é feita utilizando o atributo
     * "quantDaPalavra" dos objetos como parâmetro.
     *
     * @param paginas
     * @return lista ordenada de forma crescente
     */
    public Ilist sort(Ilist paginas) {

        Ilist aux = new LinkedList();
        Iterator iterador = paginas.iterator();

        while (iterador.hasNext()) {
            aux.addLast(iterador.next());
        }
        return mergeSort(0, paginas.size() - 1, paginas, aux);
    }

    private Ilist mergeSort(int low, int high, Ilist paginas, Ilist aux) {

        Ilist retorno = null;

        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(low, mid, paginas, aux);
            mergeSort(mid + 1, high, paginas, aux);
            retorno = merge(low, mid, high, paginas, aux);
        }
        return retorno;
    }

    private Ilist merge(int low, int mid, int high, Ilist paginas, Ilist aux) {

        int left = low;
        int right = mid + 1;

        for (int i = low; i <= high; i++) {
            aux.set(i, paginas.get(i));
        }

        while (left <= mid && right <= high) {

            Pagina auxLeft = (Pagina) aux.get(left);
            Pagina auxRight = (Pagina) aux.get(right);

            paginas.set(low++, auxLeft.getQuantDaPalavra() < auxRight.getQuantDaPalavra() ? aux.get(left++) : aux.get(right++));
        }

        while (left <= mid) {
            paginas.set(low++, aux.get(left++));
        }
        return paginas;
    }

}