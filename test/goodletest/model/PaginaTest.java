package goodletest.model;

import goodle.model.Pagina;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaginaTest {
    
    Pagina pagina1, pagina2, pagina3;
    
    @Before
    public void setUp() throws Exception {
        pagina1 = new Pagina("Pagina1");
        pagina2 = new Pagina("Pagina2");
        pagina3 = new Pagina("Pagina3");
    }
    
    @Test
    public void testQuantidade(){
        pagina1.setQuantDaPalavra();
        pagina1.setQuantDaPalavra();
        
        pagina2.setQuantDaPalavra();
        
        assertEquals(2, pagina1.getQuantDaPalavra());
        assertEquals(1, pagina2.getQuantDaPalavra());
    }
    
    @Test
    public void testAcessos(){
        pagina3.quantAcesso();
        pagina3.quantAcesso();
        pagina3.quantAcesso();
        
        pagina2.quantAcesso();
        pagina2.quantAcesso();
        
        assertEquals(3, pagina3.getAcesso());
        assertEquals(2, pagina2.getAcesso());
    }
}
