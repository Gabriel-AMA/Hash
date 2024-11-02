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
        int[] conjuntos = {1000000,5000000,20000000};
        int[] tamanhos ={1000,10000,100000};
        int rodadas =3;
        Registro[][] listas = new Registro[rodadas][];
        
        Divisao divisao = new Divisao(conjuntos, seeds, rodadas, tamanhos);
        
        DefaultCategoryDataset tempo = new DefaultCategoryDataset();
        DefaultCategoryDataset colisao = new DefaultCategoryDataset();
        DefaultCategoryDataset busca = new DefaultCategoryDataset();
        
        divisao.inserir(tempo, colisao);
        divisao.buscar(busca);
        
        
        Grafico grafico = new Grafico();

        
        grafico.graficoTempo(tempo);
        grafico.graficoColisao(colisao);
        grafico.graficoBusca(busca);
    }
}
