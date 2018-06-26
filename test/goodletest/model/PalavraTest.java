package goodletest.model;

import goodle.model.*;
import goodle.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalavraTest {

    Palavra palavra1, palavra2, palavra3;
    Pagina pagina1, pagina2;

    @Before
    public void setUp() throws Exception {
        palavra1 = new Palavra("Palavra1");
        palavra2 = new Palavra("Palavra2");
        palavra3 = new Palavra("Palavra3");
        pagina1 = new Pagina("Pagina1");
        pagina2 = new Pagina("Pagina2");
    }

    @Test
    public void testQuantidade() {
        palavra1.quantidade();
        palavra1.quantidade();

        palavra2.quantidade();

        assertEquals(3, palavra1.getQuantidade());
        assertEquals(2, palavra2.getQuantidade());
    }

    @Test
    public void testBusca() {
        palavra3.buscas();
        palavra3.buscas();
        palavra3.buscas();

        palavra2.buscas();
        palavra2.buscas();

        assertEquals(3, palavra3.getBuscas());
        assertEquals(2, palavra2.getBuscas());
    }

    @Test
    public void testLista() {
        palavra1.addPagina(pagina1);
        palavra1.addPagina(pagina2);

        Iterator it = palavra1.getlPagina().iterator();
        assertTrue(it.hasNext());

        assertSame(pagina1, it.next());
        assertTrue(it.hasNext());
        assertSame(pagina2, it.next());
        assertFalse(it.hasNext());
    }
}
