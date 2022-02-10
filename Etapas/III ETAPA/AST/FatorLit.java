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
public class FatorLit extends Fator{
    public Literal literal;

    public FatorLit(Literal literal) {
        this.literal = literal;
    }
    
    public void visit(Visitor v){
        v.visitFatorLit(this);
    }
}
