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
public class ExpressaoUnica extends Expressao{
    public ExpressaoSimples expressaoSimples;

    public ExpressaoUnica(ExpressaoSimples expressaoSimples) {
        this.expressaoSimples = expressaoSimples;
    }
    
    public void visit(Visitor v){
        v.visitExpressaoUnica(this);
    }
    
}
