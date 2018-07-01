package goodle.util;

import goodle.model.*;

/**
 * Classe contendo o método de ordenação "Merge Sort".
 * Adaptação do código https://gist.github.com/louisbros/8514819
 * 
 * @author Daniel Alves, Gabriela Nunes e "louisbros".
 */
public class MergeSort {
    
    private boolean crescente;
    
    
    /**
     * O método é chamado pela classe "controller", que informa a lista que deseja ordenar 
     * juntamente com uma variável booleana, indicando em que ordem a lista deverá ser ordenada.
     * Este método faz a chamada de um outro método privado, que por vez chama também outro privado. 
     * 
     * @param lista coleção que será ordenada.
     * @param crescente se for "true", apresenta os resultados de maneira decrescente. Se for "false", de maneira crescente.
     * @return lista ordenada.
     */
    public Ilist sort(Ilist lista, boolean crescente) {
        
        this.crescente = crescente;
        
        Ilist aux = new LinkedList();        
        Iterator iterador = lista.iterator();
        
        while(iterador.hasNext()){
            aux.addLast(iterador.next());            
        }
        return mergeSort(0, lista.size() - 1, lista, aux);
    }

    private Ilist mergeSort(int low, int high, Ilist lista, Ilist aux) {

        Ilist retorno = null;
        
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(low, mid, lista, aux);
            mergeSort(mid + 1, high, lista, aux);
            retorno = merge(low, mid, high, lista, aux);
        }
        return retorno;
    }

    private Ilist merge(int low, int mid, int high, Ilist lista, Ilist aux) {

        int left = low;
        int right = mid + 1;
        

        for (int i = low; i <= high; i++) {
            aux.set(i, lista.get(i));
        }

        while (left <= mid && right <= high) {

            if(crescente){
                if(verifica(aux.get(left)) == 1){
                   Palavra auxLeft = (Palavra)aux.get(left);
                   Palavra auxRight = (Palavra)aux.get(right);
                   lista.set(low++, auxLeft.compareTo(auxRight) < 0 ? aux.get(left++) : aux.get(right++));
                }
                else{
                   Pagina auxLeft = (Pagina)aux.get(left);
                   Pagina auxRight = (Pagina)aux.get(right);
                   lista.set(low++, auxLeft.compareTo(auxRight) < 0 ? aux.get(left++) : aux.get(right++));
                }   
            }
            else{
                if(verifica(aux.get(left)) == 1){
                   Palavra auxLeft = (Palavra)aux.get(left);
                   Palavra auxRight = (Palavra)aux.get(right);
                   lista.set(low++, auxLeft.compareTo(auxRight) > 0 ? aux.get(left++) : aux.get(right++));
                }
                else{
                   Pagina auxLeft = (Pagina)aux.get(left);
                   Pagina auxRight = (Pagina)aux.get(right);
                   lista.set(low++, auxLeft.compareTo(auxRight) > 0 ? aux.get(left++) : aux.get(right++));
                }
            }
        }

        while (left <= mid) {
            lista.set(low++, aux.get(left++));
        }
        crescente = false;
        return lista;
    }
    
    private int verifica(Object compara){       
        
        if(compara instanceof Palavra){            
            return 1;
        }
        return 2;       
    }
    
    

   
    
    

    
    
   
}
