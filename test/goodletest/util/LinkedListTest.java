package goodletest.util;

import goodle.model.*;
import goodle.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    LinkedList lista;
    Object pagina1, pagina2, pagina3;

    @Before
    public void setUp() throws Exception {
        lista = new LinkedList();
        pagina1 = new Pagina("Arquivo1");
        pagina2 = new Pagina("Arquivo2");
        pagina3 = new Pagina("Arquivo3");
    }

    @Test
    public void testEstaVazia() {
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testTamanho() {
        assertEquals(0, lista.size());

        lista.addFirst(pagina1);
        assertEquals(1, lista.size());

        lista.addFirst(pagina2);
        lista.addFirst(pagina3);
        assertEquals(3, lista.size());

        lista.removeLast();
        assertEquals(2, lista.size());

        lista.removeFirst();
        lista.removeLast();
        assertEquals(0, lista.size());
    }

    @Test
    public void testAdicionaInicioRemoveInicio() {
        lista.addFirst(pagina1);
        lista.addFirst(pagina2);
        lista.addFirst(pagina3);
        assertFalse(lista.isEmpty());

        assertSame(pagina3, lista.removeFirst());
        assertFalse(lista.isEmpty());

        assertSame(pagina2, lista.removeFirst());
        assertFalse(lista.isEmpty());

        assertSame(pagina1, lista.removeFirst());
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testAdicionaInicioRemoveFinal() {
        lista.addFirst(pagina1);
        lista.addFirst(pagina2);
        lista.addFirst(pagina3);
        assertFalse(lista.isEmpty());

        assertSame(pagina1, lista.removeLast());
        assertFalse(lista.isEmpty());

        assertSame(pagina2, lista.removeLast());
        assertFalse(lista.isEmpty());

        assertSame(pagina3, lista.removeLast());
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testAdicionaFinalRemoveInicio() {
        lista.addLast(pagina1);
        lista.addLast(pagina2);
        lista.addLast(pagina3);
        assertFalse(lista.isEmpty());

        assertSame(pagina1, lista.removeFirst());
        assertFalse(lista.isEmpty());

        assertSame(pagina2, lista.removeFirst());
        assertFalse(lista.isEmpty());

        assertSame(pagina3, lista.removeFirst());
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testAdicionaFinalRemoveFinal() {
        lista.addLast(pagina1);
        lista.addLast(pagina2);
        lista.addLast(pagina3);
        assertFalse(lista.isEmpty());

        assertSame(pagina3, lista.removeLast());
        assertFalse(lista.isEmpty());

        assertSame(pagina2, lista.removeLast());
        assertFalse(lista.isEmpty());

        assertSame(pagina1, lista.removeLast());
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testIterador() {
        Iterator it = lista.iterator();
        assertFalse(it.hasNext());

        lista.addLast(pagina1);
        lista.addLast(pagina2);
        lista.addFirst(pagina3);
        it = lista.iterator();
        assertTrue(it.hasNext());
        assertSame(pagina3, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina1, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina2, it.next());
        assertFalse(it.hasNext());
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> nova2
