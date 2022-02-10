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
public class ComandoCondicional extends Comando{
    public Condicional condicional;

    public ComandoCondicional(Condicional condicional) {
        this.condicional = condicional;
    }
    
    public void visit(Visitor v){
        v.visitComandoCondicional(this);
    }
}
