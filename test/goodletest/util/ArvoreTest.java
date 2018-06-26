package goodletest.util;

import goodle.model.*;
import goodle.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ArvoreTest {
    
    Arvore test;
    Object palavra1, palavra2, palavra3, pagina;
    
    @Before
    public void setUp() throws Exception {
        test = new Arvore();
        palavra1 = new Palavra("Palavra1");
        palavra2 = new Palavra("Palavra2");
        palavra3 = new Palavra("Palavra3");
        pagina = new Pagina("Arquivo1");
    }
    
    @Test
    public void testEstaVazia() {
        assertEquals(0, test.size());
    }
    
    @Test
    public void testTamanho() {
        assertEquals(0, test.size());

        test.inserir(palavra1, pagina);
        assertEquals(1, test.size());

        test.inserir(palavra2, pagina);
        test.inserir(palavra3, pagina);
        assertEquals(3, test.size());

        test.remove(palavra3);
        assertEquals(2, test.size());

        test.remove(palavra2);
        test.remove(palavra1);
        assertEquals(0, test.size());
    }
    
    @Test
    public void testBusca(){
        test.inserir(palavra1, pagina);
        test.inserir(palavra2, pagina);
        test.inserir(palavra3, pagina);
        assertEquals(3, test.size());
        
        assertSame(test.busca(palavra1), palavra1);
        test.remove(palavra1);
        assertEquals(2, test.size());
        
        assertSame(test.busca(palavra2), palavra2);
        
        assertSame(test.busca(palavra3), palavra3);
        test.remove(palavra3);
        assertEquals(1, test.size());
        
        test.remove(palavra2);
        assertEquals(0, test.size());
    }
}
