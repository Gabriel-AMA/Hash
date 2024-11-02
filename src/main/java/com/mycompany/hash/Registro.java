/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

/**
 *
 * @author otaku
 */
public class Registro {
    private long chave;
    private int valor;

    public Registro(long chave, int valor){
        this.valor=valor;
        this.chave=chave;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public long getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
}

