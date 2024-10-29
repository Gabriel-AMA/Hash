/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

/**
 *
 * @author otaku
 */
public class Node {
    private int valor;
    private Node proximo;
    
    public Node(int valor){
        this.valor=valor;
        this.proximo=null;
    }
    public void setValor(int valor){
        this.valor=valor;
    }
    public void setProximo(Node proximo){
        this.proximo=proximo;
    }
    public int getValor(){
        return this.valor;
    }
    public Node getProximo(){
        return this.proximo;
    }
}
