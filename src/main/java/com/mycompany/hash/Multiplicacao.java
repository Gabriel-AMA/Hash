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
    private Node[][] tabelas;
    private final int rodadas;
    private final int[] tamanhos;

    public Multiplicacao(int rodada, int[] tamanhos) {
        this.tempoExe = 0;
        this.tempoBusca = 0;
        this.colisao = 0;
        this.tabelas = new Node[rodada*rodada][];
        this.rodadas = rodada;
        this.tamanhos=tamanhos;
    }

    public int chave(long chave, int tamanho) {
        double fracionaria = chave * 0.618;
        double multiplicacao = fracionaria * tamanho;
        return (int) (multiplicacao % tamanho);
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
            tempo.addValue(this.tempoExe, "Multiplicacao", "Tabela " + (index));
            dados.addValue(this.colisao, "Multiplicacao", "Tabela " + (index));
            index+=this.rodadas;
        }
    }

    public Node[] inserir(Registro[] conjuntos, Node[] tabela, int tamanho) {
        int inseridos=0;
        for (Registro chave : conjuntos) {
            if (inseridos>=tamanho){
                break;
            }
            int hChave = chave(chave.getChave(), tamanho);
            int posicao = hChave;

            while (tabela[posicao] != null) {
                posicao = (posicao + 1) % tamanho;
                this.colisao++;
                if (posicao == hChave) {
                    break;
                }
            }
            inseridos++;
            tabela[posicao] = new Node(chave.getValor());
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
            busca.addValue(this.tempoBusca, "Multiplicação", "Tabela " + i);
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
