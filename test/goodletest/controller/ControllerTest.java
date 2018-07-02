package goodletest.controller;

import goodle.model.*;
import goodle.util.*;
import goodle.controller.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTest {

    Controller controller;
    Object pagina1, palavra;
    Ilist lista;

    @Before
    public void setUp() throws Exception {
        controller = new Controller();
        pagina1 = new Pagina("teste2.txt");
        palavra = new Palavra("daniel", (Pagina) pagina1);
        lista = new LinkedList();
    }

    @Test
    public void testBusca() {
        lista = controller.buscar((Palavra) palavra, true);
        Iterator iterator = lista.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), pagina1);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRanking() {
        controller.buscar((Palavra) palavra, true);
        lista = controller.ranking("palavra", true);
        Iterator iterator = lista.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), palavra);
        assertFalse(iterator.hasNext());
    }
}
