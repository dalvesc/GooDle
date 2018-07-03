package goodletest.controller;

import goodle.model.*;
import goodle.util.*;
import goodle.controller.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class ControllerTest {

    Controller controller;
    Object pagina1;
    Palavra palavra;
    Ilist lista;

    @Before
    public void setUp() throws Exception {
        controller = new Controller();
        pagina1 = new Pagina("teste2.txt");
        palavra = new Palavra("daniel", (Pagina) pagina1);
        lista = new LinkedList();
        
        String textoQueSeraEscrito = "Texto para arquivo para teste de"
                + " exclusão de arquivo";
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(new File(controller.getDiretorio(), "Arquivo.txt"));
            arquivo.write(textoQueSeraEscrito);
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void testDeletar() throws FileNotFoundException, UnsupportedEncodingException{
        controller.buscar((Palavra) palavra, true);
        assertTrue(controller.deletarPagina("Arquivo"));
    }
    
   @Test
    public void testRanking() {
        controller.buscar((Palavra) palavra, true);
        
        lista = controller.ranking("palavra", true);
        Iterator iterator = lista.iterator();
        assertTrue(iterator.hasNext());        
        Palavra temp = (Palavra) iterator.next();
        assertEquals(0, temp.compareTo(palavra.getPalavra()));
        assertFalse(iterator.hasNext());        
    }
}
