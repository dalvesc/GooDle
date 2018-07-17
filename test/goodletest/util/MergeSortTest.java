package goodletest.util;

import goodle.model.*;
import goodle.util.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {

    MergeSort merge;
    Ilist lista, lista2;
    Pagina pagina1, pagina2, pagina3, pagina4;

    @Before
    public void setUp() throws Exception {
        merge = new MergeSort();
        lista = new LinkedList();
        lista2 = new LinkedList();
        pagina1 = new Pagina("Arquivo1");
        pagina2 = new Pagina("Arquivo2");
        pagina3 = new Pagina("Arquivo3");
        pagina4 = new Pagina("Arquivo4");
    }

    @Test
    public void testSort() {
        pagina1.setQuantDaPalavra();
        pagina1.setQuantDaPalavra();
        pagina2.setQuantDaPalavra();
        pagina2.setQuantDaPalavra();
        pagina2.setQuantDaPalavra();
        pagina2.setQuantDaPalavra();
        pagina3.setQuantDaPalavra();
        pagina4.setQuantDaPalavra();
        pagina4.setQuantDaPalavra();
        pagina4.setQuantDaPalavra();

        lista.addLast(pagina1);
        lista.addLast(pagina2);
        lista.addLast(pagina3);
        lista.addFirst(pagina4);
        assertFalse(lista.isEmpty());

        lista2 = merge.sort(lista, true, 0);
        assertFalse(lista2.isEmpty());
        Iterator it = lista.iterator();
        
        assertTrue(it.hasNext());
        assertSame(pagina3, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina1, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina4, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina2, it.next());
        assertFalse(it.hasNext());

    }
}
