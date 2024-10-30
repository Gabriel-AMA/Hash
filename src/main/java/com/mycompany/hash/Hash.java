/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hash;
import java.util.Random;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author otaku
 */
public class Hash {

    public static void main(String[] args) {
        Random random = new Random(27);
        int[] conjuntos = {1000000,5000000,20000000};
        Divisao divisao = new Divisao();
        //Multiplicacao multiplicacao = new Multiplicacao();
        //Dobramento dobramento = new Dobramento();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Grafico grafico = new Grafico();
        
        
        int[] tamanho ={100,1000,10000};
        
        for (int tamanhoC:conjuntos){
            Registro[] lista = new Registro[tamanhoC];
            for (int i=0; i<lista.length;i++){
                int rand = random.nextInt(100000000);
                lista[i] = new Registro(rand,rand);
            }
            for (int tamanhoH:tamanho){
                System.out.println("Tamanho da Tabela: " + tamanhoH);
                Node[] tabelaH = new Node[tamanhoH];
                divisao.Inserir(lista, tabelaH, dataset);
            }
            grafico.graficoTempo(dataset, tamanhoC);
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
