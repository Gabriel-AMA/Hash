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
    private long valor;
    private Node proximo;
    
    public Node(long valor){
        this.valor=valor;
        this.proximo=null;
    }
    
    public void setValor(long valor){
        this.valor=valor;
    }
    public void setProximo(Node node){
        this.proximo=node;
    }
    public long getValor(){
        return this.valor;
    }
    public Node getProximo(){
        return this.proximo;
    }
}
