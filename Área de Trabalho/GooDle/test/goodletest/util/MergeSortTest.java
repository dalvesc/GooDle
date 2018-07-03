package goodletest.util;

import goodle.model.*;
import goodle.util.Ilist;
import goodle.util.LinkedList;
import goodle.util.MergeSort;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MergeSortTest {
    
    MergeSort merge;
    Palavra pal1, pal2, pal3, expectedWord;
    Pagina pag1, pag2, pag3, expectedPage;
    
    Ilist lista, listaOrdenada;
    
    @Before
    public void setUp() throws Exception {
        lista = new LinkedList();
        listaOrdenada = new LinkedList();
        
        pag1 = new Pagina("pag1");
        pag2 = new Pagina("pag2");
        pag3 = new Pagina("pag3");
        
        pal1 = new Palavra("pal1", pag1);
        pal2 = new Palavra("pal2", pag2);
        pal3 = new Palavra("pal3", pag3);
        
        lista.addLast(pal2);
        lista.addLast(pal1);
        lista.addLast(pal3);
    }
    
    @Test
    public void sort(){
        listaOrdenada = merge.sort(lista, true);
        
        expectedWord = (Palavra)listaOrdenada.get(0);
        assertEquals(expectedWord, pal1);
        
        
        
        
        
        
    }
}
