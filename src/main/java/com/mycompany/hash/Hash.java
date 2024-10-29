/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hash;
import java.util.Random;

/**
 *
 * @author otaku
 */
public class Hash {

    public static void main(String[] args) {
        Random random = new Random(27);
        //int conjuntos = 1000000;
        Divisao divisao = new Divisao();
        //Multiplicacao multiplicacao = new Multiplicacao();
        //Dobramento dobramento = new Dobramento();
        //int[] tamanho ={100};
        int[] tabelaH = new int[100];
        Registro[] lista = new Registro[1000000];
        for (int i=0; i<lista.length;i++){
            int rand = random.nextInt(100000000);
            lista[i] = new Registro(rand,rand);
        }
        
        long tempoExe = divisao.Inserir(lista, tabelaH);
        long colisao = divisao.getColisao();
        System.out.println(tempoExe);
        System.out.println(colisao);
        long tempoBus = divisao.Buscar(lista, tabelaH);
        System.out.println(tempoBus);
    }
}
