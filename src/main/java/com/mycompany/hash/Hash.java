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
        Multiplicacao multiplicacao = new Multiplicacao();
        Dobramento dobramento = new Dobramento();
        int[] tamanho ={100,1000,10000};
        Registro[] lista = new Registro[1000000];
        for (int i=0; i<lista.length;i++){
            int rand = random.nextInt(100000000);
            lista[i] = new Registro(rand,rand);
        }
        for (int tamanho1:tamanho){
            Node[] tabelaH = new Node[tamanho1];
            long tempoExe = divisao.Inserir(lista, tabelaH);
            long colisao = divisao.getColisao();
            System.out.println("Tempo de Execucao Divisao: " + tempoExe);
            System.out.println("Numero de Colisoes Divisao: "+ colisao);
        }
        //long tempoExeM = multiplicacao.Inserir(lista, tabelaH);
        //long colisaoM = multiplicacao.getColisao();
        //long tempoExeD = dobramento.Inserir(lista, tabelaH);
        //long colisaoD = dobramento.getColisao();
       
        //System.out.println("Tempo de Execucao Multiplicacao: " + tempoExeM);
        //System.out.println("Numero de Colisoes Multiplicacao: "+ colisaoM);
        //System.out.println("Tempo de Execucao Multiplicacao: " + tempoExeD);
        //System.out.println("Numero de Colisoes Multiplicacao: "+ colisaoD);
        //long tempoBus = divisao.Buscar(lista, tabelaH);
        //System.out.println("Tempo de Busca: " + tempoBus);
    }
}
