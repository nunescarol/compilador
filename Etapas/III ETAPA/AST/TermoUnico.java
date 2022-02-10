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
public class TermoUnico extends Termo{
    public Fator fator;

    public TermoUnico(Fator fator) {
        this.fator = fator;
    }
    
    public void visit(Visitor v){
        v.visitTermoUnico(this);
    }
}
