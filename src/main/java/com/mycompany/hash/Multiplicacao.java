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
    public Multiplicacao(){}

    public int Chave(long chave, int tamanho){
        //System.out.println(chave);
        double fracionaria = chave * 0.618;
        //System.out.println(fracionaria);
        double multiplicacao = fracionaria*tamanho;
        //System.out.println(multiplicacao);
        return (int) (multiplicacao%tamanho);
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
