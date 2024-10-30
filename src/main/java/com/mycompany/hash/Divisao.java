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
public class Divisao {
    private long tempoExe;
    private long colisao;

    public Divisao(){
        this.tempoExe=0;
        this.colisao=0;
    }

    public int Chave(long chave, int tamanho){
        return (int) chave%tamanho;
    }
    
    public void Inserir(Registro[] lista, Node[] tabela, DefaultCategoryDataset dataset, DefaultCategoryDataset datasetColisao){
        this.colisao=0;
        long comeco = System.currentTimeMillis();
        for (Registro lista1 : lista) {
            Inserir(lista1, tabela);
        }
        long fim = System.currentTimeMillis();
        this.tempoExe = fim-comeco;
        System.out.println("Tempo de Execucao Divisao: "+this.tempoExe);
        dataset.addValue(this.tempoExe, "Divisao", Integer.toString(tabela.length));
        System.out.println("Colisao Divisao: "+this.colisao);
        datasetColisao.addValue(this.colisao, "Divisao", Integer.toString(tabela.length));
    }
    public void Inserir(Registro chave, Node[] lista) {
        int hChave = Chave(chave.getChave(), lista.length);
        if (lista[hChave] == null) {
            lista[hChave] = new Node(chave.getValor());
        } else {
            Node atual = lista[hChave];
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(new Node(chave.getValor()));
            this.colisao++;
        }
    }
    
    public void Buscar(Registro[] lista, Node[] tabela, DefaultCategoryDataset dataset){
        long comeco = System.nanoTime();
        for (int i=0;i<6;i++) {
            System.out.println("Valor Buscado: "+Buscar(lista[i], tabela));
        }
        long fim = System.nanoTime();
        this.tempoExe = fim-comeco;
        System.out.println("Tempo de Busca Divisao: "+this.tempoExe);
        dataset.addValue(this.tempoExe, "Divisao", Integer.toString(tabela.length));
    }
    
    public long Buscar(Registro chave, Node[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
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

