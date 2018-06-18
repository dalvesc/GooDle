package goodle.controller;

import goodle.model.Pagina;
import goodle.model.Palavra;
import goodle.util.Ilist;
import goodle.util.LinkedList;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Controller {
    
    Ilist listaPalavras = new LinkedList();

    public void AdicionarPalavras() {
  
        Pagina pagina;
        Palavra palavras;
        String palavra;
        
        try {
            File arq = new File("jogadoresCadastrados.txt");
            
            pagina = new Pagina(arq);
            Scanner scan = new Scanner(new FileReader(arq)).useDelimiter(" |\n");
            
            //Falta a condição caso a palavra já tenha aparecido ateriormente
            while (scan.hasNext()) {
                palavra = scan.next();

                palavras = new Palavra(palavra);
                palavras.Pagina(pagina);
                
                listaPalavras.addLast(palavras);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo!");
        }
    }
    
    public void Buscar(){
    }

    
}
