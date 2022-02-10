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
public class FatorVariavel extends Fator{
    public Variavel variavel;

    public FatorVariavel(Variavel variavel) {
        this.variavel = variavel;
    }
    
    public void visit(Visitor v){
        v.visitFatorVariavel(this);
    }
}
