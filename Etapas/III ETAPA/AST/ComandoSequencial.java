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
public class ComandoSequencial extends Comando{
    public Comando comando1, comando2;

    public ComandoSequencial(Comando comando1, Comando comando2) {
        this.comando1 = comando1;
        this.comando2 = comando2;
    }
    
    public void visit(Visitor v){
        v.visitComandoSequencial(this);
    }
    
}
