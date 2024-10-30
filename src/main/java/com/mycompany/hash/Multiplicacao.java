/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

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
        //System.out.println(chave);
        double fracionaria = chave * 0.618;
        //System.out.println(fracionaria);
        double multiplicacao = fracionaria*tamanho;
        //System.out.println(multiplicacao);
        return (int) (multiplicacao%tamanho);
    }
    
    public long Inserir(Registro[] lista, Node[] tabela){
        this.colisao=0;
        long comeco = System.currentTimeMillis();
        for (Registro lista1 : lista) {
            Inserir(lista1, tabela);
        }
        long fim = System.currentTimeMillis();
        this.tempoExe = fim-comeco;
        return this.tempoExe;
    }
    
     public void Inserir(Registro chave, Node[] lista) {
        int hChave = Chave(chave.getChave(), lista.length);
        int posicao = hChave;

        while (lista[posicao] != null) {
            posicao = (posicao + 1) % lista.length;
            this.colisao++;
            if (posicao == hChave) {
                return;
            }
        }
        
        lista[posicao] = new Node(chave.getValor());
    }

    public long Buscar(Registro chave, Node[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        return lista[hChave].getValor();
    }
    
    public long getColisao(){
        return this.colisao;
    }
}
