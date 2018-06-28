/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodle.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gabriela
 */
public class Teste {

    public static void main(String[] args) {

        String string = "Olás àèìòùáéíóúãẽĩõũç";
        String textoFormatado = "";

        Pattern pattern = Pattern.compile("[\\p{L}0-9]+{1,}"); //ESSA EXPRESSÃO É RESPONSÁVEL POR PROCURAR UM DETERMINADO PADRÃO NO TEXTO, O PADRÃO NESSE CASO É APENAS LETRAS, NÚMEROS E ESPAÇOS EM BRANCO
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            textoFormatado += (matcher.group() + " "); //E AQUI O "MATCHER" DEVOLVE PRA STRING TUDO O QUE ELE ACHOU NO TEXTO QUE SATISFAZ ESSE PADRÃO               
        }
        String[] p = textoFormatado.split(" \n");
        
       for(String c: p){
           System.out.println(c);
       }
    }

}
