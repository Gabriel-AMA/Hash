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
    
    public static Registro[] gerarDados(int tamanho, int seed){
        Random random = new Random(seed);
        Registro[] lista = new Registro[tamanho];
        for (int l = 0; l < tamanho; l++) {
            long rand = random.nextInt(100000000);
            lista[l] = new Registro(rand, (int)rand);
        }
        return lista;
    }
    
    public static void main(String[] args) {
        int[] seeds = {3,9,21}, conjuntos = {1000000,5000000,20000000}, tamanhos = {125000,250000,500000};
        int rodadas =3;
        Registro[][] listas = new Registro[rodadas][];
        
        //Divisao divisao = new Divisao(rodadas, tamanhos);
        //Dobramento dobramento = new Dobramento(rodadas,tamanhos);
        Multiplicacao multiplicacao = new Multiplicacao(rodadas,tamanhos);
        
        DefaultCategoryDataset tempo = new DefaultCategoryDataset();
        DefaultCategoryDataset colisao = new DefaultCategoryDataset();
        DefaultCategoryDataset busca = new DefaultCategoryDataset();
        
        for (int index=0; index<rodadas;index++){
            listas[index]=gerarDados(conjuntos[index],seeds[index]);
            //divisao.inserir(listas[index],index, tempo, colisao);
            //divisao.buscar(listas[index], busca, index,conjuntos[index], seeds[index]);
            //dobramento.inserir(listas[index],index, tempo, colisao);
            //dobramento.buscar(listas[index], busca, index,conjuntos[index], seeds[index]);
            multiplicacao.inserir(listas[index],index, tempo, colisao);
            multiplicacao.buscar(listas[index], busca, index,conjuntos[index], seeds[index]);
        }
        
        
        Grafico grafico = new Grafico();

        
        grafico.graficoTempo(tempo);
        grafico.graficoColisao(colisao);
        grafico.graficoBusca(busca);
    }
}
