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
public class CondicionalCE extends CondicionalSE{
    public Comando comando2;

    public CondicionalCE(Comando comando2, Expressao expressao, Comando comando) {
        super(expressao, comando);
        this.comando2 = comando2;
    }
    
    public void visit(Visitor v){
        v.visitCondicionalCE(this);
    }

}
