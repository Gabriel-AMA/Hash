/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

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
    
    public long Inserir(Registro[] lista, int[] tabela){
        this.colisao=0;
        long comeco = System.currentTimeMillis();
        for (Registro lista1 : lista) {
            Inserir(lista1, tabela);
        }
        long fim = System.currentTimeMillis();
        this.tempoExe = fim-comeco;
        return this.tempoExe;
    }
    
    public void Inserir(Registro chave, int[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        if (lista[hChave]==0){
            lista[hChave]=chave.getValor();
        }
        else{
            this.colisao++;
        }
        
    }
    
    public long Buscar(Registro[] lista, int[] tabela){
        long comeco = System.currentTimeMillis();
        for (int i=0;i<6;i++) {
            System.out.println(Buscar(lista[i], tabela));
        }
        long fim = System.currentTimeMillis();
        this.tempoExe = fim-comeco;
        return this.tempoExe;
    }
    
    public long Buscar(Registro chave, int[] lista){
        int hChave = Chave(chave.getChave(), lista.length);
        if (lista[hChave] == chave.getValor()){
            return lista[hChave];
        }
        return 0;
    }
    
    public long getColisao(){
        return this.colisao;
    }
}
