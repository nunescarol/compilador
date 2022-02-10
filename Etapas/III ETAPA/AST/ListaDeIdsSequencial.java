/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import visitor.Visitor;

/**
 *
 * @author Usuario
 */
public class ListaDeIdsSequencial extends ListaDeIds{
    public ListaDeIds esquerda, direita;

    public ListaDeIdsSequencial(ListaDeIds esquerda, ListaDeIds direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }
    
    public void visit(Visitor v){
        v.visitListaDeIdsSequencial(this);
    }


    
}
