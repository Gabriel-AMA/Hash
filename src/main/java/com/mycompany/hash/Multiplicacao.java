/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author otaku
 */

public class Multiplicacao {
    private int colisao;
    private long tempoExe;
    public Multiplicacao(){
        this.tempoExe=0;
        this.colisao=0;
    }

    public int Chave(long chave, int tamanho){
        double fracionaria = chave * 0.618;
        double multiplicacao = fracionaria*tamanho;
        return (int) (multiplicacao%tamanho);
    }
    
    public void Inserir(Registro[] lista, int[] tabela, DefaultCategoryDataset dataset, DefaultCategoryDataset datasetColisao){
        this.colisao=0;
        long comeco = System.currentTimeMillis();
        for (Registro lista1 : lista) {
            Inserir(lista1, tabela);
        }
        long fim = System.currentTimeMillis();
        this.tempoExe = fim-comeco;
        System.out.println("Tempo de Execucao Multiplicacao: "+this.tempoExe);
        dataset.addValue(this.tempoExe, "Multiplicacao", Integer.toString(tabela.length));
        System.out.println("Colisao Divisao: "+this.colisao);
        datasetColisao.addValue(this.colisao, "MUltiplicacao", Integer.toString(tabela.length));
    }
    
     public void Inserir(Registro chave, int[] lista) {
        int hChave = Chave(chave.getChave(), lista.length);
        int posicao = hChave;

        while (lista[posicao] != 0) {
            posicao = (posicao + 1) % lista.length;
            this.colisao++;
            if (posicao == hChave) {
                return;
            }
        }
        
        lista[posicao] = chave.getValor();
    }

    public void Buscar(Registro[] lista, int[] tabela, DefaultCategoryDataset dataset){
        long comeco = System.nanoTime();
        for (int i=0;i<6;i++) {
            System.out.println("Valor Buscado: "+Buscar(lista[i], tabela));
        }
        long fim = System.nanoTime();
        this.tempoExe = fim-comeco;
        System.out.println("Tempo de Busca Multiplicacao: "+this.tempoExe);
        dataset.addValue(this.tempoExe, "Multiplicacao", Integer.toString(tabela.length));
    }
    
    public long Buscar(Registro chave, int[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        if (lista[hChave] == chave.getValor()){
            return lista[hChave];
        }
        return 0;
    }
}
