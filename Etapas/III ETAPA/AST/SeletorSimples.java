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
public class SeletorSimples extends Seletor{
    public Expressao expressao;

    public SeletorSimples(Expressao expressao) {
        this.expressao = expressao;
    }
    
    public void visit(Visitor v){
        v.visitSeletorSimples(this);
    }
}
