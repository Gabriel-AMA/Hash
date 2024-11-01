/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hash;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author otaku
 */
public class Hash {

    public static void main(String[] args) {
        int[] seeds = {3,9,21};
        int[] conjuntos = {1000000,5000000,20000000};
        int[] tamanho ={1000,10000,100000};
        int size =3;
        
        Divisao divisao = new Divisao(conjuntos, seeds, size);
        //Dobramento dobramento = new Dobramento(conjuntos, seeds, size);
        Multiplicacao multiplicacao = new Multiplicacao(conjuntos, seeds, size);
        
        
        DefaultCategoryDataset tempo = new DefaultCategoryDataset();
        DefaultCategoryDataset colisao = new DefaultCategoryDataset();
        DefaultCategoryDataset busca = new DefaultCategoryDataset();
        
        
        Grafico grafico = new Grafico();

        
        divisao.inserir(tempo, colisao);
        divisao.buscar(busca);
        //dobramento.inserir(tempo, colisao);
        //dobramento.buscar(busca);
        multiplicacao.inserir(tempo,colisao);
        multiplicacao.buscar(busca);
        
        grafico.graficoTempo(tempo);
        grafico.graficoColisao(colisao);
        grafico.graficoBusca(busca);
    }
}
