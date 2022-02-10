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
public class ComandoIterativo extends Comando{
    public Iterativo iterativo;

    public ComandoIterativo(Iterativo iterativo) {
        this.iterativo = iterativo;
    }
    
    public void visit(Visitor v){
        v.visitComandoIterativo(this);
    }
    
}
