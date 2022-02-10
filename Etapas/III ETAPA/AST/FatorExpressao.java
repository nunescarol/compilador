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
public class FatorExpressao extends Fator{
    public Expressao expressao;

    public FatorExpressao(Expressao expressao) {
        this.expressao = expressao;
    }
    
    public void visit(Visitor v){
        v.visitFatorExpressao(this);
    }
}
