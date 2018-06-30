package goodletest.util;

import goodle.model.*;
import goodle.util.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ArvoreTest {

    Arvore test;
    Palavra palavra1, palavra2, palavra3;
    Pagina pagina, pagina2;

    @Before
    public void setUp() throws Exception {
        test = new Arvore();
        pagina = new Pagina("Pagina1");
        pagina2 = new Pagina("Pagina2");
        palavra1 = new Palavra("Palavra1", pagina);
        palavra2 = new Palavra("Palavra1", pagina2);
        palavra3 = new Palavra("Palavra3", pagina);

    }

    @Test
    public void testBusca() {
        test.inserir(palavra1);
        test.inserir(palavra2);
        test.inserir(palavra3);

        assertSame(test.busca(palavra1), palavra1);
        test.remove(palavra1);

        assertSame(test.busca(palavra3), palavra3);
        test.remove(palavra3);
    }
    
    @Test
    public void testListaPagina(){
        Pagina pag;
        test.inserir(palavra1);
        test.inserir(palavra2);
        test.inserir(palavra3);
        
        Iterator iterator = palavra1.getlPagina().iterator();
        assertTrue(iterator.hasNext());
        pag = (Pagina) iterator.next();
        assertSame(pagina, pag);
        assertTrue(iterator.hasNext());
        pag = (Pagina) iterator.next();
        assertSame(pagina2, pag);
        assertFalse(iterator.hasNext());
    }
}