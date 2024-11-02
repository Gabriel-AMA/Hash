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
public class Divisao {
    private long tempoExe;
    private long tempoBusca;
    private long colisao;
    private Registro[][] listas;
    private Node[][] tabelas;
    private int[] seeds;
    private int[] conjuntos;
    private int rodadas;
    private int[] tamanhos;

    public Divisao(int[] conjuntos, int[] seeds, int rodada, int[] tamanhos) {
        this.tempoExe = 0;
        this.tempoBusca = 0;
        this.colisao = 0;
        this.conjuntos = conjuntos;
        this.listas = new Registro[rodada][];
        this.tabelas = new Node[rodada*rodada][];
        this.seeds = seeds;
        this.rodadas = rodada;
        this.tamanhos=tamanhos;
    }

    public void gerarDados(int tamanho, int index) {
        Random random = new Random(seeds[index]);
        Registro[] lista = new Registro[tamanho];
        for (int l = 0; l < tamanho; l++) {
            long rand = random.nextInt(10000000);
            lista[l] = new Registro(rand, (int)rand);
        }
        this.listas[index] = lista;
    }

    public int chave(long chave, int tamanho) {
        return (int)chave % tamanho;
    }

    public void inserir(DefaultCategoryDataset tempo, DefaultCategoryDataset dados) {
        this.colisao = 0;
        this.tempoExe = 0;
        int index = 0;

        for (int tamanho : this.conjuntos) {
            gerarDados(tamanho, index);
            for (int j = 0; j < this.rodadas; j++) {
                Node[] tabela = new Node[tamanhos[index]];
                long comeco = System.currentTimeMillis();
                tabela = inserir(this.listas[index], tabela, tamanhos[index]);
                long fim = System.currentTimeMillis();
                this.tempoExe = fim - comeco;
                tempo.addValue(this.tempoExe, "Divisão", "Tabela " + (index * this.rodadas + j + 1));
                dados.addValue(this.colisao, "Divisão", "Tabela " + (index * this.rodadas + j + 1));
                this.tabelas[index * this.rodadas + j] = tabela;
            }
            index++;
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

    public void buscar(DefaultCategoryDataset tempo) {
        for (int i = 0; i < this.rodadas * this.rodadas; i++) {
            long comeco = System.nanoTime();
            for (int j = 0; j < 5; j++) {
                buscar(this.listas[i / this.rodadas][j], this.tabelas[i], this.conjuntos[i / this.rodadas]);
            }
            long fim = System.nanoTime();
            this.tempoBusca = fim - comeco;
            tempo.addValue(this.tempoBusca, "Divisão", "Tabela " + (i + 1));
        }
    }

    public long buscar(Registro chave, Node[] lista, int tamanho) {
        int hChave = chave(chave.getChave(), tamanho);
        if (lista[hChave] != null && lista[hChave].getValor() == chave.getValor()) {
            return lista[hChave].getValor();
        } else {
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
}
