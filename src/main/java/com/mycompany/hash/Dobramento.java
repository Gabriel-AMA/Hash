/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

/**
 *
 * @author otaku
 */
public class Dobramento {
    public Dobramento(){}
    
    public int Chave(long chave, int tamanho) {
        long chaveFim = chave%tamanho;
        long chaveComeco = chave/tamanho;
        long chaveFinal = chaveFim+chaveComeco;
        //System.out.println(chave);
        //System.out.println(chaveFinal);
        return (int) chaveFinal%tamanho;
    }
    
    public void Inserir(Registro chave, int[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        lista[hChave] = chave.getValor();
    }

    public long Buscar(Registro chave, int[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        return lista[hChave];
    }
}
