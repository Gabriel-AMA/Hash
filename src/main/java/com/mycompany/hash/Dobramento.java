/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

import java.util.Random;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author otaku
 */
public class Dobramento {
    private long tempoExe;
    private long tempoBusca;
    private long colisao;
    private Node[][] tabelas;
    private final int rodadas;
    private final int[] tamanhos;

    public Dobramento(int rodada, int[] tamanhos) {
        this.tempoExe = 0;
        this.tempoBusca = 0;
        this.colisao = 0;
        this.tabelas = new Node[rodada*rodada][];
        this.rodadas = rodada;
        this.tamanhos=tamanhos;
    }

    public int chave(long chave, int tamanho) {
        long chaveFim = chave % tamanho;
        long chaveComeco = chave / tamanho;
        long chaveFinal = chaveFim + chaveComeco;
        return (int) chaveFinal % tamanho;
    }

public void inserir(Registro[] conjunto, int index, DefaultCategoryDataset tempo, DefaultCategoryDataset dados) {
        for (int tamanho:this.tamanhos) {
            this.colisao = 0;
        this.tempoExe = 0;
            Node[] tabela = new Node[tamanho];
            long comeco = System.currentTimeMillis();
            tabela = inserir(conjunto, tabela, tamanho);
            long fim = System.currentTimeMillis();
            this.tempoExe = fim - comeco;
            this.tabelas[index] = tabela;
            tempo.addValue(this.tempoExe, "Dobramento", "Tabela " + (index));
            dados.addValue(this.colisao, "Dobramento", "Tabela " + (index));
            index+=this.rodadas;
        }
    }

    public Node[] inserir(Registro[] conjuntos, Node[] tabela, int tamanho) {
        for (Registro chave : conjuntos) {
            int hChave = chave(chave.getChave(), tamanho);
            if (tabela[hChave] == null) {
                tabela[hChave] = new Node(chave.getValor());
            } else {
                Node atual = tabela[hChave];
                while (atual.getProximo() != null) {
                    atual = atual.getProximo();
                }
                atual.setProximo(new Node(chave.getValor()));
                this.colisao++;
            }
        }
        return tabela;
    }

    public void buscar(Registro[] conjunto, DefaultCategoryDataset busca, int index, int tamanho, int seed) {
        for (int i = index; i < this.rodadas * this.rodadas; i+=this.rodadas) {
            long comeco = System.nanoTime();
            for (int j = 0; j < 5; j++) {
                Random random = new Random(seed);
                int rand = random.nextInt(tamanho);
                buscar(conjunto[rand], this.tabelas[i], this.tamanhos[i / this.rodadas]);
            }
            long fim = System.nanoTime();
            this.tempoBusca = fim - comeco;
            busca.addValue(this.tempoBusca, "Dobramento", "Tabela " + i);
        }
    }

    public long buscar(Registro chave, Node[] lista, int tamanho) {
        int hChave = chave(chave.getChave(), tamanho);
        if (lista[hChave] != null && lista[hChave].getValor() == chave.getValor()) {
            return lista[hChave].getValor();
        } else if(lista[hChave] != null && lista[hChave].getValor() != chave.getValor()) {
            Node atual = lista[hChave].getProximo();
            while (atual != null) {
                if (atual.getValor() == chave.getValor()) {
                    return atual.getValor();
                }
                atual = atual.getProximo();
            }
        }
        return 0;
    }
    public void reset(){
        this.tabelas=null;
    }
}
