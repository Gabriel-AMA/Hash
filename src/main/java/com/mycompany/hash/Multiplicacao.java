/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

import java.util.Random;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * Adaptado para registrar tempo e colisões para inserções e buscas.
 */
public class Multiplicacao {
    private long tempoExe;
    private long tempoBusca;
    private long colisao;
    private Registro[][] listas;
    private Node[][] tabelas;
    private int[] seeds;
    private int[] conjuntos;
    private int size;

    public Multiplicacao(int[] conjuntos, int[] seeds, int size) {
        this.tempoExe = 0;
        this.tempoBusca = 0;
        this.colisao = 0;
        this.conjuntos = conjuntos;
        this.listas = new Registro[size][];
        this.tabelas = new Node[size * size][];
        this.seeds = seeds;
        this.size = size;
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
        double fracionaria = chave * 0.618;
        double multiplicacao = fracionaria * tamanho;
        return (int) (multiplicacao % tamanho);
    }
    

    public void inserir(DefaultCategoryDataset tempo, DefaultCategoryDataset dados) {
        this.colisao = 0;
        this.tempoExe = 0;
        int index = 0;

        for (int tamanho : this.conjuntos) {
            gerarDados(tamanho, index);
            for (int j = 0; j < size; j++) {
                Node[] tabela = new Node[tamanho];
                long comeco = System.currentTimeMillis();
                tabela = inserir(this.listas[index], tabela, tamanho);
                long fim = System.currentTimeMillis();
                this.tempoExe = fim - comeco;
                tempo.addValue(this.tempoExe, "Multiplição", "Tabela " + (index * this.size + j + 1));
                dados.addValue(this.colisao, "Multiplição", "Tabela " + (index * this.size + j + 1));
                this.tabelas[index * this.size + j] = tabela;
            }
            index++;
        }
    }

    public Node[] inserir(Registro[] conjuntos, Node[] tabela, int tamanho) {
        for (Registro chave : conjuntos) {
            int hChave = chave(chave.getChave(), tamanho);
            int posicao = hChave;

            while (tabela[posicao] != null) {
                posicao = (posicao + 1) % tabela.length;
                this.colisao++;
                if (posicao == hChave) {
                    return null;
                }
            }

            tabela[posicao] = new Node(chave.getValor());
        }
        return tabela;
    }

    public void buscar(DefaultCategoryDataset tempo) {
        for (int i = 0; i < this.size * this.size; i++) {
            long comeco = System.nanoTime();
            for (int j = 0; j < 5; j++) {
                buscar(this.listas[i / this.size][j], this.tabelas[i], this.conjuntos[i / this.size]);
            }
            long fim = System.nanoTime();
            this.tempoBusca = fim - comeco;
            tempo.addValue(this.tempoBusca, "Dobramento", "Tabela " + (i + 1));
        }
    }

    public long buscar(Registro chave, Node[] tabela, int tamanho) {
        int hChave = chave(chave.getChave(), tamanho);

        if (tabela[hChave].getValor() == chave.getValor()) {
            return tabela[hChave].getValor();
        }
        
        int posicao = (hChave + 1) % tabela.length;
        
        while (posicao != hChave) {
            if (tabela[posicao].getValor() == chave.getValor()) {
                return tabela[posicao].getValor();
            }
            posicao = (posicao + 1) % tabela.length;
        }
        
        return 0;
    }
}
