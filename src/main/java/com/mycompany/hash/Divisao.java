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
    private int size;

    public Divisao(int[] conjuntos, int[] seeds, int size){
        this.tempoExe=0;
        this.tempoBusca=0;
        this.colisao=0;
        this.conjuntos=conjuntos;
        this.listas=new Registro[size][];
        this.tabelas=new Node[size*size][];
        this.seeds=seeds;
        this.size = size;
    }
    
    public void gerarDados(int tamanho, int index) {
        Random random = new Random(seeds[index]);
        Registro[] lista = new Registro[tamanho];
        for (int l = 0; l < tamanho; l++) {
            long rand = random.nextInt(10000000);
            lista[l] = new Registro(rand,(int)rand);
        }
        this.listas[index] = lista;
    }

    public int chave(long chave, int tamanho){
        return (int) chave%tamanho;
    }
    
    
    public Node[] inserir(Registro[] conjunto, Node[] tabela, int tamanho) {
        for(Registro chave:conjunto){
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
    
    public void inserir(DefaultCategoryDataset tempo, DefaultCategoryDataset dados){
        this.colisao = 0;
        this.tempoExe = 0;
        int index = 0;

        for (int tamanho : this.conjuntos) {
            gerarDados(tamanho, index);
            for (int j = 0; j < this.size; j++) {
                Node[] tabela = new Node[tamanho];
                long comeco = System.currentTimeMillis();
                tabela = inserir(this.listas[index], tabela, tamanho);
                long fim = System.currentTimeMillis();
                this.tempoExe += fim - comeco;

                tempo.addValue(this.tempoExe, "Divisão", "Tabela " + (index * this.size + j + 1));
                dados.addValue(this.colisao, "Divisão", "Tabela " + (index * this.size + j + 1));
                this.tabelas[index * this.size + j] = tabela;
            }
            index++;
        }
    }
    
    public void buscar(DefaultCategoryDataset tempo){
       for (int i = 0; i < this.size * this.size; i++) {
           long comeco = System.currentTimeMillis();
           for (int j = 0; j < 5; j++) {
               buscar(this.listas[i / this.size][j], this.tabelas[i], this.conjuntos[i/this.size]);
           }
           long fim = System.currentTimeMillis();
           this.tempoBusca = fim - comeco;
           tempo.addValue(this.tempoBusca, "Divisão", "Tabela " + (i + 1));
       }
    }
    
    public long buscar(Registro chave, Node[] lista, int tamanho){
        int hChave = chave(chave.getChave(), tamanho);
        if (lista[hChave].getValor() == chave.getValor()){
            return lista[hChave].getValor();
        }
        else{
            Node atual = lista[hChave].getProximo();
            while (atual!=null){
                if (atual.getValor()==chave.getValor()){
                    return atual.getValor();
                }
                atual = atual.getProximo();
            }
        }
        return 0;
    }
}

