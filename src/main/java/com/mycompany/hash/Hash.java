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
        int[] seeds = {3,9,21};
        int[] conjuntos = {100000};
        Divisao divisao = new Divisao();
        Multiplicacao multiplicacao = new Multiplicacao();
        Dobramento dobramento = new Dobramento();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetColisao = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetBusca = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetM = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetColisaoM = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetBuscaM = new DefaultCategoryDataset();
        Grafico grafico = new Grafico();
        
        
        int[] tamanho ={1000,10000,100000};
        
        for (int i=0;i<conjuntos.length;i++){
            Random random = new Random(seeds[i]);
            Registro[] lista = new Registro[conjuntos[i]];
            for (int j=0; j<lista.length;j++){
                int rand = random.nextInt(100000000);
                lista[j] = new Registro(rand,rand);
            }
            System.out.println("------- Tamanho do conjunto: " + conjuntos[i] + "-------");
            for (int tamanhoH:tamanho){
                System.out.println("----- Tamanho da Tabela: " + tamanhoH + "-----");
                Node[] tabelaH = new Node[tamanhoH];
                int[] tabelaHM = new int[tamanhoH];
                Node[] tabelaHD = new Node[tamanhoH];
                divisao.Inserir(lista, tabelaH, dataset, datasetColisao);
                multiplicacao.Inserir(lista, tabelaHM, datasetM, datasetColisaoM);
                dobramento.Inserir(lista, tabelaHD, dataset, datasetColisao);    
                divisao.Buscar(lista,tabelaH, datasetBusca);
                multiplicacao.Buscar(lista, tabelaHM, datasetBuscaM);
                dobramento.Buscar(lista,tabelaHD, datasetBusca);
            }
            grafico.graficoTempo(dataset, conjuntos[i]);
            grafico.graficoColisao(datasetColisao, conjuntos[i]);
            grafico.graficoTempo(datasetM, conjuntos[i]);
            grafico.graficoColisao(datasetColisaoM, conjuntos[i]);
            grafico.graficoBusca(datasetBusca, conjuntos[i]);
            grafico.graficoBusca(datasetBuscaM, conjuntos[i]);
        }

    }
}
